package com.springboot.jdbc.template;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.springboot.jdbc.domain.Account;

public class CallbackJdbcTemplateTest {

	public Object queryAccount(final String sql)  {

		class AccountStatementCallback implements StatementCallback {

			public Object handleStatement(Statement statement) throws SQLException {
				ResultSet rs = statement.executeQuery(sql);
				List<Account> accounts = new ArrayList<Account>();
				while (rs.next()) {
					Account account = new Account(rs.getLong("id"), rs.getString("account_number"),
							rs.getString("account_name"));
					accounts.add(account);
				}

				return accounts;
			}
		}

		CallbackJdbcTemplate jdbcTemplate = new CallbackJdbcTemplate();
		return jdbcTemplate.execute(new AccountStatementCallback());
	}
	
	public Object queryAccount2(final String sql)  {

		CallbackJdbcTemplate jdbcTemplate = new CallbackJdbcTemplate();
		return jdbcTemplate.execute(new StatementCallback() {
			
			public Object handleStatement(Statement statement) throws SQLException {
				ResultSet rs = statement.executeQuery(sql);
				List<Account> accounts = new ArrayList<Account>();
				while (rs.next()) {
					Account account = new Account(rs.getLong("id"), rs.getString("account_number"),
							rs.getString("account_name"));
					accounts.add(account);
				}

				return accounts;
			}
		});		
	}


	@SuppressWarnings("unchecked")
	public void test() {
		List<Account> accounts = (List<Account>) queryAccount("select * from Account");
	}
}
