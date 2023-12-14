package ma.ensaf.Qcmexamcenterbackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ExamDto {
    @JsonIgnore
    private Long id;

    private String examId;

    private String title;

    private String description;

    private int duration;

    private UserDto professor;

    private List<QuestionDto> questions;

    private List<ExamRecordDto> examRecords;


}
