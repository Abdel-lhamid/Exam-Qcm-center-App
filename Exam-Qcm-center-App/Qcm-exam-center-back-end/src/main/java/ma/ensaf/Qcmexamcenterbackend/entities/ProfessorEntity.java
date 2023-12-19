package ma.ensaf.Qcmexamcenterbackend.entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import ma.ensaf.Qcmexamcenterbackend.enums.UserRole;

import java.util.List;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "professors")
public class ProfessorEntity extends UserEntity{

    @OneToMany(mappedBy = "professor", cascade = CascadeType.PERSIST)
    private List<ModuleEntity> modules;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.PERSIST)
    private List<ExamEntity> examsCreated;

    {
        setUserRole(UserRole.PROFESSOR); // Set the default user role for professor
    }
}
