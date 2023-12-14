package ma.ensaf.Qcmexamcenterbackend.entities;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "questions")
public class QuestionEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionId;

    private String text; // The question text.

    @Column(name = "images_url")
    private String imagesUrl;  // URL(s) for images related to the question.

    @Column(name = "point", nullable = false)
    private Integer point;  // The point/marks assigned to the question if answered correctly.

    @ManyToOne
    @JoinColumn(name = "exam_id", nullable = false)
    private ExamEntity exam;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OptionEntity> options;

}
