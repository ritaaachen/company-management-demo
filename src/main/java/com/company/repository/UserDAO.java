package com.company.repository;

import com.company.entity.Company;
import com.company.entity.User;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class UserDAO {
	JdbcTemplate jdbcTemplate;

	@Autowired
	public UserDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void createUser(User user) {
		String sql = "INSERT INTO user (user_name, position, phone, email, company_id)"
				+ " VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, user.getUserName(), user.getPosition(), user.getPhone(), user.getEmail(), user.getCompanyId());
	}

	public void updateUser(User user) {
		String sql = "UPDATE user SET user_name=?, position=?, phone=?, email=? WHERE company_id=? && user_id=?";
		jdbcTemplate.update(sql, user.getUserName(), user.getPosition(), user.getPhone(), user.getEmail(), user.getCompanyId(), user.getUserId());
	}

	public void deleteUser(int userId) {
		String sql = "DELETE FROM user WHERE user_id=?";
		System.out.println("delete in dao");
		jdbcTemplate.update(sql, userId);
	}

	public List<User> getUsers(int companyId) {
		String sql = "SELECT * FROM user where company_id =? order by user_id asc";
		List<User> list = jdbcTemplate.query(sql, new Object[] {companyId}, new UserDAO.UserMapper());
		return list;
	}

	public User getUserById(int companyId, int userId) {
		String sql = "SELECT * FROM user where user_id =? && company_id =?";
		User user;
		try {
			user = jdbcTemplate.queryForObject(sql, new Object[] {userId, companyId}, new UserDAO.UserMapper());
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
		return user;
	}

	private static final class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User u= new User();
			u.setUserId(rs.getInt("user_id"));
			u.setUserName(rs.getString("user_name"));
			u.setCompanyId(rs.getInt("company_id"));
			u.setEmail(rs.getString("email"));
			u.setPhone(rs.getString("phone"));
			u.setPosition(rs.getString("position"));
			return u;
		}
	}

}
