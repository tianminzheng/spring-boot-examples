package com.springboot.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.springboot.jpa.domain.Account;


@Repository("accountJpaRepository")
public interface AccountRepository extends JpaRepository<Account, Long>, 
	JpaSpecificationExecutor<Account>, QueryByExampleExecutor<Account> {

	@Query("select o from Account o where o.accountNumber = ?1")
	Account getAccountByAccountNumberWithQuery(String accountNumber);

	Account getAccountByAccountNumber(String accountNumber);

}
