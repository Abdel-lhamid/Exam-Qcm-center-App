package ma.ensaf.Qcmexamcenterbackend.services.implimentation;

import ma.ensaf.Qcmexamcenterbackend.config.JwtService;
import ma.ensaf.Qcmexamcenterbackend.entities.AdminEntity;
import ma.ensaf.Qcmexamcenterbackend.repositories.AdminRepository;
import ma.ensaf.Qcmexamcenterbackend.services.AdminService;
import ma.ensaf.Qcmexamcenterbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    JwtService jwtService;

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;
    @Autowired
    AdminRepository adminRepository;


}
