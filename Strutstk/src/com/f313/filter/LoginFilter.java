package com.f313.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author junming
 *	µÇÂ½¹ýÂËÆ÷
 */
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		try{
		if(request.getSession().getAttribute("user")==null)
			response.sendRedirect("login.jsp");
		else
			chain.doFilter(request, response);
		}catch(Exception e){
			response.sendRedirect("mistake.jsp");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
