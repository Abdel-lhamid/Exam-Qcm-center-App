package ma.ensaf.Qcmexamcenterbackend.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "exam_records")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamRecordEntity implements Serializable {
    private static final long serialVersionUID = 6407689839461559517L;

    @Id
    @GeneratedValue
    private Long id;

    private String examRecordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    private ExamEntity exam;

    @Column(nullable = false)
    private double score;


    @CreationTimestamp
    private LocalDateTime assignedAt;

    private LocalDateTime submittedAt;

}
