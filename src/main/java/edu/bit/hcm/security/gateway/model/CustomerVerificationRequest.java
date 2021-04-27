package edu.bit.hcm.security.gateway.model;

import java.io.Serializable;

public class CustomerVerificationRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4699730747298170423L;

	private int verificationCode;

	private String username;

	public CustomerVerificationRequest(int verificationCode, String username) {
		super();
		this.verificationCode = verificationCode;
		this.username = username;
	}

	public int getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(int verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
