package ma.ensaf.Qcmexamcenterbackend.services;

import ma.ensaf.Qcmexamcenterbackend.entities.SchoolEntity;

public interface SchoolService {
    SchoolEntity findSchoolBySchoolId(String schoolId);
}
