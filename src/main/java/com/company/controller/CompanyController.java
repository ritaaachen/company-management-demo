package com.company.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.company.service.CompanyService;
import com.company.entity.Company;

import java.util.List;

@RestController
@RequestMapping(value = "/api/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	// add company
	@PostMapping(value="/add")
	public @ResponseBody String addCompany (@RequestParam String companyName, @RequestParam String completedBy
			, @RequestParam String unifiedBusinessNumber, @RequestParam String owner
			, @RequestParam String address, @RequestParam String phoneNumber
			, @RequestParam String fax, @RequestParam String note) {
		System.out.println("===========  addCompany");
		this.companyService.addCompany(companySetting(companyName, unifiedBusinessNumber, phoneNumber,
				owner, note, fax, completedBy, address));
		return "Saved";
	}

	// get all company
	@GetMapping(value="/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Company> getAllCompanies() {
		System.out.println("===========  getAllCompanies");
		return this.companyService.getCompanies();
	}

	// get company by company id
	@GetMapping(value="/{companyId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Company getCompanyById(@PathVariable("companyId") int companyId) {
		System.out.println("===========  getCompanyById");
		return this.companyService.getCompanyById(companyId);
	}

	// delete company
	@PostMapping(value="/delete")
	public String deleteCompanyById(@RequestParam int companyId) {
		System.out.println("===========  deleteCompanyById");
		System.out.println(this.companyService.deleteCompanyById(companyId));
		return this.companyService.deleteCompanyById(companyId);
	}

	// update company
	@PostMapping(value="/update")
	public @ResponseBody String updateCompany (@RequestParam int companyId, @RequestParam String companyName, @RequestParam String completedBy
			, @RequestParam String unifiedBusinessNumber, @RequestParam String owner
			, @RequestParam String address, @RequestParam String phoneNumber
			, @RequestParam String fax, @RequestParam String note) {
		System.out.println("===========  updateCompany");
		Company company = companySetting(companyName, unifiedBusinessNumber, phoneNumber,
				owner, note, fax, completedBy, address);
		company.setCompanyId(companyId);
		this.companyService.updateCompany(company);
		return "Saved";
	}

	private Company companySetting(String companyName, String unifiedBusinessNumber, String phoneNumber,
																 String owner, String note, String fax, String completedBy, String address) {
		Company c = new Company();
		c.setCompanyName(companyName);
		c.setUnifiedBusinessNumber(unifiedBusinessNumber);
		c.setPhoneNumber(phoneNumber);
		c.setOwner(owner);
		c.setNote(note);
		c.setFax(fax);
		c.setCompletedBy(completedBy);
		c.setAddress(address);
		return c;
	}
}
