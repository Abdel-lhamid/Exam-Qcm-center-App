package ma.ensaf.Qcmexamcenterbackend.services.implimentation;

import io.jsonwebtoken.Jwts;
import ma.ensaf.Qcmexamcenterbackend.config.JwtService;
import ma.ensaf.Qcmexamcenterbackend.dtos.StudentGroupDto;
import ma.ensaf.Qcmexamcenterbackend.dtos.UserDto;
import ma.ensaf.Qcmexamcenterbackend.entities.GroupEntity;
import ma.ensaf.Qcmexamcenterbackend.entities.StudentEntity;
import ma.ensaf.Qcmexamcenterbackend.repositories.GroupRepository;
import ma.ensaf.Qcmexamcenterbackend.repositories.StudentRepository;
import ma.ensaf.Qcmexamcenterbackend.services.StudentService;
import ma.ensaf.Qcmexamcenterbackend.services.UserService;
import ma.ensaf.Qcmexamcenterbackend.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @Autowired
    JwtService jwtService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    GroupRepository groupRepository;


    @Autowired
    Utils utils;
    @Override
    public StudentEntity addStudentToGroup(String email, GroupEntity savedGroup) {


        StudentEntity studentExists = studentRepository.findByEmail(email).orElse(null);

        if(studentExists != null){
            studentExists.setGroup(savedGroup);
            return studentRepository.save(studentExists);
        }
        else{
            StudentEntity student = StudentEntity.builder()
                            .email(email)
                    .userId(utils.generateCustomId(20))
                    .fullName("Student")
                    .group(savedGroup)
                    .isActive(false)
                    .school(savedGroup.getSchool())
                    .build();
            //TODO: send email invite to student
            try {
                authService.sendEmailVerification(student, "students/complete-sign-up");
            }catch (Exception e){
                throw new RuntimeException(e.getMessage(),e.getCause());
            }
            return studentRepository.save(student);
        }

    }



    @Override
    public StudentEntity getStudentByEmail(String email) {
        StudentEntity student = studentRepository.findByEmail(email).orElse(null);
        if(student != null){
            return student;
        }
        else {
            throw new UsernameNotFoundException("Student not found with email: " + email);
        }
    }

    @Override
    public ResponseEntity<String> completeSignup(String verificationToken, UserDto profileInfo) {
        String emailToken = jwtService.extractEmail(verificationToken);
        StudentEntity student = studentRepository.findByEmail(emailToken).orElse(null);
        if(student != null){
            if(!Objects.equals(student.getVerificationToken(), verificationToken) || !jwtService.isVerificationTokenValid(verificationToken, student)){
                authService.sendEmailVerification(student, "students/sign-up");
                throw new RuntimeException("Invalid token");
            }
            student.setFullName(profileInfo.getFullName());
            userService.activateUser(student);
            student.setPassword(passwordEncoder.encode(profileInfo.getRawPassword()));
            student.setProfileImageUrl(profileInfo.getProfileImageUrl());
            studentRepository.save(student);
            return ResponseEntity.ok("Signup completed successfully");
        }
        else {
            return new ResponseEntity<>("ask you school manager to send you an invite", HttpStatus.CONFLICT);
        }


    }

    @Override
    public ResponseEntity<String> addStudentGroup(StudentGroupDto studentGroup) {
        GroupEntity group = groupRepository.findByGroupId(studentGroup.getGroupId()).orElse(null);
        if(group != null){
            StudentEntity student = addStudentToGroup(studentGroup.getEmail(), group);
        }
        return ResponseEntity.ok("Student added successfully");
    }
}
