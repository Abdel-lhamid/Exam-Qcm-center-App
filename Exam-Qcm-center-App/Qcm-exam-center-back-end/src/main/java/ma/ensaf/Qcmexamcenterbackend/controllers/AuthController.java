package ma.ensaf.Qcmexamcenterbackend.controllers;

import ma.ensaf.Qcmexamcenterbackend.dtos.Requests.AuthRequest;
import ma.ensaf.Qcmexamcenterbackend.dtos.Requests.AdminSignUpRequest;
import ma.ensaf.Qcmexamcenterbackend.dtos.Requests.ResetPasswordRequest;
import ma.ensaf.Qcmexamcenterbackend.repositories.StudentRepository;
import ma.ensaf.Qcmexamcenterbackend.response.AuthenticationResponse;
import ma.ensaf.Qcmexamcenterbackend.services.implimentation.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthService authService;

    //sign in getaway for all users

    @PostMapping("/sign-in")
    @CrossOrigin
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthRequest authRequest
    ) {
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }

    //sign up getaway for Admin
    @PostMapping("/sign-up")
    public ResponseEntity<String> adminRegister(
            @RequestBody AdminSignUpRequest adminReq
    ) {
        return ResponseEntity.ok(authService.adminRegistration(adminReq));
    }

    @GetMapping("/verifyEmail")
    public ResponseEntity<String> verifyAdmin(@RequestParam String verificationToken){

        return ResponseEntity.ok(authService.verifyEmail(verificationToken));
    }
    @PostMapping("/forgetPassword")
    public ResponseEntity<String> forgetPassword(@RequestBody String email){
        return ResponseEntity.ok(authService.forgetPassword(email));
    }
    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request){
        return ResponseEntity.ok(authService.resetPassword(request.getVerificationToken(),request.getPassword()));
    }


    //TODO: send invites to professors


    //TODO: send invites to students




}
