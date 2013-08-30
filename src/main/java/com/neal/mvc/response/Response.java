package com.neal.mvc.response;

import com.neal.mvc.msg.Message;

public class Response {
	/**
	 * responseDataFormat
	 * 1:json, 2:xml
	 */
	public static void print(Message message, short responseDataFormat){
		if(responseDataFormat==1){
			printJson(message);
		}else if(responseDataFormat==2){
			printXml(message);
		}
	}
	
	private static void printJson(Message message){
		
	}
	
	private static void printXml(Message message){
		
	}
}
