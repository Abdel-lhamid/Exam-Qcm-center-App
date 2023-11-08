package ma.ensaf.Qcmexamcenterbackend.exceptions;

import org.springframework.security.core.AuthenticationException;

public class CustomAuthException extends AuthenticationException {

    public CustomAuthException(String msg) {
        super(msg);
    }
}
