package ma.ensaf.Qcmexamcenterbackend.controllers;

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
}
