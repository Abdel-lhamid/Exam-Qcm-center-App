package ma.ensaf.Qcmexamcenterbackend.services;

import ma.ensaf.Qcmexamcenterbackend.dtos.ExamRecordDto;

import java.util.List;

public interface ExamRecordService {

    ExamRecordDto assignExamRecord(ExamRecordDto examRecord);
    ExamRecordDto submitExamRecord(ExamRecordDto examRecordDto);
    List<ExamRecordDto> getAllExamRecords();
    ExamRecordDto getExamRecordById(String examRecordId);
    boolean checkExamRecordExists(String studentId, String examId);

}
