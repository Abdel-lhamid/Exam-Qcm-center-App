package ma.ensaf.Qcmexamcenterbackend.services;

import ma.ensaf.Qcmexamcenterbackend.dtos.GroupDto;
import ma.ensaf.Qcmexamcenterbackend.entities.GroupEntity;

public interface GroupService {
    void createGroup(GroupDto group);
    GroupEntity findByGroupId(String groupId);
}
