package ma.ensaf.Qcmexamcenterbackend.controllers;


import ma.ensaf.Qcmexamcenterbackend.dtos.ExamDto;
import ma.ensaf.Qcmexamcenterbackend.dtos.UserDto;
import ma.ensaf.Qcmexamcenterbackend.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping
    public ResponseEntity<List<ExamDto>> getAllExams() {
        List<ExamDto> exams = examService.getAllExams();
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @GetMapping("/{examId}")
    public ResponseEntity<ExamDto> getExamById(@PathVariable String examId) {
        ExamDto exam = examService.getExamById(examId);
        return new ResponseEntity<>(exam, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExamDto> createExam(@RequestBody ExamDto examDto) {
        ExamDto createdExam = examService.createExam(examDto);
        return new ResponseEntity<>(createdExam, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ExamDto> updateExam(@RequestBody ExamDto examDto) {
        ExamDto updatedExam = examService.updateExam(examDto);
        return new ResponseEntity<>(updatedExam, HttpStatus.OK);
    }

    @DeleteMapping("/{examId}")
    public ResponseEntity<Void> deleteExam(@PathVariable String examId) {
        examService.deleteExam(examId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/byProfessor/{professorId}")
    public ResponseEntity<List<ExamDto>> getExamsByProfessor(@PathVariable String professorId) {
        List<ExamDto> examsDto = examService.getExamsByProfessor(professorId);
        return ResponseEntity.ok(examsDto);
    }


}
