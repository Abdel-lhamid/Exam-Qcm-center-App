package ma.ensaf.Qcmexamcenterbackend.controllers;

import ma.ensaf.Qcmexamcenterbackend.dtos.StudentGroupDto;
import ma.ensaf.Qcmexamcenterbackend.dtos.UserDto;
import ma.ensaf.Qcmexamcenterbackend.entities.StudentEntity;
import ma.ensaf.Qcmexamcenterbackend.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    //get student by email
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getStudentByEmail/{email}")
    public ResponseEntity<String> getStudentByEmail(@PathVariable String email){
        StudentEntity student = studentService.getStudentByEmail(email);
        return (student != null) ? ResponseEntity.ok(student.getFullName()) : ResponseEntity.notFound().build();
    }

    //complete signup

    @PostMapping("/complete-sign-up")
    public ResponseEntity<String> completeSignup(@RequestParam String verificationToken, @RequestBody UserDto profileInfo){
        return studentService.completeSignup(verificationToken, profileInfo);
    }

    //DONE: Create student add to group already created and send email invite
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add-student")
    public ResponseEntity<String> addStudentGroup(@RequestBody StudentGroupDto studentGroup){
        return studentService.addStudentGroup(studentGroup);
    }
    // Create student or add list of student to groups
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add-students")
    @ResponseBody()
    public ResponseEntity<String> addStudentGroups(@RequestBody StudentGroupDto[] studentGroups){
        for (StudentGroupDto studentGroup : studentGroups) {
            studentService.addStudentGroup(studentGroup);
        };
        return ResponseEntity.ok("Students added successfully");
    }



}
