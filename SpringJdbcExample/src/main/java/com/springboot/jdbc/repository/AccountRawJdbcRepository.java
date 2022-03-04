package com.springboot.jdbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.jdbc.domain.Account;


@Repository("accountRawJdbcRepository")
public class AccountRawJdbcRepository implements AccountRepository {

	@Autowired
	private DataSource dataSource;

	@Override
	public Account addAccount(Account account) {
		// 不做实现
		return null;
	}

	@Override
	public Account getAccountById(Long accountId) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("select id, account_number, account_name from `account` where id=?");
			statement.setLong(1, accountId);
			resultSet = statement.executeQuery();
			Account account = null;
			if (resultSet.next()) {
				account = new Account(resultSet.getLong("id"), resultSet.getString("account_number"),
						resultSet.getString("account_name"));
			}
			return account;
		} catch (SQLException e) {
			System.out.print(e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}
		return null;
	}

	@Override
	public Account getAuthoritiesByAccountNumber(String accountNumber) {
		// 不做实现
		return null;
	}
}
