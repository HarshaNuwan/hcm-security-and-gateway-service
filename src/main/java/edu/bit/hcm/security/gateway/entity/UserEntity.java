package edu.bit.hcm.security.gateway.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user", schema = "hcm_schema")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;

	private String username;
	private String password;

	@Column(name = "user_create_datetime_stamp")
	private java.sql.Timestamp userCreateDateTimeStamp;

	private boolean active;
	@Column(name = "user_role_id")
	private Integer userRoleId;

	@Column(name = "doctor_id")
	private Integer doctorId;

	public UserEntity() {
		// TODO Auto-generated constructor stub
	}

	public UserEntity(int userId, String username, String password, Timestamp userCreateDateTimeStamp, boolean active,
			Integer userRoleId) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.userCreateDateTimeStamp = userCreateDateTimeStamp;
		this.active = active;
		this.userRoleId = userRoleId;
	}

	public UserEntity(int userId, String username, String password, Timestamp userCreateDateTimeStamp, boolean active,
			Integer userRoleId, Integer doctorId) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.userCreateDateTimeStamp = userCreateDateTimeStamp;
		this.active = active;
		this.userRoleId = userRoleId;
		this.doctorId = doctorId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public java.sql.Timestamp getUserCreateDateTimeStamp() {
		return userCreateDateTimeStamp;
	}

	public void setUserCreateDateTimeStamp(java.sql.Timestamp userCreateDateTimeStamp) {
		this.userCreateDateTimeStamp = userCreateDateTimeStamp;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

}
