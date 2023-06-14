package spring.angular.blogging.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class AuthenticationResponse {
    
	private String authenticationToken;
    private String username;
	public AuthenticationResponse(String authenticationToken, String username) {
		super();
		this.authenticationToken = authenticationToken;
		this.username = username;
	}
	public AuthenticationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AuthenticationResponse [authenticationToken=" + authenticationToken + ", username=" + username + "]";
	}
	public String getAuthenticationToken() {
		return authenticationToken;
	}
	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
    
    
}
