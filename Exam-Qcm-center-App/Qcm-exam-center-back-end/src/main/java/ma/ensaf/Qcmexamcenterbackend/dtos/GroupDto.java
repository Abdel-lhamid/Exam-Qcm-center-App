package ma.ensaf.Qcmexamcenterbackend.dtos;



import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class GroupDto {

    private Long id;

    private String name;

    private List<UserDto> students;
    private List<ModuleDto> modules;
}
