package edu.bit.hcm.security.gateway.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.bit.hcm.UserDTO;
import edu.bit.hcm.security.gateway.entity.UserEntity;
import edu.bit.hcm.security.gateway.repository.UserRepository;
import edu.bit.hcm.wrapper.UserDTOListWrapper;
import io.jsonwebtoken.lang.Collections;

@Service
public class UserModel {
	@Autowired
	private UserRepository userRepository;
	
	public void userSignUp(UserEntity user) throws Exception{
		//Get current system date time as the user account created date
		java.util.Date userCreatedDate = new java.util.Date();
		user.setUserCreateDateTimeStamp(new Timestamp(userCreatedDate.getTime()));
		
		if(null != user.getPassword() && !user.getPassword().isEmpty()) {
			user.setPassword(encrytePassword(user.getPassword()));
		}
		
		//set the user active status to false at user account creation
		//user.setActive(false);
		
		userRepository.save(user);

	}
	
	public UserDTOListWrapper getAllUsers() {
		List<UserDTO> userList = new ArrayList<>();
		if(!Collections.isEmpty(userRepository.findAll())) {
			for(UserEntity entity : userRepository.findAll()) {
				UserDTO dto = new UserDTO(entity.getUserId(), entity.getUsername(), null, entity.getUserCreateDateTimeStamp().getTime(), entity.isActive(), entity.getUserRoleId());
				
				userList.add(dto);
			}
		}
		
		return new UserDTOListWrapper(userList);
	}
	
	public void deleteUser(long userId) {
		userRepository.deleteById(userId);
	}
	
	public static String encrytePassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
}
