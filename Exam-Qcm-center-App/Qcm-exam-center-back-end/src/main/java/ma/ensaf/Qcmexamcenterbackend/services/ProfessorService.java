package ma.ensaf.Qcmexamcenterbackend.services;

import ma.ensaf.Qcmexamcenterbackend.entities.ProfessorEntity;
import ma.ensaf.Qcmexamcenterbackend.entities.SchoolEntity;

public interface ProfessorService {
    ProfessorEntity findByEmail(String email);

    ProfessorEntity createProfessor(String professorEmail, SchoolEntity school);
}
