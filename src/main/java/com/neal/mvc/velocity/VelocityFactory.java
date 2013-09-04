package com.neal.mvc.velocity;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class VelocityFactory {
	public static void init(){
		System.out.println("current path:"+ VelocityFactory.class.getResource(".").getPath());
		String home =System.getProperty("user.dir");
		System.out.println("home path:" + home);
//		Velocity.init("target/work/webapp/WEB-INF/classes/velocity.properties");
		Velocity.init();
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("key", "value");
		Template template = null;
			
		template = Velocity.getTemplate(home + "/src/main/resources/mytemplate.vm");
		
		StringWriter sw = new StringWriter();
		template.merge(velocityContext, sw);
		System.out.println(sw);
	}
	public static void main(String[] args) {
		VelocityFactory.init();
	}
}
