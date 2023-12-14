package ma.ensaf.Qcmexamcenterbackend.services.implimentation;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSender {


    @Value("${app.email}")
    private String appEmail;

    @Autowired
    public JavaMailSender emailSender;
    @Async
    public void send(String clientEmail, String subject, String htmlMessage) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            boolean multipart =true;
            MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

            message.setContent(htmlMessage, "text/html");
            helper.setFrom(appEmail);
            helper.setSubject(subject);
            helper.setTo(clientEmail);
            this.emailSender.send(message);

        } catch (MessagingException e) {
            throw new IllegalStateException("failed to send email");
        }

    }

}
