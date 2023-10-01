package ma.ensaf.Qcmexamcenterbackend.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "options", schema = "qcm_exam_db")
public class OptionEntity implements Serializable {
    private static final long serialVersionUID = 6407689839461559517L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String optionId;

    @Column(nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    @Column(nullable = false)
    private boolean isCorrect;

}
