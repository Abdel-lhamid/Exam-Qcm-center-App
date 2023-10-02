package ma.ensaf.Qcmexamcenterbackend.controllers;

import ma.ensaf.Qcmexamcenterbackend.dtos.QuestionDto;
import ma.ensaf.Qcmexamcenterbackend.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    // Create a new question
    @PostMapping
    public ResponseEntity<QuestionDto> createQuestion(@RequestBody QuestionDto questionDto) {
        QuestionDto createdQuestion = questionService.createQuestion(questionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
    }

    // Retrieve all questions for a specific exam
    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<QuestionDto>> getQuestionsForExam(@PathVariable String examId) {
        List<QuestionDto> questions = questionService.getAllQuestionsByExam(examId);
        return ResponseEntity.ok(questions);
    }

    // Retrieve a specific question by ID
    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionDto> getQuestionById(@PathVariable String questionId) {
        QuestionDto question = questionService.getQuestionById(questionId);
        return ResponseEntity.ok(question);
    }

    // Update a specific question
    @PutMapping
    public ResponseEntity<QuestionDto> updateQuestion(@RequestBody QuestionDto questionDto) {
        QuestionDto updatedQuestion = questionService.updateQuestion(questionDto);
        return ResponseEntity.ok(updatedQuestion);
    }

    // Delete a specific question
    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable String questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.noContent().build();
    }

}
