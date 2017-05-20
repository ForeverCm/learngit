package com.f313.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.f313.dao.UserDAO;
import com.f313.entity.Flight;
import com.f313.entity.User;
import com.f313.mark.Flag;
import com.f313.util.JdbcUtils;

import net.sf.json.JSONObject;

public class DeleteUserServlet extends HttpServlet{
	
	private static final boolean D = true;
	private static final Logger log = Logger.getLogger("DeleteUserServlet");
	
	public void deleteUser(String userId) throws SQLException{
		Connection conn = JdbcUtils.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try{
			st = conn.createStatement();		
			String sql = "delete from user where UserId='"+userId+"'";			
			st.executeUpdate(sql);
		}catch(SQLException e){
			if(D)log.log(Level.WARNING,"-- DeleteUserServlet --");
			e.printStackTrace();
			throw e;
		}finally{
			JdbcUtils.free(rs, st, conn);
			}
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String uid = request.getParameter("uid");
		
		UserDAO userDao = new UserDAO();
		User user = new User();
		
		/*
		try {
			user = userDao.findById(uid);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		
		if( uid == null || uid.equals("") || user == null ){
			Flag flag = new Flag();
			flag.setSign("two");
			flag.setUrl("/tk/manager.html");
			String reg = JSONObject.fromObject(flag).toString();
			response.getOutputStream().write(reg.getBytes());
		}
		else{
			try {
				deleteUser(uid);
				Flag flag = new Flag();
				flag.setSign("one");
				flag.setUrl("/tk/manager.html");
				String reg = JSONObject.fromObject(flag).toString();
				response.getOutputStream().write(reg.getBytes());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
