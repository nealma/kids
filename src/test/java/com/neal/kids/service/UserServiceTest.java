package com.neal.kids.service;

import java.util.Arrays;

import junit.framework.TestCase;

import com.neal.kids.service.impl.UserService;

public class UserServiceTest extends TestCase {
	
	public void testPrint(){
		IUserService us = new UserService();
		String result = us.print();
		System.out.println("result:"+result);
	}
	
	public void testStartWith(){
		String url = "http://192.168.1.6:8080/kids/action/user/createUser/neal/";
		System.out.println(url.indexOf("/action/"));
		System.out.println(url.substring(url.indexOf("/action/")).replace("/action", "").split("/"));
		System.out.println(Arrays.asList(url.substring(url.indexOf("/action/")).replace("/action", "").split("/")));
	}
}
