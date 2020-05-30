package com.EmployeeManagementSystem.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.EmployeeManagementSystem.Model.AuthenticationRequest;
import com.EmployeeManagementSystem.Util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	final static Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	JwtUtil jwtutil;

	@Autowired
	AuthenticationManager authenticationmanager;
	
	@PostMapping("/token")
	public String generateToken(@RequestBody AuthenticationRequest authenticationrequest) throws Exception {
		LOGGER.info("authenticationrequest");
		try {
			authenticationmanager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationrequest.getUsername(), authenticationrequest.getPassword()));
		} catch (Exception e) {
			throw new Exception("Invalid UserName or password");

		}
		return jwtutil.generateToken(authenticationrequest.getUsername());

	}
}
