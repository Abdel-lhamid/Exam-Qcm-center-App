package ma.ensaf.Qcmexamcenterbackend.controllers;

import ma.ensaf.Qcmexamcenterbackend.dtos.AuthRequest;
import ma.ensaf.Qcmexamcenterbackend.dtos.SignUpRequest;
import ma.ensaf.Qcmexamcenterbackend.dtos.UserDto;
import ma.ensaf.Qcmexamcenterbackend.response.AuthenticationResponse;
import ma.ensaf.Qcmexamcenterbackend.services.UserService;
import ma.ensaf.Qcmexamcenterbackend.services.implimentation.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/test")
    public String test(){
        return "auth working, test success";
    }
    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody SignUpRequest request
    ) {
        return ResponseEntity.ok(authService.createUser(request));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthRequest authRequest
            ) {
        String email = authRequest.getEmail();
        String password = authRequest.getPassword();

        return ResponseEntity.ok(authService.authenticate(email, password));
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/students")
    public ResponseEntity<List<UserDto>> getAllStudents() {
        List<UserDto> students = userService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String email) {
        UserDto user = modelMapper.map(userService.getUserByEmail(email), UserDto.class);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
