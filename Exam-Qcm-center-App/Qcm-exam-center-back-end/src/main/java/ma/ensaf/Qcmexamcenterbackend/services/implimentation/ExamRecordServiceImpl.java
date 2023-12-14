package ma.ensaf.Qcmexamcenterbackend.services.implimentation;

import ma.ensaf.Qcmexamcenterbackend.dtos.ExamRecordDto;
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






}
