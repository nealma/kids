package com.neal.mvc.velocity;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.servlet.VelocityServlet;

@SuppressWarnings("deprecation")
@WebServlet("velocity")
public class SampleServlet extends VelocityServlet {

	private static final long serialVersionUID = 2481574910722020739L;

	public Template handleRequest(HttpServletRequest request,
			HttpServletResponse response, Context context) {
		
		String p1 = "Jakarta";
		String p2 = "Velocity";
		
		Vector<String> vec = new Vector<String>();
		vec.addElement(p1);
		vec.addElement(p2);
		
		context.put("list", vec);
		
		Template template = null;
		
		try {
			template = getTemplate("mytemplate.vm");
		} catch (ResourceNotFoundException rnfe) {
			// couldn't find the template
		} catch (ParseErrorException pee) {
			// syntax error : problem parsing the template
		} catch (Exception e) {
		}
		
		return template;
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Velocity.init();
		VelocityContext velocityContext = new VelocityContext();
		handleRequest(request, response, velocityContext);
	}
	
	
}
