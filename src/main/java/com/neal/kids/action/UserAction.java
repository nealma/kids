package com.neal.kids.action;

import com.neal.kids.bean.User;
import com.neal.kids.service.IUserService;
import com.neal.kids.service.impl.UserService;
import com.neal.mvc.request.RequestContext;


public class UserAction{
	
	IUserService userService = new UserService();

	/**
	 * curl http://localhost:8080/kids/action/user/createUser?name=neal
	 */
	public void createUser(){
		System.out.println("run createUser ");
		User user = RequestContext.get().form(User.class);
		System.out.println("name="+user.getName());
	}

	public void modifyUser(){
		System.out.println("run modifyUser");
	}
	
	public void eraseUser(){
		System.out.println("run eraseUser");
	}
	/**
	 * curl http://192.168.1.6:8080/kids/action/user/getuser
	 */
	public void getUsers(){
		userService.getUsers();
		System.out.println("run getUser");
	}
}
