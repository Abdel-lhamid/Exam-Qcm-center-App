package ma.ensaf.Qcmexamcenterbackend.services.implimentation;

import lombok.RequiredArgsConstructor;
import ma.ensaf.Qcmexamcenterbackend.dtos.UserDto;
import ma.ensaf.Qcmexamcenterbackend.entities.UserEntity;
import ma.ensaf.Qcmexamcenterbackend.enums.UserRole;
import ma.ensaf.Qcmexamcenterbackend.repositories.UserRepository;
import ma.ensaf.Qcmexamcenterbackend.services.UserService;
import ma.ensaf.Qcmexamcenterbackend.shared.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Utils util;






    @Override
    public UserEntity getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElse(null);
        if (userEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UserEntity with email " + email + " not found");
        }

        return userEntity;
    }


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email).orElse(null);
        if (userEntity == null) {
            throw new UsernameNotFoundException("UserEntity not found with email: " + email);
        }
        // Return a UserDetails object based on userEntity information
        Collection<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(userEntity.getUserRole().name()));

        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }




}
