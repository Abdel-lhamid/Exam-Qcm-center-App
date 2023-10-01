package ma.ensaf.Qcmexamcenterbackend.dtos;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ma.ensaf.Qcmexamcenterbackend.response.UserResponse;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDto implements Serializable {
    private static final long serialVersionUID = 6407689839461559517L;

    private Long id;

    @JsonView(value = {UserResponse.class})
    private String userId;

    @JsonView(value = {UserResponse.class})
    private String fullName;

    @JsonView(value = {UserResponse.class})
    private String email;

    @JsonView(value = {UserResponse.class})
    private String profileImageUrl;

    private String password;

    private String verificationToken;

    @JsonView(value = {UserResponse.class})
    private Enum UserRole;

    @JsonView(value = {UserResponse.class})
    private List<ExamDto> exams;
    @JsonView(value = {UserResponse.class})
    private List<ExamRecordDto> examsTaken;






}
