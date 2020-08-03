package com.company.controller;

import com.company.entity.Company;
import com.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.company.entity.User;
import com.company.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	// create user
	@PostMapping(value="/{companyId}/create")
	public @ResponseBody String createUser(@RequestParam String userName, @RequestParam String position,
																				 @RequestParam String phone, @RequestParam String email,
																				 @PathVariable int companyId) {
		System.out.println("===========  createUser");
		this.userService.createUser(userSetting(userName, position, phone, email, companyId));
		return "Saved";
	}

	// get all user
	@GetMapping(value="/{companyId}/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers(@PathVariable int companyId) {
		System.out.println("===========  getAllUsers");
		return this.userService.getUsers(companyId);
	}

	// get user by user id
	@GetMapping(value="/{companyId}/{userId}")
	public User getUserById(@PathVariable int companyId, @PathVariable int userId) {
		System.out.println("===========  getUserById");
		return this.userService.getUserById(companyId, userId);
	}

	// delete user
	@PostMapping(value="/{companyId}/delete")
	public String deleteUserById(@PathVariable int companyId, @RequestParam int userId) {
		System.out.println("===========  deleteUserById");
		return this.userService.deleteUserById(companyId, userId);
	}

	// update user
	@PostMapping(value="/{companyId}/update")
	public @ResponseBody String updateCompany (@RequestParam String userName, @RequestParam String position,
																						 @RequestParam String phone, @RequestParam String email,
																						 @RequestParam int userId, @PathVariable int companyId) {
		System.out.println("===========  updateUser");
		User user = userSetting(userName, position, phone, email, companyId);
		user.setUserId(userId);
		this.userService.updateUser(user);
		return "Saved";
	}

	private User userSetting(String userName, String position, String phone, String email, int companyId) {
		User u = new User();
		u.setUserName(userName);
		u.setPosition(position);
		u.setPhone(phone);
		u.setEmail(email);
		u.setCompanyId(companyId);
		return u;
	}

}
