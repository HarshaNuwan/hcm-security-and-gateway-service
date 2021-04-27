package edu.bit.hcm.security.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.bit.hcm.security.gateway.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>{

	CustomerEntity findByCustomerUserAccountId(long customerUserAccountId);
}