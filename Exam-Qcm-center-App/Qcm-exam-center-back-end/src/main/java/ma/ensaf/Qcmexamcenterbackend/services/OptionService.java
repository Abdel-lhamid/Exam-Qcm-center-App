package ma.ensaf.Qcmexamcenterbackend.services;

import ma.ensaf.Qcmexamcenterbackend.dtos.OptionDto;

import java.util.List;

public interface OptionService {
    OptionDto createOption(OptionDto optionDto);

    OptionDto getOptionByOptionId(String optionId);

    List<OptionDto> getAllOptionsByQuestion(String questionId);

    OptionDto updateOption(String optionId, OptionDto optionDto);

    void deleteOption(String optionId);




}
