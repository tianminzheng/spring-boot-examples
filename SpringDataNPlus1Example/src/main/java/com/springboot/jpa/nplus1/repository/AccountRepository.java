package com.springboot.jpa.nplus1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.jpa.nplus1.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	@Query("SELECT obj FROM Account obj JOIN FETCH obj.authorities")
	List<Account> findAccounts();

	@Query("SELECT obj FROM Account obj JOIN FETCH obj.authorities WHERE obj IN :accounts")
	List<Account> findAccounts(@Param(value = "accounts") List<Account> accounts);
}
