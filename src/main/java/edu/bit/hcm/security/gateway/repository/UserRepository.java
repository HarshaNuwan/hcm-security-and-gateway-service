package edu.bit.hcm.security.gateway.repository;

import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.bit.hcm.security.gateway.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	UserEntity findByUsername(final String userName);

	@Transactional
	@Modifying
	@Query("UPDATE UserEntity u SET u.username=?1, u.userCreateDateTimeStamp=?2,"
			+ "u.active=?3, u.userRoleId=?4, u.doctorId=?5  WHERE u.userId = ?6")
	public void updateUser(String username, Timestamp userCreateDateTimeStamp, boolean active, int userRoleId,
			int doctorId, int userId);
}
