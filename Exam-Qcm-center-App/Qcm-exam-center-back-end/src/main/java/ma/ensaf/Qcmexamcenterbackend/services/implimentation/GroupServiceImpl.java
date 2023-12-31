package ma.ensaf.Qcmexamcenterbackend.services.implimentation;

import ma.ensaf.Qcmexamcenterbackend.dtos.GroupDto;
import ma.ensaf.Qcmexamcenterbackend.entities.GroupEntity;
import ma.ensaf.Qcmexamcenterbackend.entities.StudentEntity;
import ma.ensaf.Qcmexamcenterbackend.repositories.GroupRepository;
import ma.ensaf.Qcmexamcenterbackend.services.GroupService;
import ma.ensaf.Qcmexamcenterbackend.services.SchoolService;
import ma.ensaf.Qcmexamcenterbackend.services.StudentService;
import ma.ensaf.Qcmexamcenterbackend.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    Utils utils;
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    SchoolService schoolService;

    @Autowired
    StudentService studentService;

    @Override
    public void createGroup(GroupDto group) {

        GroupEntity groupEntity = GroupEntity.builder()
                .groupId(utils.generateCustomId(11))
                .name(group.getName())
                .school(schoolService.findSchoolBySchoolId(group.getSchoolId()))
                .build();
        GroupEntity savedGroup = groupRepository.save(groupEntity);

        List<StudentEntity> students = new ArrayList<>();
        // check if students exists, if not send email invites
        for (String email : group.getStudentsEmail()) {
            //check if user exists in students if so add to students list
            StudentEntity student = studentService.addStudentToGroup(email,savedGroup);
            students.add(student);
        }
        savedGroup.setStudents(students);

        savedGroup = groupRepository.save(groupEntity);

    }

    @Override
    public GroupEntity findByGroupId(String groupId) {
        GroupEntity group = groupRepository.findByGroupId(groupId).orElse(null);
        if(group == null)
        {
            throw new RuntimeException("Group not found");
        }
        return group;
    }


}
