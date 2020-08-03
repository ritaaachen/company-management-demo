package com.company.repository;

import com.company.entity.Company;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Slf4j
@Repository
public class CompanyDAO {

	JdbcTemplate jdbcTemplate;

	@Autowired
	public CompanyDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void createCompany(Company company) {
		String sql= "INSERT INTO companies (company_name, address, completed_by, fax,"
				+ "note, owner, phone_number, unified_business_number, update_date)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, current_timestamp)";

		jdbcTemplate.update(sql, company.getCompanyName(), company.getAddress(), company.getCompletedBy(),
				company.getFax(), company.getNote(), company.getOwner(), company.getPhoneNumber(),
				company.getUnifiedBusinessNumber());
	}

	public void updateCompany(Company company) {
		String sql = "UPDATE companies SET company_name=?, address=?, completed_by=?, "
					  + "fax=?, note=?, owner=?, phone_number=?, unified_business_number=?,"
					  + "update_date=current_timestamp WHERE company_id=?";
		jdbcTemplate.update(sql, company.getCompanyName(), company.getAddress(), company.getCompletedBy(),
				company.getFax(), company.getNote(), company.getOwner(), company.getPhoneNumber(),
				company.getUnifiedBusinessNumber(), company.getCompanyId());
	}

	public List<Company> getCompanies() {
		String sql = "SELECT * FROM companies order by company_id asc";
		List<Company> list = jdbcTemplate.query(sql, new Object[] {}, new CompanyMapper());
		return list;
	}

	public Company getByCompanyId(int id) {
		String sql = "SELECT * FROM companies WHERE company_id=? ";
		Company company;
		try {
			company = jdbcTemplate.queryForObject(sql, new Object[] {id}, new CompanyMapper());
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
		return company;
	}

	public void deleteCompany(int companyId) {
		String sql = "DELETE FROM companies WHERE company_id=?";
		jdbcTemplate.update(sql, companyId);
	}

	private static final class CompanyMapper implements RowMapper<Company> {
		public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
			Company c= new Company();
			c.setCompanyId(rs.getInt("company_id"));
			c.setCompanyName(rs.getString("company_name"));
			c.setAddress(rs.getString("address"));
			c.setCompletedBy(rs.getString("completed_by"));
			c.setFax(rs.getString("fax"));
			c.setNote(rs.getString("note"));
			c.setOwner(rs.getString("owner"));
			c.setPhoneNumber(rs.getString("phone_number"));
			c.setUnifiedBusinessNumber(rs.getString("unified_business_number"));
			c.setUpdateDate(rs.getDate("update_date"));
			return c;
		}
	}
}

