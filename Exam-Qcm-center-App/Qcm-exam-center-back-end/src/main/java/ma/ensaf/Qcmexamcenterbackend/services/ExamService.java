package ma.ensaf.Qcmexamcenterbackend.services;

import ma.ensaf.Qcmexamcenterbackend.dtos.ExamDto;
import ma.ensaf.Qcmexamcenterbackend.dtos.UserDto;
import ma.ensaf.Qcmexamcenterbackend.entities.ExamEntity;

import java.util.List;

public interface ExamService {
    ExamDto createExam(ExamDto examDto);
    ExamDto getExamById(String examId);
    List<ExamDto> getAllExams();
    void deleteExam(String examId);
    ExamDto updateExam(ExamDto examDto);

    List<ExamDto> getExamsByProfessor(String professorDto);

}
