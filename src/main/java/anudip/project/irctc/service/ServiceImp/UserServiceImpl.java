package anudip.project.irctc.service.ServiceImp;

import java.time.LocalTime;
import java.util.List;

import anudip.project.irctc.entity.UserVerification;
import anudip.project.irctc.repository.UserVerificationRepository;
import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import anudip.project.irctc.repository.UserRepository;
import anudip.project.irctc.entity.User;
import anudip.project.irctc.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserVerificationRepository userVerificationRepository;

    @Override
    public int checkUserStatus(User user) {
        User existedUser = getUserByEmail(user.getEmail());

        if (existedUser == null)
            return 3; // user not available

        if (existedUser.getStatus() == 0)
            return 2; // already registered need to verify

        return 1; // already registered need to login
    }

    @Override
    public User saveUser(User user) {
        User savedUser = userRepository.save(user);
        int otp = generateOTP();

        userVerificationRepository.save(new UserVerification(user.getEmail(), otp));

        try {
            triggerMail(user.getEmail(), user.getFirstName(), otp);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        return savedUser;
    }

    @Override
    public boolean verifyUser(UserVerification verification) {
        boolean flag = false;
        if (userVerificationRepository
                .findByEmail(verification.getEmail())
                .getOtp() == verification
                .getOtp()) {

            User user = userRepository.findByEmail(verification.getEmail());
            user.setStatus(1);
            updateUser(user);
            flag = true;
        }
        return flag;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateUser(User user) {
        User existedUser = userRepository.findByEmail(user.getEmail());
        existedUser.setFirstName(user.getFirstName());
        existedUser.setLastName(user.getLastName());
        existedUser.setEmail(user.getEmail());
        existedUser.setContact(user.getContact());
        existedUser.setPassword(user.getPassword());
        existedUser.setStatus(user.getStatus());
        existedUser.setRole(user.getRole());

        return userRepository.save(existedUser);
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

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
    public void sentVerificationMail(String toEmail, String userName, int otp) {
        SimpleMailMessage message = new SimpleMailMessage();

        String subject = "User Verification - IRCTC";

        String body = "Dear " + userName + ",\n" +
                "Thank you for your registration in IRCTC. \n\n" +
                "Kindly use code: " + otp + ".\n" +
                "For account verification\n\n" +
                "Thanks & Regards\n\n" +
                "IRCTC(Demo)\n" +
                "Indian Railway";

        message.setFrom("r007.5y573m@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send...");
    }

    @Override
    public void triggerMail(String toEmail, String userName, int otp) throws MessagingException {
        sentVerificationMail(toEmail, userName, otp);
    }
}
