package ma.ensaf.Qcmexamcenterbackend.services.implimentation;

import ma.ensaf.Qcmexamcenterbackend.entities.GroupEntity;
import ma.ensaf.Qcmexamcenterbackend.entities.StudentEntity;
import ma.ensaf.Qcmexamcenterbackend.repositories.StudentRepository;
import ma.ensaf.Qcmexamcenterbackend.services.StudentService;
import ma.ensaf.Qcmexamcenterbackend.services.UserService;
import ma.ensaf.Qcmexamcenterbackend.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    UserService userService;

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
                    .userId(utils.generateCustomId(11))
                    .group(savedGroup)
                    .isActive(false)
                    .school(savedGroup.getSchool())
                    .build();

            return studentRepository.save(student);
            //TODO: send email invite to student




        }

    }
}
