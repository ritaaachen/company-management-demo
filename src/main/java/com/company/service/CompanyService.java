package com.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.entity.Company;
import com.company.repository.CompanyDAO;

import java.util.List;


@Service
public class CompanyService {

	@Autowired
	private CompanyDAO companyDAO;

	public void addCompany(Company company) {
		this.companyDAO.createCompany(company);
	}

	public void updateCompany(Company company) {
		this.companyDAO.updateCompany(company);
	}

	public List<Company> getCompanies() {
		return this.companyDAO.getCompanies();
	}

	public Company getCompanyById(int companyId) {
		return this.companyDAO.getByCompanyId(companyId);
	}

	public String deleteCompanyById(int companyId) {
		if(this.companyDAO.getByCompanyId(companyId) != null) {
			this.companyDAO.delete(companyId);
		}
		else{
			return "Company Id is not existed.";
		}
		return "success";
	}
}
