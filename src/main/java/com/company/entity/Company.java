package com.company.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="companies")
public class Company {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int companyId;

	private String completedBy;
	private String companyName;
	private String unifiedBusinessNumber;
	private String owner;
	private String address;
	private String phoneNumber;
	private String fax;
	private String note;

	@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private Date updateDate;

	public Company() {

	}

	public int getCompanyId() {
		return companyId;
	}

	public String getCompletedBy() {
		return completedBy;
	}

	public String getAddress() {
		return address;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getFax() {
		return fax;
	}

	public String getNote() {
		return note;
	}

	public String getOwner() {
		return owner;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getUnifiedBusinessNumber() {
		return unifiedBusinessNumber;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCompletedBy(String completedBy) {
		this.completedBy = completedBy;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setUnifiedBusinessNumber(String unifiedBusinessNumber) {
		this.unifiedBusinessNumber = unifiedBusinessNumber;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
