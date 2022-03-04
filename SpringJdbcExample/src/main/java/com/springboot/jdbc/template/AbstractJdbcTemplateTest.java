package com.springboot.jdbc.template;

import java.util.List;

import com.springboot.jdbc.domain.Account;


public class AbstractJdbcTemplateTest {

	@SuppressWarnings("unchecked")
	public void test() {
	     AbstractJdbcTemplate jdbcTemplate = new AccountJdbcTemplate();  
	     List<Account> account = (List<Account>) jdbcTemplate.execute("select * from Account");
	}
}
