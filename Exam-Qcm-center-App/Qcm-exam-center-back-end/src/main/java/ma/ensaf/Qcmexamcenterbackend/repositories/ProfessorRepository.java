package ma.ensaf.Qcmexamcenterbackend.repositories;

import ma.ensaf.Qcmexamcenterbackend.entities.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
    Optional<ProfessorEntity> findByEmail(String email);
}
