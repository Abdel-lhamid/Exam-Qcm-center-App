package ma.ensaf.Qcmexamcenterbackend.services.implimentation;

import ma.ensaf.Qcmexamcenterbackend.entities.SchoolEntity;
import ma.ensaf.Qcmexamcenterbackend.repositories.SchoolRepository;
import ma.ensaf.Qcmexamcenterbackend.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class schoolServiceImpl implements SchoolService {
    @Autowired
    SchoolRepository schoolRepository;

    @Override
    public SchoolEntity findSchoolBySchoolId(String schoolId) {
        SchoolEntity school = schoolRepository.findBySchoolId(schoolId).orElse(null);
        if (school == null) {
            throw new RuntimeException("School not found");
        }
        return school;
    }
}
