package com.f313.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.f313.entity.Flight;
import com.f313.entity.User;
import com.f313.service.UserService;
import com.f313.service.impl.UserServiceImpl;
import com.f313.util.JdbcUtils;

import net.sf.json.JSONArray;

public class AllUserServlet extends HttpServlet {

	private static final boolean D = true;
	private static final Logger log = Logger.getLogger("AllUserServlet");
	
	private static final UserService userService;
	static{
		userService = new UserServiceImpl();
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<User> list = new ArrayList<User>();
		try {
			list = userService.findAllUser();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result=JSONArray.fromObject(list).toString();
		response.getOutputStream().write(result.getBytes());
	}
}
