package anudip.project.irctc.service;


import jakarta.mail.MessagingException;

public interface UserService {

    int generateOTP();

    void sendMail(String toEmail, String subject, String body);

    void triggerMail() throws MessagingException;
}
