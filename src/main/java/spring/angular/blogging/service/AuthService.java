package spring.angular.blogging.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import spring.angular.blogging.exception.InvalidOtpException;
import spring.angular.blogging.exception.UserNotFoundException;
import spring.angular.blogging.model.User;
import spring.angular.blogging.repository.UserRepository;
import spring.angular.blogging.security.JwtProvider;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    
    Integer randNumber = (int) (Math.random()*10000);

    public void signup(User registerRequest) {
        User user = new User();
    user.setUserName(registerRequest.getUserName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodePassword(registerRequest.getPassword()));
        user.setOtp(randNumber);

        userRepository.save(user);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public AuthenticationResponse login(User loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(authenticationToken, loginRequest.getUserName());
    }

    public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
    	org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }

	public Integer getOtp(User findByName, String uname, Integer otp) {
		String uname1 = findByName.getUserName();
		Integer dbOtp = findByName.getOtp();
		if(uname1.equals(uname)) {
			//randNumber = this.randNumber;
			if(otp.equals(dbOtp)) {
				return otp;
			}
			else {
				throw new InvalidOtpException("Invalid OTP");
			}
		}
		else
		{
			throw new UserNotFoundException("Username not found in the Database, please check !");
		}
	}
	
	public User findByName(String uname) {
		User user = userRepository.findByName(uname);
		return user;
		
	}
}
