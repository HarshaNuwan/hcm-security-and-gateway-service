package edu.bit.hcm.security.gateway.model;

import java.io.Serializable;

import edu.bit.hcm.UserDTO;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private UserDTO dto;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
	
	public UserDTO getDto() {
		return dto;
	}
	
	public void setDto(UserDTO dto) {
		this.dto = dto;
	}
}
