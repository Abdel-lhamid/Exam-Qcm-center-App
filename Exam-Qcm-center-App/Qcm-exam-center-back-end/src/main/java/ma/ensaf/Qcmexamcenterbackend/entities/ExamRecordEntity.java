package ma.ensaf.Qcmexamcenterbackend.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "exam_records")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "exam_record_id", unique = true, nullable = false)
    private String examRecordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private UserEntity student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    private ExamEntity exam;

    @Column(nullable = false)
    private Integer score;

    @Column(name = "initiated_at", updatable = false)
    private LocalDateTime initiatedAt;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;
}
