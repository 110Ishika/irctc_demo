package anudip.project.irctc.service;

import anudip.project.irctc.entity.UserVerification;
import jakarta.mail.MessagingException;

import java.util.List;

import anudip.project.irctc.entity.User;

public interface UserService {

    int checkUserStatus(User user);

    User saveUser(User user);

    boolean verifyUser(UserVerification verification);

    List<User> getAllUser();

    User getUserByEmail(String email);

    User updateUser(User user);

    void deleteUser(int userId);

    int generateOTP();

    void sentVerificationMail(String toEmail, String userName, int otp);

    void triggerMail(String toEmail, String userName, int otp) throws MessagingException;
}

