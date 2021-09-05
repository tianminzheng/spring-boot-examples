package com.springboot.actuator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.actuator.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>  {

	Account findAccountByAccountName(String accountName);
}
