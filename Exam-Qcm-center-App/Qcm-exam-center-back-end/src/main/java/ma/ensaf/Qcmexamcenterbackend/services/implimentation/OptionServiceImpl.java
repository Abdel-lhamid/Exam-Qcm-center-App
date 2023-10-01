package ma.ensaf.Qcmexamcenterbackend.services.implimentation;


import ma.ensaf.Qcmexamcenterbackend.dtos.OptionDto;
import ma.ensaf.Qcmexamcenterbackend.entities.OptionEntity;
import ma.ensaf.Qcmexamcenterbackend.entities.QuestionEntity;
import ma.ensaf.Qcmexamcenterbackend.repositories.OptionRepository;
import ma.ensaf.Qcmexamcenterbackend.repositories.QuestionRepository;
import ma.ensaf.Qcmexamcenterbackend.services.OptionService;

import ma.ensaf.Qcmexamcenterbackend.shared.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OptionServiceImpl implements OptionService {

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Utils util;

    @Override
    public OptionDto createOption(OptionDto optionDto) {
        OptionEntity optionEntity = modelMapper.map(optionDto, OptionEntity.class);
        optionEntity.setOptionId(util.generateCustomId(32));
        OptionEntity storedOption = optionRepository.save(optionEntity);
        return modelMapper.map(storedOption, OptionDto.class);
    }

    @Override
    public OptionDto getOptionByOptionId(String optionId) {
        OptionEntity optionEntity = optionRepository.findByOptionId(optionId);
        if(optionEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Option with ID " + optionId + " not found");
        }
        return modelMapper.map(optionEntity, OptionDto.class);
    }

    @Override
    public List<OptionDto> getAllOptionsByQuestion(String questionId) {
        QuestionEntity questionEntity = questionRepository.findByQuestionId(questionId);
        if(questionEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question with ID " + questionId + " not found");
        }
        List<OptionEntity> options = optionRepository.findByQuestion(questionEntity);
        return options.stream()
                .map(option -> modelMapper.map(option, OptionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OptionDto updateOption(String optionId, OptionDto optionDto) {
        OptionEntity existingOption = optionRepository.findByOptionId(optionId);
        if(existingOption == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Option with ID " + optionId + " not found");
        }
        existingOption.setText(optionDto.getText());
        //... and other properties you want to update

        OptionEntity updatedOption = optionRepository.save(existingOption);
        return modelMapper.map(updatedOption, OptionDto.class);
    }

    @Override
    public void deleteOption(String optionId) {
        OptionEntity optionEntity = optionRepository.findByOptionId(optionId);
        if(optionEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Option with ID " + optionId + " not found");
        }
        optionRepository.delete(optionEntity);
    }


}
