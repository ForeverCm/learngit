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
 * ����ҳ���������
 */
public class DefaultEncodingFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse responst,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse rep = (HttpServletResponse)responst;
		req.setCharacterEncoding("utf-8");//�������ڽ�����Ϣ
		rep.setCharacterEncoding("utf-8");//��Ӧ��Ϣ
		chain.doFilter(req, rep);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}


}
