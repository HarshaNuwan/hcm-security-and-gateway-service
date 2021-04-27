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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.bit.hcm.UserDTO;
import edu.bit.hcm.security.gateway.config.JwtTokenUtil;
import edu.bit.hcm.security.gateway.entity.UserEntity;
import edu.bit.hcm.security.gateway.model.JwtRequest;
import edu.bit.hcm.security.gateway.model.JwtResponse;
import edu.bit.hcm.security.gateway.model.UserModel;
import edu.bit.hcm.security.gateway.service.JwtUserDetailsService;
import edu.bit.hcm.wrapper.UserDTOListWrapper;

@RestController
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private UserModel userModel;
	
	@PostMapping("/user/signup")
	public ResponseEntity<Object> userSignUp(@RequestBody UserEntity user) {
		try {
			userModel.userSignUp(user);
			return ResponseEntity.status(HttpStatus.OK)
					.body("{\"MESSAGE\":\"User creation Success\"}");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"ERROR\":\"Internal Error\"}");
		}
	}
	
	@RequestMapping(value = "/user/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

			final String token = jwtTokenUtil.generateToken(userDetails);

			return ResponseEntity.ok(new JwtResponse(token));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}
	
	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public ResponseEntity<?> deleteUser(@RequestBody UserDTO userDTO){
		try {
			userModel.deleteUser(userDTO.getUserId());
			return ResponseEntity.status(HttpStatus.OK)
					.body("{\"MESSAGE\":\"User deleted\"}");
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("/user/getUsers")
	public ResponseEntity<UserDTOListWrapper> getAllUsers(){		
		return ResponseEntity.status(HttpStatus.OK).body(userModel.getAllUsers());
	
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			e.printStackTrace();
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("{\"ERROR\":\"INVALID_CREDENTIALS\"}", e);
		}
	}


}
