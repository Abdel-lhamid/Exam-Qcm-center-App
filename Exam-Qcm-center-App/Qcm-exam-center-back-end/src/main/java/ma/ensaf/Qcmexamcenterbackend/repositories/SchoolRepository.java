package ma.ensaf.Qcmexamcenterbackend.repositories;

import ma.ensaf.Qcmexamcenterbackend.entities.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface SchoolRepository extends JpaRepository<SchoolEntity, Long> {
    Optional<SchoolEntity> findByName(String schoolName);
    Optional<SchoolEntity> findBySchoolId(String schoolId);
}
