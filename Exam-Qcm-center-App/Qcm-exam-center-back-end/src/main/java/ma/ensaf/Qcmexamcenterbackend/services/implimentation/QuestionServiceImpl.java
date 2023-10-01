package ma.ensaf.Qcmexamcenterbackend.services.implimentation;

import ma.ensaf.Qcmexamcenterbackend.dtos.QuestionDto;
import ma.ensaf.Qcmexamcenterbackend.entities.ExamEntity;
import ma.ensaf.Qcmexamcenterbackend.entities.QuestionEntity;
import ma.ensaf.Qcmexamcenterbackend.repositories.ExamRepository;
import ma.ensaf.Qcmexamcenterbackend.repositories.QuestionRepository;
import ma.ensaf.Qcmexamcenterbackend.services.QuestionService;
import ma.ensaf.Qcmexamcenterbackend.shared.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    Utils utils;


    @Override
    public QuestionDto createQuestion(QuestionDto questionDto) {
        QuestionEntity questionEntity = modelMapper.map(questionDto, QuestionEntity.class);
        questionEntity.setQuestionId(utils.generateCustomId(32));
        QuestionEntity storedQuestion = questionRepository.save(questionEntity);
        return modelMapper.map(storedQuestion, QuestionDto.class);
    }

    @Override
    public QuestionDto getQuestionById(String questionId) {
        QuestionEntity questionEntity = questionRepository.findByQuestionId(questionId);
        if(questionEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question with ID " + questionId + " not found");
        }
        return modelMapper.map(questionEntity, QuestionDto.class);
    }

    @Override
    public List<QuestionDto> getAllQuestionsByExam(String examId) {
        ExamEntity examEntity = examRepository.findByExamId(examId);
        if(examEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exam with ID " + examId + " not found");
        }
        List<QuestionEntity> questions = questionRepository.findByExam(examEntity);
        return questions.stream()
                .map(question -> modelMapper.map(question, QuestionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public QuestionDto updateQuestion(QuestionDto questionDto) {
        QuestionEntity existingQuestion = questionRepository.findByQuestionId(questionDto.getQuestionId());
        if(existingQuestion == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question with ID " + questionDto.getQuestionId() + " not found");
        }
        existingQuestion.setText(questionDto.getText());
        //... and other properties you want to update

        QuestionEntity updatedQuestion = questionRepository.save(existingQuestion);
        return modelMapper.map(updatedQuestion, QuestionDto.class);
    }

    @Override
    public void deleteQuestion(String questionId) {
        QuestionEntity questionEntity = questionRepository.findByQuestionId(questionId);
        if(questionEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question with ID " + questionId + " not found");
        }
        questionRepository.delete(questionEntity);

    }
}
