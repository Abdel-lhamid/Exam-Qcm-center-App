package ma.ensaf.Qcmexamcenterbackend.dtos.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensaf.Qcmexamcenterbackend.enums.UserRole;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    String fullName;
    String email;
    String password;
    String schoolId;
}
