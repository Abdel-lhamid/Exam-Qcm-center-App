package ma.ensaf.Qcmexamcenterbackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
@Builder
public class ModuleDto {

    @JsonIgnore
    private long id;

    private String moduleId;

    private String name;

    private String professorEmail;

    private List<String> groupsId;

    private String schoolId;

    private List<ExamDto> exams;
}
