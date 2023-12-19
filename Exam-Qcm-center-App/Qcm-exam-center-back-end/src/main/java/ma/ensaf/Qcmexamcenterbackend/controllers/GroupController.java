package ma.ensaf.Qcmexamcenterbackend.controllers;

import ma.ensaf.Qcmexamcenterbackend.dtos.GroupDto;
import ma.ensaf.Qcmexamcenterbackend.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    GroupService groupService;

    // create Crud api for Group Entity
        // create group api
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/example")
    public String exampleMethod(Authentication authentication) {
        // Log or inspect the authentication object
        System.out.println(authentication);
        return ("admin authorization working");
        // Your method logic...
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<String> createGroup(@RequestBody GroupDto group){
        groupService.createGroup(group);
        return ResponseEntity.ok("group created");


    }










}
