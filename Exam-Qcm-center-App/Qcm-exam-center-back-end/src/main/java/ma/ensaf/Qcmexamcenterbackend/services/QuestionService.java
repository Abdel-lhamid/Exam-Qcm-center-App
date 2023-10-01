package ma.ensaf.Qcmexamcenterbackend.services;

import ma.ensaf.Qcmexamcenterbackend.dtos.QuestionDto;

import java.util.List;

public interface QuestionService {
    QuestionDto createQuestion(QuestionDto questionDto);
    QuestionDto getQuestionById(String questionId);
    List<QuestionDto> getAllQuestionsByExam(String examId);

    QuestionDto updateQuestion(QuestionDto questionDto);

    void deleteQuestion(String questionId);

}
