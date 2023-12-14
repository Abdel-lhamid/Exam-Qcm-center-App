package ma.ensaf.Qcmexamcenterbackend.services.implimentation;

import lombok.RequiredArgsConstructor;
import ma.ensaf.Qcmexamcenterbackend.config.JwtService;
import ma.ensaf.Qcmexamcenterbackend.controllers.AdminController;
import ma.ensaf.Qcmexamcenterbackend.dtos.AuthRequest;
import ma.ensaf.Qcmexamcenterbackend.dtos.Requests.AdminSignUpRequest;
import ma.ensaf.Qcmexamcenterbackend.entities.AdminEntity;
import ma.ensaf.Qcmexamcenterbackend.entities.SchoolEntity;
import ma.ensaf.Qcmexamcenterbackend.enums.UserRole;
import ma.ensaf.Qcmexamcenterbackend.exceptions.CustomAuthException;
import ma.ensaf.Qcmexamcenterbackend.repositories.AdminRepository;
import ma.ensaf.Qcmexamcenterbackend.repositories.SchoolRepository;
import ma.ensaf.Qcmexamcenterbackend.repositories.UserRepository;
import ma.ensaf.Qcmexamcenterbackend.response.AuthenticationResponse;
import ma.ensaf.Qcmexamcenterbackend.shared.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private SchoolRepository schoolRepository;

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

    private final EmailSender emailSender;


    @Value("${url}")
    private String url;
    @Value("${login.token.expiry_minutes}")
    private int restPassword_expiring_min;
    @Value("${verify.email.token.expiry_minutes}")
    private int verify_email_expiring_min;
    @Value("${app.email}")
    private String appEmail;

    //Registration only for admin
    public String adminRegistration(AdminSignUpRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists with this email");
        }
        if (schoolRepository.findByName(request.getSchoolName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "school already exists with this name");
        }
        SchoolEntity school = (SchoolEntity) SchoolEntity.builder()
                .name(request.getSchoolName())
                .schoolId(util.generateCustomId(11))
                .build()
                ;
        SchoolEntity schoolSaved = schoolRepository.save(school);
        // Encrypt the password
        String encryptedPassword = bCryptPasswordEncoder.encode(request.getPassword());
        AdminEntity admin = new AdminEntity();
        admin.setEmail(request.getEmail());
        admin.setPassword(encryptedPassword);
        admin.setFullName(request.getFullName());
        admin.setSchool(schoolSaved);
        admin.setUserId(util.generateCustomId(11));
        admin.setUserRole(UserRole.ADMIN);
        var verificationToken = jwtService.generateToken(admin);
        admin.setVerificationToken(verificationToken);
        AdminEntity storedAdminEntity = adminRepository.save(admin);
        //TODO: send verification email
        String verificationLink = url + "verifyEmail?verificationToken=" + verificationToken;

        String emailVerificationHtmlMsg = "<!-- In Container -->\n"
                + "<table class=\"in_container\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" align=\"center\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\">\n"
                + "  <tr>\n"
                + "    <td>\n"
                + "      <!-- About  -->\n"
                + "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" align=\"center\" bgcolor=\"ffffff\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt; background-color:#ffffff;\">\n"
                + "        <tr><td height=\"65px\" width=\"100%\" style=\"height:65px;\"></td></tr>\n"
                + "        <tr>\n"
                + "          <td align=\"center\">\n"
                + "            <table class=\"full_width_600\" width=\"600\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt; border:0;text-align:center;\">\n"
                + "              <tr>\n"
                + "                <td>\n"
                + "                  <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt; border:0;\">\n"
                + "                    <tr>\n"
                + "                      <td align=\"left\" style=\" text-align:left; color: #676f84; font-family: 'Open Sans', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 600; line-height:30px;\"> <span style=\"color:black;\">Hello</span> " + admin.getFullName() + ",</td>\n"
                + "                    </tr>\n"
                + "                  </table>\n"
                + "                </td>\n"
                + "              </tr>\n"
                + "              <tr><td height=\"15px\" width=\"100%\" style=\"height:15px;\"></td></tr>\n"
                + "              <tr>\n"
                + "                <td>\n"
                + "                  <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width: 600px; margin:0 auto;border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt; border:0;\">\n"
                + "                    <tr>\n"
                + "                      <td style=\"text-align:left; color: black; font-family: 'Open Sans', Helvetica, Arial, sans-serif; font-size: 14px; font-weight: 600; line-height: 20px;\">\n"
                + "Your email address has been successfully registered. Please click the following link to verify your email:\n"
                + "<br>\n"
                + "<div style=\"text-align:center; background-color: #00bcd4; color: white;padding: 15px 25px; text-align: center; text-decoration: none; display: inline-block;\">\n"
                + "  <a style=\"color:white;\" href=" + verificationLink + ">Verify Email</a>\n"
                + "</div>\n"
                + "<br>\n"
                + "Thank you for using our service!\n"
                + "                      </td>\n"
                + "                    </tr>\n"
                + "                  </table>\n"
                + "                </td>\n"
                + "              </tr>\n"
                + "            </table>\n"
                + "          </td>\n"
                + "        </tr>\n"
                + "        <tr><td height=\"65px\" width=\"100%\" style=\"height:65px;\"></td></tr>\n"
                + "      </table>\n"
                + "      <!-- End About  -->\n"
                + "\n"
                + "      <!-- Bottom -->\n"
                + "      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"  width=\"100%\" align=\"center\" background=\"http://ryanhallmedia.com/thirdspace/img/email/lastback.jpg\" bgcolor=\"3d424e\" style=\"background-image:url('img/lastback.jpg'); background-size: cover; -webkit-background-size: cover; -moz-background-size: cover -o-background-size: cover; background-position: bottom center; background-repeat: no-repeat; background-color: #00bcd4; border-radius: 0px 0px 2px 2px;\">\n"
                + "        <tr>\n"
                + "          <td>\n"
                + "            <!--  Caption  -->\n"
                + "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                + "              <tr>\n"
                + "                <td>\n"
                + "                  <table width=\"100%\" border=\"0\" cellpadding=\"20\" cellspacing=\"0\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt; border:0;\">\n"
                + "                    <tr>\n"
                + "                      <td>\n"
                + "                        <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt; border:0;\">\n"
                + "                          <tr>\n"
                + "                            <td align=\"left\" style=\" text-align:left; color: #fff; font-family: 'Open Sans', Helvetica, Arial, sans-serif; font-size: 14px; font-weight: 700;line-height:24px;letter-spacing:0.5px;\"> Best regards,</td>\n"
                + "                          </tr>\n"
                + "                        </table>\n"
                + "                      </td>\n"
                + "                    </tr>\n"
                + "                  </table>\n"
                + "                </td>\n"
                + "              </tr>\n"
                + "\n"
                + "            </table>\n"
                + "          </td>\n"
                + "        </tr>\n"
                + "      </table>\n"
                + "      <!-- Bottom -->\n"
                + "    </td>\n"
                + "  </tr>\n"
                + "</table>\n"
                + "<!-- End In Container -->";

        try {
            emailSender.send(admin.getEmail(),"Verification email Qcm-Portal.ma",emailVerificationHtmlMsg);
        } catch (Exception ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //end email sending


        return ("Your Admin profile has been created, please check your email to verify your account");

    }
    public AuthenticationResponse authenticate(AuthRequest authRequest) throws AuthenticationException {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword()
                    )
            );
            var user = userRepository.findByEmail(authRequest.getEmail()).orElse(null);
            assert user != null;
            if (!user.isEnabled()) {
                throw new CustomAuthException("User is not active, please check you email");
            }
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
