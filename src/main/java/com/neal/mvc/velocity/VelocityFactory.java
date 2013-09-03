package com.neal.mvc.velocity;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class VelocityFactory {
	public static void init(){
		System.out.println("current path:"+ VelocityFactory.class.getResource(".").getPath());
		System.out.println("home path:"+System.getProperty("user.dir"));
		Velocity.init("target/classes/velocity.properties");
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("key", "value");
		Template template = null;
			
		template = Velocity.getTemplate(System.getProperty("user.dir")+"/target/work/webapp/WEB-INF/vm_template/mytemplate.vm");
		
		StringWriter sw = new StringWriter();
		template.merge(velocityContext, sw);
		System.out.println(sw);
	}
	public static void main(String[] args) {
		VelocityFactory.init();
	}
}
