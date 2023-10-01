package ma.ensaf.Qcmexamcenterbackend.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "exams", schema = "qcm_exam_db")
public class ExamEntity implements Serializable {
    private static final long serialVersionUID = 6407689839461559517L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "exam_id", unique = true, nullable = false)
    private String examId;

    private String title;  // The title or name of the exam.

    private String description;  // The description of the exam.

    @CreationTimestamp
    private LocalDateTime createdAt;  // The date of creation of the exam.
    @Column(name = "duration", nullable = false)
    private int duration;  // The duration of the exam in minutes.

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity professor;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuestionEntity> questions;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ExamRecordEntity> examRecords;

}
