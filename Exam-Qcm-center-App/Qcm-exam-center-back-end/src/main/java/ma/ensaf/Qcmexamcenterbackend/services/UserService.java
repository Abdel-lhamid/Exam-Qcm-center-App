package ma.ensaf.Qcmexamcenterbackend.services;

import ma.ensaf.Qcmexamcenterbackend.dtos.UserDto;
import ma.ensaf.Qcmexamcenterbackend.entities.UserEntity;
import ma.ensaf.Qcmexamcenterbackend.response.AuthenticationResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService{
    AuthenticationResponse createUser(UserDto userDto);
    UserEntity getUserByEmail(String email);
    UserDto updateUser(UserDto userDto);
    void deleteUser(String userId);
    List<UserDto> getAllUsers();
    List<UserDto> getAllStudents();

    AuthenticationResponse authenticate(String email, String password);
}
