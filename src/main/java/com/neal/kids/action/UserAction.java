package com.neal.kids.action;

import com.neal.kids.bean.User;
import com.neal.mvc.request.RequestContext;


public class UserAction{

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
	 * curl http://localhost:8080/kids/action/user/getUser?id=2
	 */
	public void getUser(){
		RequestContext.get();
		System.out.println("run getUser");
	}
}
