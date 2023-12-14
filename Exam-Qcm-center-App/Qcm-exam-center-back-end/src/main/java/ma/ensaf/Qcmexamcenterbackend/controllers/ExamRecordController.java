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



}
