package ma.ensaf.Qcmexamcenterbackend.dtos.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminSignUpRequest {

    private String fullName;
    private String email;
    private String password;
    private String schoolName;
}
