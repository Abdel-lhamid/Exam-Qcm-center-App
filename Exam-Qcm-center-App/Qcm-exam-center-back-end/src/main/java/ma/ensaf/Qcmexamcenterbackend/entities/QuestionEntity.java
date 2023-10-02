package ma.ensaf.Qcmexamcenterbackend.entities;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "questions", schema = "qcm_exam_db")
public class QuestionEntity implements Serializable {
    private static final long serialVersionUID = 6407689839461559517L;

    @Id
    @GeneratedValue
    private Long id;

    private String questionId;

    private String text; // The question text.

    @ManyToOne
    @JoinColumn(name = "exam_id", nullable = false)
    private ExamEntity exam;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OptionEntity> options;

}
