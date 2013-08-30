package com.neal.mvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class URIFilter implements Filter {

	private String encoding = "UTF-8";
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String encodingParameter = filterConfig.getInitParameter("encoding");
		if(null != encodingParameter){
			this.encoding = encodingParameter;
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(this.encoding);
		response.setCharacterEncoding(this.encoding);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		//nothing to do
	}
	
}
