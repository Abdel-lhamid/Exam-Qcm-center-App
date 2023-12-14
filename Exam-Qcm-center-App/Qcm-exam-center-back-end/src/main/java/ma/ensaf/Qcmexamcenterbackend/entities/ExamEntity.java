package ma.ensaf.Qcmexamcenterbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exams")
public class ExamEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "exam_id", unique = true, nullable = false)
    private String examId;

    private String title;  // The title or name of the exam.

    private String description;  // The description of the exam.

    @CreationTimestamp
    private LocalDateTime createdAt;  // The date of creation of the exam.

    @Column(name = "duration", nullable = false)
    private Integer duration;  // The duration of the exam in minutes.
    @Column(name = "deadline")
    private LocalDateTime deadline;
    @Column(name = "passing_mark")
    private Integer passingMark;
    @Column(name = "max_mark")
    private Integer maxMark;
    @Column(name = "visible_to_students")
    private Boolean visibleToStudents;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private UserEntity professor;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuestionEntity> questions;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ExamRecordEntity> examRecords;

    //the group for this exam
    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private ModuleEntity module;
}