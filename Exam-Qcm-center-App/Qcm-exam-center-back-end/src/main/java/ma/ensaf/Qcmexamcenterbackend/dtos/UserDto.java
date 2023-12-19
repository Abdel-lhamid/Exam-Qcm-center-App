package ma.ensaf.Qcmexamcenterbackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ma.ensaf.Qcmexamcenterbackend.enums.UserRole;
import ma.ensaf.Qcmexamcenterbackend.response.UserResponse;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDto {


    private String userId;

    private String fullName;

    private String email;

    private String profileImageUrl;

    private String rawPassword;

    @JsonIgnore
    private String verificationToken;




}
