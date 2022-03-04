package com.springboot.jdbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.springboot.jdbc.domain.Account;
import com.springboot.jdbc.domain.Authority;


@Repository("accountJdbcRepository")
public class AccountJdbcRepository implements AccountRepository {

	private JdbcTemplate jdbcTemplate;

	private SimpleJdbcInsert accountInserter;
	private SimpleJdbcInsert accountAuthorityInserter;

	@Autowired
	public AccountJdbcRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.accountInserter = new SimpleJdbcInsert(jdbcTemplate).withTableName("`account`").usingGeneratedKeyColumns("id");
		this.accountAuthorityInserter = new SimpleJdbcInsert(jdbcTemplate).withTableName("account_authority");
	}

	@Override
	public Account addAccount(Account account) {
		// return addAccountWithJdbcTemplate(account);

		return addAccountDetailWithSimpleJdbcInsert(account);
	}

	@Override
	public Account getAccountById(Long accountId) {
		Account account = jdbcTemplate.queryForObject("select id, account_number, account_name from `account` where id=?",
				this::mapRowToAccount, accountId);

		return account;
	}

	@Override
	public Account getAuthoritiesByAccountNumber(String accountNumber) {
		//获取Account基础信息
		Account account = jdbcTemplate.queryForObject(
				"select id, account_number, account_name from `account` where account_number=?", this::mapRowToAccount,
				accountNumber);

		if (account == null)
			return account;

		//获取Account与Authority之间的关联关系，找到给Account中的所有AuthorityId
		Long accountId = account.getId();
		List<Long> authorityIds = jdbcTemplate.query("select account_id, authority_id from account_authority where account_id=?",
				new ResultSetExtractor<List<Long>>() {
					public List<Long> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<Long> list = new ArrayList<Long>();
						while (rs.next()) {
							list.add(rs.getLong("authority_id"));
						}
						return list;
					}
				}, accountId);

		//根据AuthorityId分别获取Authority信息并填充到Account对象中
		for (Long authorityId : authorityIds) {
			Authority authority = getAuthorityById(authorityId);
			account.addAuthority(authority);
		}

		return account;
	}

	private Authority getAuthorityById(Long authorityId) {
		return jdbcTemplate.queryForObject("select id, authority_code, authority_name, description from authority where id=?",
				this::mapRowToAuthority, authorityId);
	}

	private Authority mapRowToAuthority(ResultSet rs, int rowNum) throws SQLException {
		return new Authority(rs.getLong("id"), rs.getString("authority_code"), rs.getString("authority_name"),
				rs.getString("description"));
	}

	private Account mapRowToAccount(ResultSet rs, int rowNum) throws SQLException {
		return new Account(rs.getLong("id"), rs.getString("account_number"), rs.getString("account_name"));
	}

	private Account addAccountDetailWithJdbcTemplate(Account account) {
		//插入Account基础信息
		Long accountId = saveAccountWithJdbcTemplate(account);

		account.setId(accountId);

		//插入Account与Authority的对应关系
		List<Authority> authorityList = account.getAuthorities();
		for (Authority authority : authorityList) {
			saveAuthorityToAccountWithJdbcTemplate(authority, accountId);
		}

		return account;
	}

	private Long saveAccountWithJdbcTemplate(Account account) {

		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(
						"insert into `account` (account_number, account_name) values (?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, account.getAccountNumber());
				ps.setString(2, account.getAccountName());
				return ps;
			}
		};

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc, keyHolder);

		return keyHolder.getKey().longValue();
	}

	private void saveAuthorityToAccountWithJdbcTemplate(Authority authority, long accountId) {
		jdbcTemplate.update("insert into account_authority (account_id, authority_id) " + "values (?, ?)", accountId, authority.getId());
	}

	private Account addAccountDetailWithSimpleJdbcInsert(Account account) {
		//插入Account基础信息
		Long accountId = saveAccountWithSimpleJdbcInsert(account);

		account.setId(accountId);
		
		//插入Account与Authority的对应关系
		List<Authority> authorityList = account.getAuthorities();
		for (Authority authority : authorityList) {
			saveAuthorityToAccountWithSimpleJdbcInsert(authority, accountId);
		}

		return account;
	}

	private Long saveAccountWithSimpleJdbcInsert(Account account) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("account_number", account.getAccountNumber());
		values.put("account_name", account.getAccountName());

		Long accountId = accountInserter.executeAndReturnKey(values).longValue();
		return accountId;
	}

	private void saveAuthorityToAccountWithSimpleJdbcInsert(Authority authority, long accountId) {
		Map<String, Object> values = new HashMap<>();
		values.put("account_id", accountId);
		values.put("authority_id", authority.getId());
		accountAuthorityInserter.execute(values);
	}
}
