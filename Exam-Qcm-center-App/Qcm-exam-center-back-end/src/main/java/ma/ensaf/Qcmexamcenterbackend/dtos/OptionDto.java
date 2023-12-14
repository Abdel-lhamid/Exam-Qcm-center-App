package ma.ensaf.Qcmexamcenterbackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class OptionDto {
    @JsonIgnore

    private Long id;

    private String optionId;

    private String text;

    private boolean isCorrect;

    private QuestionDto question;

}
