package ma.ensaf.Qcmexamcenterbackend.services.implimentation;

import ma.ensaf.Qcmexamcenterbackend.dtos.ExamDto;
import ma.ensaf.Qcmexamcenterbackend.dtos.ExamRecordDto;
import ma.ensaf.Qcmexamcenterbackend.dtos.UserDto;
import ma.ensaf.Qcmexamcenterbackend.entities.ExamEntity;
import ma.ensaf.Qcmexamcenterbackend.entities.ExamRecordEntity;
import ma.ensaf.Qcmexamcenterbackend.entities.UserEntity;
import ma.ensaf.Qcmexamcenterbackend.repositories.ExamRecordRepository;
import ma.ensaf.Qcmexamcenterbackend.repositories.ExamRepository;
import ma.ensaf.Qcmexamcenterbackend.repositories.UserRepository;
import ma.ensaf.Qcmexamcenterbackend.services.ExamRecordService;
import ma.ensaf.Qcmexamcenterbackend.shared.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExamRecordServiceImpl implements ExamRecordService{

    @Autowired
    private ExamRecordRepository examRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    Utils utils;

    @Override
    public ExamRecordDto assignExamRecord(ExamRecordDto examRecordDto) {
        UserEntity student = userRepository.findByUserId(examRecordDto.getStudent().getUserId());
        ExamEntity exam = examRepository.findByExamId(examRecordDto.getExam().getExamId());

        if(checkExamRecordExists(student.getUserId(), exam.getExamId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Exam record already exists for this student and exam.");
        }

        ExamRecordEntity examRecordEntity = modelMapper.map(examRecordDto, ExamRecordEntity.class);
        examRecordEntity.setStudent(student);
        examRecordEntity.setExam(exam);

        examRecordEntity.setExamRecordId(utils.generateCustomId(32));
        ExamRecordEntity storedExamRecord = examRecordRepository.save(examRecordEntity);
        return modelMapper.map(storedExamRecord, ExamRecordDto.class);
    }

    @Override
    public ExamRecordDto submitExamRecord(ExamRecordDto examRecordDto) {
        ExamRecordEntity examRecordEntity = examRecordRepository.findByExamRecordId(examRecordDto.getExamRecordId());
        examRecordEntity.setScore(examRecordDto.getScore());
        examRecordEntity.setSubmittedAt(LocalDateTime.now());

        ExamRecordEntity updatedExamRecord = examRecordRepository.save(examRecordEntity);
        return modelMapper.map(updatedExamRecord, ExamRecordDto.class);
    }

    @Override
    public List<ExamRecordDto> getAllExamRecords() {
        List<ExamRecordEntity> examRecords = examRecordRepository.findAll();
        return examRecords.stream().map(record -> modelMapper.map(record, ExamRecordDto.class)).collect(Collectors.toList());
    }

    @Override
    public ExamRecordDto getExamRecordById(String examRecordId) {
        ExamRecordEntity examRecordEntity = examRecordRepository.findByExamRecordId(examRecordId);
        return modelMapper.map(examRecordEntity, ExamRecordDto.class);
    }

    @Override
    public boolean checkExamRecordExists(String studentId, String examId) {
        UserEntity student = userRepository.findByUserId(studentId);
        ExamEntity exam = examRepository.findByExamId(examId);
        ExamRecordEntity examRecord = examRecordRepository.findByStudentAndExam(student, exam);
        if (examRecord != null) {
            return true;
        }
        return false;
    }


}
