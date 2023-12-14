package ma.ensaf.Qcmexamcenterbackend.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ma.ensaf.Qcmexamcenterbackend.enums.UserRole;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "admins")
public class AdminEntity extends UserEntity{

    public AdminEntity() {
        setUserRole(UserRole.ADMIN); // Set the default user role for admin
    }

}
