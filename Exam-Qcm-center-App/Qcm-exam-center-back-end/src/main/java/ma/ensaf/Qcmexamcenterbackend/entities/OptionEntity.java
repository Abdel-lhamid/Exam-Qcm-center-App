package ma.ensaf.Qcmexamcenterbackend.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "options", schema = "qcm_exam_db")
public class OptionEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String optionId;

    @Column(name="text")
    private String text;
    @Column(name = "image_url")
    private String imageUrl;  // URL for images related to the option.


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    @Column(name = "is_correct", nullable = false)
    private boolean isCorrect;

}
