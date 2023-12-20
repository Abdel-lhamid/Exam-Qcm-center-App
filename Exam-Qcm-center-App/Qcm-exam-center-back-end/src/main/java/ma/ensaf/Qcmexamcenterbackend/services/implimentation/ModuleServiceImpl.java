package ma.ensaf.Qcmexamcenterbackend.services.implimentation;

import ma.ensaf.Qcmexamcenterbackend.dtos.ModuleDto;
import ma.ensaf.Qcmexamcenterbackend.entities.GroupEntity;
import ma.ensaf.Qcmexamcenterbackend.entities.ModuleEntity;
import ma.ensaf.Qcmexamcenterbackend.entities.ProfessorEntity;
import ma.ensaf.Qcmexamcenterbackend.repositories.ModuleRepository;
import ma.ensaf.Qcmexamcenterbackend.services.GroupService;
import ma.ensaf.Qcmexamcenterbackend.services.ModuleService;
import ma.ensaf.Qcmexamcenterbackend.services.ProfessorService;
import ma.ensaf.Qcmexamcenterbackend.services.SchoolService;
import ma.ensaf.Qcmexamcenterbackend.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    ProfessorService professorService;

    @Autowired
    SchoolService schoolService;
    @Autowired
    Utils utils;

    @Autowired
    GroupService groupService;

    @Override
    public ResponseEntity<String> createModule(ModuleDto moduleDto) {
        List <GroupEntity> groups = new ArrayList<>();
        for (String group : moduleDto.getGroupsId()) {
            GroupEntity groupEntity = groupService.findByGroupId(group);
            groups.add(groupEntity);
        }
        ModuleEntity module = ModuleEntity.builder()
                .moduleId(utils.generateCustomId(20))
                .name(moduleDto.getName())
                .school(schoolService.findSchoolBySchoolId(moduleDto.getSchoolId()))
                .groups(groups)
                .build();
        ModuleEntity moduleSaved = moduleRepository.save(module);
        ProfessorEntity professorExists = professorService.findByEmail(moduleDto.getProfessorEmail());
        if (professorExists != null) {
            moduleSaved.setProfessor(professorExists);
            moduleRepository.save(moduleSaved);
        }
        else {
            //create Professor and send Invitation, Done
            ProfessorEntity professorCreated = professorService.createProfessor(moduleDto.getProfessorEmail(), moduleSaved.getSchool());
            moduleSaved.setProfessor(professorCreated);
            moduleRepository.save(moduleSaved);
        }
        return ResponseEntity.ok("Module Created Successfully");
    }
}
