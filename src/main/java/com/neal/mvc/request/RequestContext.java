package com.neal.mvc.request;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

public class RequestContext {
	
	private final static ThreadLocal<RequestContext> contexts = new ThreadLocal<RequestContext>();
	private ServletContext context;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HashMap<String,Cookie> cookies;
	private Object bean;

	public static RequestContext begin(ServletContext context,
					HttpServletRequest request,
					HttpServletResponse response) {
		
		RequestContext rc = new RequestContext();
		rc.setContext(context);
		rc.setRequest(request);
		rc.setResponse(response);
		HashMap<String,Cookie> cookies = new HashMap<String, Cookie>();
		Cookie[] _cookies = request.getCookies();
		if(null !=_cookies){
			for(Cookie _cookie : _cookies){
				cookies.put(_cookie.getName(), _cookie);
			}
		}
		rc.setCookies(cookies);
		contexts.set(rc);
		return rc;
	}
	
	public static RequestContext get() {
		
		return contexts.get();
		
	}
	
	public String getURI() {
		
		return contexts.get().getRequest().getRequestURI();
		
	}
	
	public void desory(){
		this.context	= null;
		this.request	= null;
		this.response	= null;
		this.cookies	= null;
		contexts.remove();
	}
	
	public <T> T form(Class<T> beanClass){
		T bean;
		try {
			bean = beanClass.newInstance();
			BeanUtils.populate(bean, get().request.getParameterMap());
			return bean;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*****************************************************************************
	 *								geter/seter
	 *****************************************************************************/
	
	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HashMap<String, Cookie> getCookies() {
		return cookies;
	}

	public void setCookies(HashMap<String, Cookie> cookies) {
		this.cookies = cookies;
	}
	
	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}
}