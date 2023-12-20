package ma.ensaf.Qcmexamcenterbackend.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentGroupDto {
    private String email;
    private String groupId;
}
