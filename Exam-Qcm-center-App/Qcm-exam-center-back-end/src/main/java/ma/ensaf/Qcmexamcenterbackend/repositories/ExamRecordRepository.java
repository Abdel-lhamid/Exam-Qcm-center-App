package ma.ensaf.Qcmexamcenterbackend.repositories;

import ma.ensaf.Qcmexamcenterbackend.entities.ExamEntity;
import ma.ensaf.Qcmexamcenterbackend.entities.ExamRecordEntity;
import ma.ensaf.Qcmexamcenterbackend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRecordRepository extends JpaRepository<ExamRecordEntity, Long> {
    ExamRecordEntity findByExamRecordId(String examRecordId);

    List<ExamRecordEntity> findByStudent(UserEntity userEntity);

    List<ExamRecordEntity> findByExam(ExamEntity examEntity);

    ExamRecordEntity findByStudentAndExam(UserEntity userEntity, ExamEntity examEntity);


}
