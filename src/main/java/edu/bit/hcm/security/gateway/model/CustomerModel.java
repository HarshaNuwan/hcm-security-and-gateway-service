package edu.bit.hcm.security.gateway.model;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.bit.hcm.security.gateway.entity.CustomerEntity;
import edu.bit.hcm.security.gateway.entity.CustomerUserAccountEntity;
import edu.bit.hcm.security.gateway.repository.CustomerRepository;
import edu.bit.hcm.security.gateway.repository.CustomerUserAccountRepository;
import edu.bit.hcm.security.gateway.service.VerificationCodeGeneratorService;

@Service
public class CustomerModel {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerUserAccountRepository customerUserAccountRepository;

	/**
	 * signup new customer
	 * @param customerUserAccountEntity
	 * @return
	 */
	public ResponseEntity<Object> signUpCustomer(CustomerUserAccountEntity customerUserAccountEntity) {
		try {

			if (null != customerUserAccountRepository.findByUsername(customerUserAccountEntity.getUsername())) {
				return ResponseEntity.status(HttpStatus.OK)
						.body("User " + customerUserAccountEntity.getUsername() + " already exist!");
			}

			// Get current system date time as the user account created date
			java.util.Date userCreatedDate = new java.util.Date();
			customerUserAccountEntity.setUserCreateDateTimeStamp(new Timestamp(userCreatedDate.getTime()));
			customerUserAccountEntity.setPassword(UserModel.encrytePassword(customerUserAccountEntity.getPassword()));
			// set the user active status to false at user account creation
			customerUserAccountEntity.setActive(false);

			// get the saved customer user account object
			CustomerUserAccountEntity userAccountEntity = customerUserAccountRepository.save(customerUserAccountEntity);

			CustomerEntity customerEntity = new CustomerEntity();
			// set customer user account id in customer entity
			customerEntity.setCustomerUserAccountId(userAccountEntity.getCustomerUserAccountId());
			customerEntity.setCustomerName(customerUserAccountEntity.getCustomerEntity().getCustomerName());
			customerEntity.setMobileNumber(customerUserAccountEntity.getCustomerEntity().getMobileNumber());
			customerEntity.setAddress(customerUserAccountEntity.getCustomerEntity().getAddress());
			customerEntity.setScore(0);// set initial score as 0

			/**
			 * Generated SMS code should send to the customer via a SMS gateway for two
			 * factor authentication
			 */
			customerEntity
					.setVerificationCode(Integer.parseInt(VerificationCodeGeneratorService.generateVerificationCode()));
			customerEntity.setDatetimeStamp(new Timestamp(userCreatedDate.getTime()));

			customerRepository.save(customerEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error occured when creating the use account!");
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body("User " + customerUserAccountEntity.getUsername() + " is created successfully.");

	}
	
	
	/**
	 * Verify customer by the SMS code and activate the account if the verification code matches.
	 * @param verificationRequest
	 * @return
	 */
	@SuppressWarnings("unused")
	public ResponseEntity<Object> verifyCustomer(CustomerVerificationRequest verificationRequest) {
		try {
			CustomerUserAccountEntity userAccountEntity = customerUserAccountRepository
					.findByUsername(verificationRequest.getUsername());
			CustomerEntity customerEntity = customerRepository
					.findByCustomerUserAccountId(userAccountEntity.getCustomerUserAccountId());

			if (null == userAccountEntity) {
				return ResponseEntity.status(HttpStatus.OK)
						.body("User " + verificationRequest.getUsername() + " not found!");
			}

			if (null != customerEntity && customerEntity.getVerificationCode() != verificationRequest.getVerificationCode()) {
				return ResponseEntity.status(HttpStatus.OK).body("Verification code not matching!");
			}
			
			userAccountEntity.setActive(true);
			customerUserAccountRepository.save(userAccountEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.OK).body("Account validation failed!");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Account validation successful!");

	}
}
