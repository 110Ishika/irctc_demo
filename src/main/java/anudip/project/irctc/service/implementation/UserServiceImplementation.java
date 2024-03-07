package anudip.project.irctc.service.implementation;

import anudip.project.irctc.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public int generateOTP() {
        String otp = "";

        while (otp.length() != 4) {

            otp = String.valueOf(Math.abs((LocalTime.now().getNano() *
                    (LocalTime.now().getNano() % 9753)) %
                    9999));
        }

        return Integer.parseInt(otp);
    }

    @Override
    public void sendMail(String toEmail, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("r007.5y573m@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send...");
    }

    @Override
    public void triggerMail() throws MessagingException {
        System.out.println("Called");
        sendMail("3991aniketmishra@gmail.com",
                "Verification Mail",
                String.valueOf(generateOTP()));

    }
}
