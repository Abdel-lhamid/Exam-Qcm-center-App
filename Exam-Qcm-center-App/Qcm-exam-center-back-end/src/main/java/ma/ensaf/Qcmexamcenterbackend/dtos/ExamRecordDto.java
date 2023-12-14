package ma.ensaf.Qcmexamcenterbackend.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder

public class ExamRecordDto {

    private Long id;

    private String examRecordId;

    private UserDto student;

    private ExamDto exam;

    private double score;

    private LocalDateTime submittedAt;






}
