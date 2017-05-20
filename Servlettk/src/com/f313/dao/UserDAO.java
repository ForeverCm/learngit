package com.f313.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.f313.entity.Flight;
import com.f313.entity.User;
import com.f313.util.JdbcUtils;

public class UserDAO {
	private static final boolean D = true;
	private static final Logger log = Logger.getLogger("UserDAO");
	
	public List<User> findAllUser() throws SQLException{
		Connection conn = JdbcUtils.getConnection();
		List<User> list = new ArrayList<User>();
		Statement st = null;
		ResultSet rs = null;
		try{
			st = conn.createStatement();		
			String sql = "select * from user";			
			rs = st.executeQuery(sql);				
			while(rs.next()){	
				User user = new User();
				user.setUserId(rs.getString("UserId"));
				user.setUserPass("****");
				user.setUserIden(rs.getString("UserIden"));
				list.add(user); 			
			}
		}catch(SQLException e){
			if(D)log.log(Level.WARNING,"-- findAllUser() --");
			e.printStackTrace();
			throw e;
		}finally{
			JdbcUtils.free(rs, st, conn);
			}
		return list;
	}
	
	public boolean addUser(String id, String psw) throws Exception{
		Connection conn = JdbcUtils.getConnection();
		PreparedStatement ps = null;
		String userIden = "yonghu";
		boolean flag = true;
		
		try{
			conn = JdbcUtils.getConnection();
			String sql = "insert into user values (?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, psw);
			ps.setString(3, userIden);
			int row = ps.executeUpdate();
			if (row > 0) {
				if(D)
					log.log(Level.WARNING,id+"添加成功");
					
			}else{
				if(D){
					log.log(Level.WARNING,id+"添加失败");
					// TODO 应该自定义异常
					flag = false;
				}
				throw new Exception("添加失败");
			}
		}catch(SQLException e){
			if(D)log.log(Level.WARNING,"--UserDAO addUser() --");
			e.printStackTrace();
			throw e;
		}finally{
			ps.close();
			conn.close();
			}
		return flag;
	}
	
	
	
	
	
	public User findById(String id) throws Exception{
		Connection conn = JdbcUtils.getConnection();
		//PassagerBean passager = null;
		User user = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			st = conn.createStatement();		
			String sql = "select * from user where UserId='"+id+"'";
			rs = st.executeQuery(sql);				
			while(rs.next()){								
				user = new User();	
				user.setUserId(rs.getString("UserId"));
				user.setUserPass(rs.getString("UserPass"));
				user.setUserId("yonghu");	
			}
		}catch(Exception e){
			if(D)log.log(Level.WARNING,"-- UserDAO findById() --");
			e.printStackTrace();
			throw e;
		}finally{
			JdbcUtils.free(rs, st, conn);
			}
		return user;
	}
}
