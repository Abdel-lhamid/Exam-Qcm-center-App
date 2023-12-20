package ma.ensaf.Qcmexamcenterbackend.services;

import ma.ensaf.Qcmexamcenterbackend.dtos.StudentGroupDto;
import ma.ensaf.Qcmexamcenterbackend.dtos.UserDto;
import ma.ensaf.Qcmexamcenterbackend.entities.GroupEntity;
import ma.ensaf.Qcmexamcenterbackend.entities.StudentEntity;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    StudentEntity addStudentToGroup(String email, GroupEntity group);

    StudentEntity getStudentByEmail(String email);

    ResponseEntity<String> completeSignup(String verificationToken, UserDto profileInfo);

    ResponseEntity<String> addStudentGroup(StudentGroupDto studentGroup);
}
