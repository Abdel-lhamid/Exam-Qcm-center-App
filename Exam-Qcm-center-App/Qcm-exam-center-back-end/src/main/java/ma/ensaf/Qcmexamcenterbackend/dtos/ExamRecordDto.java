package ma.ensaf.Qcmexamcenterbackend.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExamRecordDto {

    private Long id;

    private String examRecordId;

    private UserDto student;

    private ExamDto exam;

    private double score;

    private LocalDateTime assignedAt;

    private LocalDateTime submittedAt;






}
