package ma.ensaf.Qcmexamcenterbackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class QuestionDto {

    @JsonIgnore
    private Long id;

    private String questionId;

    private String text;

    private ExamDto exam;

    private List<OptionDto> options;
}
