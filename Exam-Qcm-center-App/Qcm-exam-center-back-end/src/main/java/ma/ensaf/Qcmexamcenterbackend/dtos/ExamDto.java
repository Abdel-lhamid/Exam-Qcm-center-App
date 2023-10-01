package ma.ensaf.Qcmexamcenterbackend.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ExamDto {
    private Long id;

    private String examId;

    private String title;

    private String description;

    private int duration;

    private UserDto professor;

    private List<QuestionDto> questions;

    private List<ExamRecordDto> examRecords;


}
