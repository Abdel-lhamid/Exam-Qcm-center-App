package ma.ensaf.Qcmexamcenterbackend.controllers;


import ma.ensaf.Qcmexamcenterbackend.dtos.ModuleDto;
import ma.ensaf.Qcmexamcenterbackend.services.ModuleService;
import ma.ensaf.Qcmexamcenterbackend.services.implimentation.ModuleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/modules")
public class ModuleController {
    @Autowired
    ModuleService moduleService;


    //TODO: Create a Module and assign a teacher if exist, if not send invite to teacher
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/create")
    public ResponseEntity<String> createModule(ModuleDto moduleDto){
        return moduleService.createModule(moduleDto);
    }





}
