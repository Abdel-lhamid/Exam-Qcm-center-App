package ma.ensaf.Qcmexamcenterbackend.controllers;


import ma.ensaf.Qcmexamcenterbackend.dtos.ExamRecordDto;
import ma.ensaf.Qcmexamcenterbackend.dtos.QuestionDto;
import ma.ensaf.Qcmexamcenterbackend.services.ExamRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam-records")
public class ExamRecordController {
    @Autowired
    private ExamRecordService examRecordService;

    // Assign an exam record to a student
    @PostMapping("/assign")
    public ResponseEntity<ExamRecordDto> assignExamRecord(@RequestBody ExamRecordDto examRecordDto) {
        ExamRecordDto assignedExamRecord = examRecordService.assignExamRecord(examRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(assignedExamRecord);
    }

    // Start an exam, get examRecord by id
    @PostMapping("/exam/{examRecordId}/start")
    public ResponseEntity<ExamRecordDto> startOrFetchQuestionsForExam(@PathVariable String examRecordId) {
        ExamRecordDto  examRecordDto= examRecordService.getExamRecordById(examRecordId);
        return ResponseEntity.ok(examRecordDto);
    }

    // Submit answers for an exam and record the results
    @PostMapping("/submit")
    public ResponseEntity<ExamRecordDto> submitExamRecord(@RequestBody ExamRecordDto examRecordDto) {
        ExamRecordDto submittedExamRecord = examRecordService.submitExamRecord(examRecordDto);
        return ResponseEntity.ok(submittedExamRecord);
    }

    // Retrieve records for a specific exam (like a leaderboard)
    @GetMapping("/exam/{examId}/records")
    public ResponseEntity<List<ExamRecordDto>> getRecordsForExam(@PathVariable String examId) {
        List<ExamRecordDto> examRecords = examRecordService.getAllExamRecordsForExam(examId);
        return ResponseEntity.ok(examRecords);
    }

    // Retrieve all exam records for a specific student/user
    @GetMapping("/users/{userId}/records")
    public ResponseEntity<List<ExamRecordDto>> getRecordsForStudent(@PathVariable String userId) {
        List<ExamRecordDto> examRecords = examRecordService.getRecordsForStudent(userId);
        return ResponseEntity.ok(examRecords);
    }

    // Retrieve specific details of an exam record by its ID
    @GetMapping("/{recordId}")
    public ResponseEntity<ExamRecordDto> getExamRecordById(@PathVariable String recordId) {
        ExamRecordDto examRecord = examRecordService.getExamRecordById(recordId);
        return ResponseEntity.ok(examRecord);
    }


}
