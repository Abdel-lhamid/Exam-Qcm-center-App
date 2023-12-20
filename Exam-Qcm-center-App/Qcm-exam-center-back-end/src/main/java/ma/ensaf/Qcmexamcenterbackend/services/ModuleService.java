package ma.ensaf.Qcmexamcenterbackend.services;

import ma.ensaf.Qcmexamcenterbackend.dtos.ModuleDto;
import org.springframework.http.ResponseEntity;

public interface ModuleService {

    ResponseEntity<String>createModule(ModuleDto moduleDto);
}
