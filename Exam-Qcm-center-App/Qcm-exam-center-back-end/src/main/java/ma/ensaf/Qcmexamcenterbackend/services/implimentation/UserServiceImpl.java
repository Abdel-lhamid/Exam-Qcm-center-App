package ma.ensaf.Qcmexamcenterbackend.services.implimentation;

import ma.ensaf.Qcmexamcenterbackend.dtos.UserDto;
import ma.ensaf.Qcmexamcenterbackend.entities.UserEntity;
import ma.ensaf.Qcmexamcenterbackend.enums.UserRole;
import ma.ensaf.Qcmexamcenterbackend.repositories.UserRepository;
import ma.ensaf.Qcmexamcenterbackend.services.UserService;
import ma.ensaf.Qcmexamcenterbackend.shared.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
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
    public UserDto createUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists with this email");
        }
        // Encrypt the password
        String encryptedPassword = bCryptPasswordEncoder.encode(userDto.getPassword());

        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setEncryptedPassword(encryptedPassword);
        //set userId
        userEntity.setUserId(util.generateCustomId(32));

        UserEntity storedUser = userRepository.save(userEntity);
        return modelMapper.map(storedUser, UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with email " + email + " not found");
        }

        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        UserEntity userEntity = userRepository.findByUserId(userDto.getUserId());
        if (userEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + userDto.getUserId() + " not found");
        }

        modelMapper.map(userDto, userEntity); // This updates the existing entity with the values from DTO
        UserEntity updatedUser = userRepository.save(userEntity);

        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + userId + " not found");
        }

        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getAllStudents() {
        List<UserEntity> students = userRepository.findAllByUserRole(UserRole.STUDENT);
        return students.stream()
                .map(student -> modelMapper.map(student, UserDto.class))
                .collect(Collectors.toList());
    }

}