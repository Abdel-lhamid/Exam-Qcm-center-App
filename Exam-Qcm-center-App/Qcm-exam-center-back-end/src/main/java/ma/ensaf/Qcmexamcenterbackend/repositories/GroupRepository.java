package ma.ensaf.Qcmexamcenterbackend.repositories;

import ma.ensaf.Qcmexamcenterbackend.entities.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    Optional<GroupEntity> findByGroupId(String groupId);

}
