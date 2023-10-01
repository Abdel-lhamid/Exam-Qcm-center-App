package ma.ensaf.Qcmexamcenterbackend.dtos;

import lombok.Data;

@Data
public class OptionDto {
    private Long id;

    private String optionId;

    private String text;

    private boolean isCorrect;

    private QuestionDto question;

}
