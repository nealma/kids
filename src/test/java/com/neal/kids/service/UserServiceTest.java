package com.neal.kids.service;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import com.neal.kids.bean.User;
import com.neal.kids.service.impl.UserService;

public class UserServiceTest extends TestCase {
	
	public void testGetUsers(){
		IUserService us = new UserService();
		List<User> users = us.getUsers();
		System.out.println("users :" + users);
	}
	
	public void testStartWith(){
		String url = "http://localhost:8080/kids/action/user/createUser/neal/";
		System.out.println(url.indexOf("/action/"));
		System.out.println(url.substring(url.indexOf("/action/")).replace("/action", "").split("/"));
		System.out.println(Arrays.asList(url.substring(url.indexOf("/action/")).replace("/action", "").split("/")));
	}
}
