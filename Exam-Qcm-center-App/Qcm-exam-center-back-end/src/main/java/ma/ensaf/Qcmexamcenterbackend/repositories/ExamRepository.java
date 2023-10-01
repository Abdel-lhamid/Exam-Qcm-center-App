package ma.ensaf.Qcmexamcenterbackend.repositories;

import ma.ensaf.Qcmexamcenterbackend.entities.ExamEntity;
import ma.ensaf.Qcmexamcenterbackend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Long> {
    ExamEntity findByExamId(String examId);
    List<ExamEntity> findByProfessor(UserEntity professor);
}
