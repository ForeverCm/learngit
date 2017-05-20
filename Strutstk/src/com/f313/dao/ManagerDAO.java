package com.f313.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.f313.entity.Flight;
import com.f313.entity.Manager;
import com.f313.entity.User;
import com.f313.util.JdbcUtils;

public class ManagerDAO {
	private static final boolean D = true;
	private static final Logger log = Logger.getLogger("ManagerDAO");
	
	public Manager findById(String id) throws Exception{
		Connection conn = JdbcUtils.getConnection();
		//PassagerBean passager = null;
		//User user = null;
		Manager manager = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			st = conn.createStatement();		
			String sql = "select * from manager where ManagerId='"+id+"'";
			rs = st.executeQuery(sql);				
			while(rs.next()){								
				manager = new Manager();	
				manager.setManagerId(rs.getString("ManagerId"));
				manager.setManagerPass(rs.getString("ManagerPass"));
				manager.setManagerIden("GuanLiYuan");
			}
		}catch(Exception e){
			if(D)log.log(Level.WARNING,"-- ManagerDAO findById() --");
			e.printStackTrace();
			throw e;
		}finally{
			JdbcUtils.free(rs, st, conn);
			}
		return manager;
	}
}
