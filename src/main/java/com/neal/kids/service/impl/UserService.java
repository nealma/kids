package com.neal.kids.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.neal.kids.bean.User;
import com.neal.kids.service.IUserService;

public class UserService implements IUserService{

	@Override
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>(10);
		User user = null;
		for (int i = 0; i < 10; i++) {
			user = new User();
			user.setId(i);
			user.setName("name"+i);
			user.setPass("pass"+i);
			user.setUuid(UUID.randomUUID().toString());
			users.add(user);
		}
		return users;
	}

	
	
}