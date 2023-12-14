package ma.ensaf.Qcmexamcenterbackend.services.implimentation;

import lombok.RequiredArgsConstructor;
import ma.ensaf.Qcmexamcenterbackend.config.JwtService;
import ma.ensaf.Qcmexamcenterbackend.dtos.AuthRequest;
import ma.ensaf.Qcmexamcenterbackend.dtos.SignUpRequest;
import ma.ensaf.Qcmexamcenterbackend.entities.UserEntity;
import ma.ensaf.Qcmexamcenterbackend.enums.UserRole;
import ma.ensaf.Qcmexamcenterbackend.exceptions.CustomAuthException;
import ma.ensaf.Qcmexamcenterbackend.repositories.UserRepository;
import ma.ensaf.Qcmexamcenterbackend.response.AuthenticationResponse;
import ma.ensaf.Qcmexamcenterbackend.shared.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Utils util;

    @Autowired
    private JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    //Registration only for admin
    /*public AuthenticationResponse createUser(SignUpRequest request) {
        if (!userRepository.findByUserRole(UserRole.ADMIN).isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Admin already exists");
        }

        if (userRepository.findByEmail(request.getEmail()).orElse(null) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "UserEntity already exists with this email");
        }
        // Encrypt the password
        String encryptedPassword = bCryptPasswordEncoder.encode(request.getPassword());

        UserEntity userEntity = modelMapper.map(request, UserEntity.class);
        userEntity.setUserRole(UserRole.ADMIN);
        userEntity.setPassword(encryptedPassword);
        //set userId
        userEntity.setUserId(util.generateCustomId(32));

        UserEntity storedUserEntity = userRepository.save(userEntity);
        var jwtToken = jwtService.generateToken(storedUserEntity);
        var refreshToken = jwtService.generateRefreshToken(storedUserEntity);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }*/
    public AuthenticationResponse authenticate(AuthRequest authRequest) throws AuthenticationException {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword()
                    )
            );
            var user = userRepository.findByEmail(authRequest.getEmail()).orElse(null);
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);

            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        } catch (AuthenticationException e) {
            // Handle authentication failure and return a custom message
            throw new CustomAuthException("Authentication failed: Email or password is incorrect");
        }

    }
}
