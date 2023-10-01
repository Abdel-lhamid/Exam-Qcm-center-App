package ma.ensaf.Qcmexamcenterbackend.dtos;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDto {

    private Long id;

    private String questionId;

    private String text;

    private ExamDto exam;

    private List<OptionDto> options;
}
