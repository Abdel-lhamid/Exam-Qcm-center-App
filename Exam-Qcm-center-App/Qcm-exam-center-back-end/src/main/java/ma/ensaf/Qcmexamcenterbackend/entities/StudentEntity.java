package ma.ensaf.Qcmexamcenterbackend.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ma.ensaf.Qcmexamcenterbackend.enums.UserRole;

import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "students")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity extends UserEntity{

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private GroupEntity group;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<ExamRecordEntity> examsTaken;

    {
        setUserRole(UserRole.STUDENT); // Set the default user role for student
    }

}
