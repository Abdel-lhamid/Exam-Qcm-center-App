package ma.ensaf.Qcmexamcenterbackend.services.implimentation;

import ma.ensaf.Qcmexamcenterbackend.entities.ProfessorEntity;
import ma.ensaf.Qcmexamcenterbackend.entities.SchoolEntity;
import ma.ensaf.Qcmexamcenterbackend.repositories.ProfessorRepository;
import ma.ensaf.Qcmexamcenterbackend.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    AuthService authService;
    @Override
    public ProfessorEntity findByEmail(String email) {
        return professorRepository.findByEmail(email).orElse(null);
    }


    @Override
    public ProfessorEntity createProfessor(String professorEmail, SchoolEntity school) {
        ProfessorEntity professor = findByEmail(professorEmail);
        if(professor == null) {
            ProfessorEntity professorEntity = ProfessorEntity.builder()
                    .email(professorEmail)
                    .school(school)
                    .build();
            ProfessorEntity professorSaved = professorRepository.save(professorEntity);
            //send email invite
            try {
                authService.sendEmailVerification(professorSaved, "professors/complete-sign-up");
            }catch (Exception e){
                throw new RuntimeException(e.getMessage(),e.getCause());
            }
            return professorSaved;
        }
        return professor;
    }
}
