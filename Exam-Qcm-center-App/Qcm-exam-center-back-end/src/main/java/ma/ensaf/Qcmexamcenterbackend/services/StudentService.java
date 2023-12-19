package ma.ensaf.Qcmexamcenterbackend.services;

import ma.ensaf.Qcmexamcenterbackend.entities.GroupEntity;
import ma.ensaf.Qcmexamcenterbackend.entities.StudentEntity;

public interface StudentService {
    StudentEntity addStudentToGroup(String email, GroupEntity group);

    StudentEntity getStudentByEmail(String email);
}
