package com.neal.mvc.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neal.mvc.request.RequestContext;

public class ActionServlet extends HttpServlet{

	private static final long serialVersionUID = -8342728825267873962L;
	private HashMap<String,Object> actions = new HashMap<String,Object>();
	private HashMap<String,Method> methods = new HashMap<String,Method>();
	private String[] packages;
	private String[] initActions;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ServletContext context = req.getServletContext();
		RequestContext requestContext = RequestContext.begin(context, req, resp);
		handle(requestContext, false);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ServletContext context = req.getServletContext();
		RequestContext requestContext = RequestContext.begin(context, req, resp);
		handle(requestContext, true);
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		
		String _packages = getInitParameter("action-packages");
		String _actions = getInitParameter("initial_actions");
		initActions = _actions.split(",");
		packages = _packages.split(",");
		for(String action:initActions){
			try {
				_LoadAction(action);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	public void handle(RequestContext requestContext,boolean isPost){
		String uri = RequestContext.get().getURI();
		
		if(uri.indexOf("/action/") == -1) return;
		
		String[] uris = uri.substring(uri.indexOf("/action/")).replace("/action", "").split("/");
		if(uris == null) return;
		
		for(int i=0;i<uris.length;i++){
			System.out.printf("[parameter%s] >> %s \n", i,uris[i]);
		}
		
		if(uris.length <3) return;
		
		try {
			Object _action = _LoadAction(uris[1]);
			Method _method = _GenActionMethod(_action,uris[2]);
			_method.invoke(_action);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	protected Object _LoadAction(String act_name)
			throws InstantiationException,IllegalAccessException {
			
			StringBuilder actionName = new StringBuilder();
			actionName.append(act_name);
			actionName.setCharAt(0, actionName.substring(0, 1).toUpperCase().charAt(0));
			actionName.append("Action");
			
			Object action = actions.get(act_name);
			if(action==null){
				for(String pkg : packages){
					StringBuilder clz = new StringBuilder();
					clz.append(pkg).append(".").append(actionName);
					action = _LoadActionOfFullName(act_name,clz.toString());
				}
			}
			return action;
		}
		protected Object _LoadActionOfFullName(String act_name,String clz) 
				throws InstantiationException,IllegalAccessException {
			Object action = null;
			try {
				action = Class.forName(clz).newInstance();
				if(!actions.containsKey(act_name)){
					synchronized(actions){
						actions.put(act_name, action);
						System.out.printf("[action put] %s >> %s \n", act_name, action);
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
			return action;
		}
		private Method _GenActionMethod(Object action, String methodName) {
			StringBuilder _key = new StringBuilder();
			_key.append(action.getClass().getSimpleName());
			_key.append(".");
			_key.append(methodName);
			String key = _key.toString().toLowerCase();
			if(methods.containsKey(key)){
				return methods.get(key);
			}
			
			for(Method m : action.getClass().getDeclaredMethods()){
				if(m.getModifiers() == Modifier.PUBLIC && m.getName().equalsIgnoreCase(methodName)){
					synchronized(methods){
						methods.put(key, m);
						System.out.printf("[method put] %s >> %s \n", key, m);
					}
					return m;
				}
			}
			return null;
		}
}