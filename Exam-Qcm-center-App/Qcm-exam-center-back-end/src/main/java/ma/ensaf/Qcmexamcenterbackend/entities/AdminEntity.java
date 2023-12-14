package ma.ensaf.Qcmexamcenterbackend.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ma.ensaf.Qcmexamcenterbackend.enums.UserRole;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "admins")
public class AdminEntity extends UserEntity{

    {
        setUserRole(UserRole.ADMIN); // Set the default user role for admin
    }

}
