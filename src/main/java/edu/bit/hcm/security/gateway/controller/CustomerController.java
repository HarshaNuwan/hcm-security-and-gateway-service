package edu.bit.hcm.security.gateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.bit.hcm.security.gateway.config.JwtTokenUtil;
import edu.bit.hcm.security.gateway.entity.CustomerUserAccountEntity;
import edu.bit.hcm.security.gateway.model.CustomerModel;
import edu.bit.hcm.security.gateway.model.CustomerVerificationRequest;
import edu.bit.hcm.security.gateway.model.JwtRequest;
import edu.bit.hcm.security.gateway.model.JwtResponse;
import edu.bit.hcm.security.gateway.model.UserModel;
import edu.bit.hcm.security.gateway.service.JwtUserDetailsService;

@RestController
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private UserModel userModel;

	@Autowired
	private CustomerModel customerModel;

	@PostMapping("/customer/signup")
	public ResponseEntity<Object> customerSignUp(@RequestBody CustomerUserAccountEntity customerUserAccountEntity) {
		try {
			return customerModel.signUpCustomer(customerUserAccountEntity);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@RequestMapping(value = "/customer/verifyUser", method = RequestMethod.POST)
	public ResponseEntity<Object> verifyCustomer(@RequestBody CustomerVerificationRequest verificationRequest)
			throws Exception {
		try {
			return customerModel.verifyCustomer(verificationRequest);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@RequestMapping(value = "/customer/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

			final String token = jwtTokenUtil.generateToken(userDetails);

			return ResponseEntity.ok(new JwtResponse(token));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
