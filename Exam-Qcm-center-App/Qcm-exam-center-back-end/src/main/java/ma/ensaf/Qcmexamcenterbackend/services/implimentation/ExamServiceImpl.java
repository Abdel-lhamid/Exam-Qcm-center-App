package ma.ensaf.Qcmexamcenterbackend.services.implimentation;

import ma.ensaf.Qcmexamcenterbackend.dtos.ExamDto;
import ma.ensaf.Qcmexamcenterbackend.entities.ExamEntity;
import ma.ensaf.Qcmexamcenterbackend.entities.UserEntity;
import ma.ensaf.Qcmexamcenterbackend.repositories.ExamRepository;
import ma.ensaf.Qcmexamcenterbackend.repositories.ModuleRepository;
import ma.ensaf.Qcmexamcenterbackend.repositories.UserRepository;
import ma.ensaf.Qcmexamcenterbackend.services.ExamService;
import ma.ensaf.Qcmexamcenterbackend.shared.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Utils util;

    @Override
    public ExamDto createExam(ExamDto examDto) {
        ExamEntity examEntity = modelMapper.map(examDto, ExamEntity.class);
        examEntity.setExamId(util.generateCustomId(32));
        ExamEntity storedExam = examRepository.save(examEntity);
        return modelMapper.map(storedExam, ExamDto.class);
    }

    @Override
    public ExamDto getExamById(String examId) {
        ExamEntity examEntity = examRepository.findByExamId(examId);
        if(examEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exam with ID " + examId + " not found");
        }
        return modelMapper.map(examEntity, ExamDto.class);
    }

    @Override
    public List<ExamDto> getAllExams() {
        List<ExamEntity> exams = examRepository.findAll();
        return exams.stream()
                .map(exam -> modelMapper.map(exam, ExamDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteExam(String examId) {
        ExamEntity examEntity = examRepository.findByExamId(examId);
        if(examEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exam with ID " + examId + " not found");
        }
        examRepository.delete(examEntity);
    }

    @Override
    public ExamDto updateExam(ExamDto examDto) {
        ExamEntity examEntity = examRepository.findByExamId(examDto.getExamId());
        if(examEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exam with ID " + examDto.getExamId() + " not found");
        }

        // Update the fields. You might want to adjust based on which fields are updatable.
        examEntity.setTitle(examDto.getTitle());
        examEntity.setDescription(examDto.getDescription());
        examEntity.setDuration(examDto.getDuration());

        ExamEntity updatedExam = examRepository.save(examEntity);
        return modelMapper.map(updatedExam, ExamDto.class);
    }

    @Override
    public List<ExamDto> getExamsByProfessor(String professorDto) {
        UserEntity professorEntity = userRepository.findByUserId(professorDto);
        List<ExamEntity> exams = examRepository.findByProfessor(professorEntity);

        return exams.stream()
                .map(exam -> modelMapper.map(exam, ExamDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ExamDto> getExamsByGroup(String groupId) {
        return Collections.emptyList();
        /*List<ModuleEntity> modules = moduleRepository.findByGroupId(groupId);
        List<ExamEntity> exams = examRepository.findByModule(modules);

        return exams.stream()
                .map(exam -> modelMapper.map(exam, ExamDto.class))
                .collect(Collectors.toList());  //return a list of examsDto*/
    }


}
