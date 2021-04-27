package edu.bit.hcm.security.gateway.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class VerificationCodeGeneratorService {
	
	public static String generateVerificationCode() {
		String vCode = null;

		Random rand = new Random();
		int n = rand.nextInt(999999);
		vCode = String.valueOf(n);

		return vCode;
	}
}
