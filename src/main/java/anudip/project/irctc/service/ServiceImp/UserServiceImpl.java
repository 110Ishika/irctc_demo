package anudip.project.irctc.service.ServiceImp;

import anudip.project.irctc.entity.User;
import anudip.project.irctc.entity.UserVerification;
import anudip.project.irctc.model.Login;
import anudip.project.irctc.repository.UserRepository;
import anudip.project.irctc.repository.UserVerificationRepository;
import anudip.project.irctc.service.UserService;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserVerificationRepository userVerificationRepository;

	/**
	 * Method is used to save the user to database
	 */
	@Override
	public User saveUser(User user) {
		user.setPassword(passwordEncryption(user.getPassword()));
		System.out.println(user.getUserId());
		if (user.getUserId() != 0)
			return updateUser(user);
		
		return userRepository.save(user);
	}

	/**
	 * Method is used to save user to data bases and send otp to used
	 */
	@Override
	public void saveUserAndSentOtp(User user) {
		User savedUser = saveUser(user);
		int otp = 0;
		if (user.getRole().equalsIgnoreCase("user")) {
			otp = generateOTP();
			userVerificationRepository.deleteAllByEmail(user.getEmail());
			userVerificationRepository.save(new UserVerification(user.getEmail(), otp, new Date()));
		}

		try {
			sentVerificationMail(user.getEmail(), user.getFirstName(), otp, user.getRole());
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			exception.printStackTrace();
		}
	}

	/**
	 * Method is used to verify used otp and activate the user
	 */
	@Override
	public boolean verifyUser(UserVerification toVerify) {
		boolean isVerified = false;
		UserVerification storedVerification = userVerificationRepository.findByEmail(toVerify.getEmail());
		if (storedVerification.getOtp() == toVerify.getOtp()) {

			User user = userRepository.findByEmail(toVerify.getEmail());
			user.setStatus(1);
			updateUser(user);
			userVerificationRepository.deleteById(storedVerification.getValidateId());
			isVerified = true;
		}
		return isVerified;
	}

	/**
	 * Method is used to return all user list
	 */
	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	/**
	 * Method is used to find user using email address
	 */
	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	/**
	 * Method is used to update an existing user
	 */
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

	/**
	 * Method is used to delete the user
	 */
	@Override
	public void deleteUser(int userId) {
		userRepository.deleteById(userId);
	}

	/**
	 * Method is used to send mail to given mail address
	 */
	@Override
	public void sentVerificationMail(String toEmail, String userName, int otp, String role) throws MessagingException {
		SimpleMailMessage message = new SimpleMailMessage();

		String subject = "User Verification - IRCTC";

		String body = getMailBody(userName, otp, role);

		message.setFrom("r007.5y573m@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		mailSender.send(message);
	}

	/**
	 * Method is used to authenticate used using user given credentials
	 */
	@Override
	public boolean userAuthentication(Login login) {
		User user = userRepository.findByEmail(login.getEmail());

		if (user == null)
			return false;

		return checkPassword(login.getPassword(), user.getPassword());
	}

	/**
	 * Method is used to generate and return 4 digit otp
	 */
	private int generateOTP() {
		String otp = "";

		while (otp.length() != 4) {

			otp = String.valueOf(Math.abs((LocalTime.now().getNano() * (LocalTime.now().getNano() % 9753)) % 9999));
		}
		return Integer.parseInt(otp);
	}

	/**
	 * Method is used return mail body based on user role
	 */
	private String getMailBody(String userName, int otp, String role) {

		String forUser = "Dear " + userName + ",\n\n" + "Thank you for your registration in IRCTC. \n\n"
				+ "Kindly use code: " + otp + ".\n" + "For account verification\n\n" + "Thanks & Regards\n\n"
				+ "IRCTC(Demo)\n" + "Indian Railway";

		String forAdmin = "Dear " + userName + ",\n\n" + "Welcome to the IRCTC. \n\n"
				+ "Kindly send following details on the same mail for admin access verification\n" + "name - \n"
				+ "address - \n" + "IRCTC mail id - \n" + "Employee Id - \n\n" + "Thanks & Regards\n\n"
				+ "IRCTC(Demo)\n" + "Indian Railway";

		return role.equalsIgnoreCase("user") ? forUser : forAdmin;
	}

	/**
	 * Mail is used to encrypt user given password using bCrypt algorithm
	 */
	private String passwordEncryption(String password) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.encode(password);
	}

	/**
	 * Method used to match user hash password and user given plain password
	 */
	private boolean checkPassword(String password, String hashPassword) {
		return BCrypt.checkpw(password, hashPassword);
	}
}