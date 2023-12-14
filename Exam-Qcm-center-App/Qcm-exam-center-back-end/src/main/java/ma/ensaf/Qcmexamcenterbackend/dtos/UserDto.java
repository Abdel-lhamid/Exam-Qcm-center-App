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
public class UserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 6407689839461559517L;

    @JsonIgnore
    private Long id;

    private String userId;

    private String fullName;

    private String email;

    private String profileImageUrl;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String verificationToken;

    private UserRole userRole;

    private List<ExamDto> examsCreated;
    private List<ExamRecordDto> examsTaken;



}
