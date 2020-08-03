package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="user")
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int userId;

	private String userName;
	private String position;
	private String phone;
	private String email;
	private int companyId;
}
