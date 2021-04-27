package edu.bit.hcm.security.gateway.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.bit.hcm.security.gateway.entity.CustomerUserAccountEntity;
import edu.bit.hcm.security.gateway.entity.UserEntity;
import edu.bit.hcm.security.gateway.repository.CustomerUserAccountRepository;
import edu.bit.hcm.security.gateway.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByUsername(username);
		
		if (null != userEntity) {
			
			if(!userEntity.isActive()) {
				throw new UsernameNotFoundException("User account not activated");
			}
			
			return new User(userEntity.getUsername(), userEntity.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}