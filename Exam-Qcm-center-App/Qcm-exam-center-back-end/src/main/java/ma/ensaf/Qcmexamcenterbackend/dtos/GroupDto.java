package ma.ensaf.Qcmexamcenterbackend.dtos;



import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GroupDto {

    private String groupId;
    private String name;
    private List<String> studentsEmail;
    private List<String> modulesId;
    private String schoolId;
}
