package spring.angular.blogging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.angular.blogging.model.User;
import spring.angular.blogging.service.AuthService;
import spring.angular.blogging.service.AuthenticationResponse;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin("http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody User registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody User loginRequest) {
        return authService.login(loginRequest);
    }
    
    @GetMapping("/getOtp/{uname}/{otp}")
	public Integer getOtp(@PathVariable String uname,@PathVariable Integer otp) throws Exception {
		User findByName = authService.findByName(uname);
		return authService.getOtp(findByName, uname, otp);
	
	}

}
