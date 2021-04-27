package edu.bit.hcm.security.gateway.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer", schema = "gloffee")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "customer_user_account_id")
	private Long customerUserAccountId;

	@Column(name = "customer_name")
	private String customerName;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "address")
	private String address;

	@Column(name = "score")
	private Integer score;

	@Column(name = "verification_code")
	private Integer verificationCode;

	@Column(name = "datetime_stamp")
	private Timestamp datetimeStamp;
	
	public CustomerEntity() {
		// TODO Auto-generated constructor stub
	}

	public CustomerEntity(Long customerId, Long customerUserAccountId, String customerName, String mobileNumber,
			String address, Integer score, Integer verificationCode, Timestamp datetimeStamp) {
		super();
		this.customerId = customerId;
		this.customerUserAccountId = customerUserAccountId;
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.score = score;
		this.verificationCode = verificationCode;
		this.datetimeStamp = datetimeStamp;
	}
	


	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getCustomerUserAccountId() {
		return customerUserAccountId;
	}

	public void setCustomerUserAccountId(Long customerUserAccountId) {
		this.customerUserAccountId = customerUserAccountId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(Integer verificationCode) {
		this.verificationCode = verificationCode;
	}

	public Timestamp getDatetimeStamp() {
		return datetimeStamp;
	}

	public void setDatetimeStamp(Timestamp datetimeStamp) {
		this.datetimeStamp = datetimeStamp;
	}


}
