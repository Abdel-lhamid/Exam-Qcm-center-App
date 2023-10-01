package ma.ensaf.Qcmexamcenterbackend.repositories;

import ma.ensaf.Qcmexamcenterbackend.entities.OptionEntity;
import ma.ensaf.Qcmexamcenterbackend.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<OptionEntity, Long> {

    OptionEntity findByOptionId(String optionId);

    List<OptionEntity> findByQuestion(QuestionEntity questionEntity);
}
