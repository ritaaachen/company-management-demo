package com.company.service;

import com.company.entity.User;
import com.company.repository.UserDAO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;

	public void createUser(User user) {
		this.userDAO.createUser(user);
	}

	public void updateUser(User user) {
		this.userDAO.updateUser(user);
	}

	public List<User> getUsers(int companyId) {
		return this.userDAO.getUsers(companyId);
	}

	public User getUserById(int companyId, int userId) {
		return this.userDAO.getUserById(companyId, userId);
	}

	public String deleteUserById(int companyId, int userId) {
		if(this.userDAO.getUserById(companyId, userId) != null) {
			this.userDAO.deleteUser(userId);
		}
		else{
			return "User Id is not existed.";
		}
		System.out.println("delete in service");
		return "success";
	}
}
