package edu.bit.hcm.security.gateway.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer_user_account", schema = "gloffee")
public class CustomerUserAccountEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_user_account_id")
	private Long customerUserAccountId;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "user_create_datetime_stamp")
	private Timestamp userCreateDateTimeStamp;

	@Column(name = "active")
	private Boolean active;

	@JoinColumn(name = "customer_user_account_id")
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private CustomerEntity customerEntity;
	
	public CustomerUserAccountEntity() {
		// TODO Auto-generated constructor stub
	}

	public CustomerUserAccountEntity(Long customerUserAccountId, String username, String password,
			Timestamp userCreateDateTimeStamp, Boolean active) {
		super();
		this.customerUserAccountId = customerUserAccountId;
		this.username = username;
		this.password = password;
		this.userCreateDateTimeStamp = userCreateDateTimeStamp;
		this.active = active;
	}

	
	
	public CustomerUserAccountEntity(Long customerUserAccountId, String username, String password,
			Timestamp userCreateDateTimeStamp, Boolean active, CustomerEntity customerEntity) {
		super();
		this.customerUserAccountId = customerUserAccountId;
		this.username = username;
		this.password = password;
		this.userCreateDateTimeStamp = userCreateDateTimeStamp;
		this.active = active;
		this.customerEntity = customerEntity;
	}

	public Long getCustomerUserAccountId() {
		return customerUserAccountId;
	}

	public void setCustomerUserAccountId(Long customerUserAccountId) {
		this.customerUserAccountId = customerUserAccountId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getUserCreateDateTimeStamp() {
		return userCreateDateTimeStamp;
	}

	public void setUserCreateDateTimeStamp(Timestamp userCreateDateTimeStamp) {
		this.userCreateDateTimeStamp = userCreateDateTimeStamp;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}

	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}

	
}
