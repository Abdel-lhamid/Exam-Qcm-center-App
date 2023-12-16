package ma.ensaf.Qcmexamcenterbackend.repositories;

import ma.ensaf.Qcmexamcenterbackend.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    Optional<StudentEntity> findByEmail(String email);

}
