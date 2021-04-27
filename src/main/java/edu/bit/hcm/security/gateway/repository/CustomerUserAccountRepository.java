package edu.bit.hcm.security.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.bit.hcm.security.gateway.entity.CustomerUserAccountEntity;

@Repository
public interface CustomerUserAccountRepository extends JpaRepository<CustomerUserAccountEntity, Long> {

	CustomerUserAccountEntity findByUsername(String username);

}
