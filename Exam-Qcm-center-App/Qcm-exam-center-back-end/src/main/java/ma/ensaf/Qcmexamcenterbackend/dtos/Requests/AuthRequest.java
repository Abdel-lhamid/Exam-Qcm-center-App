package ma.ensaf.Qcmexamcenterbackend.dtos.Requests;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    private String email;
    private String password;


}
