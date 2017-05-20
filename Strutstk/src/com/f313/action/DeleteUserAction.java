package com.f313.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.f313.dao.UserDAO;
import com.f313.entity.User;
import com.f313.mark.Flag;
import com.f313.util.JdbcUtils;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class DeleteUserAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
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
	private String res;
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res = res;
	}
	
	public String deleteUser(){
	String uid = request.getParameter("uid");
		
		UserDAO userDao = new UserDAO();
		User user = new User();
		
		
		try {
			user = userDao.findById(uid);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if( uid == null || uid.equals("") || user == null ){
			Flag flag = new Flag();
			flag.setSign("two");
			flag.setUrl("/tk/manager.html");
			res = JSONObject.fromObject(flag).toString();
			return "suc";
		}
		else{
			try {
				deleteUser(uid);
				Flag flag = new Flag();
				flag.setSign("one");
				flag.setUrl("/tk/manager.html");
				res = JSONObject.fromObject(flag).toString();
				return "suc";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "suc";
	}
	}

