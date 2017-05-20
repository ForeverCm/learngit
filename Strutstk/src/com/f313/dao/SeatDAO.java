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
import com.f313.entity.Seat;
import com.f313.util.JdbcUtils;

public class SeatDAO {
	
	private static final boolean D = true;
	private static final Logger log = Logger.getLogger("SeatDAO");
	
	public void deleteSeat(String uid,String fid) throws SQLException{
		Connection conn = JdbcUtils.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try{
			st = conn.createStatement();		
			String sql = "delete from seat where UserId='"+uid+"' and FlightId='"+fid+"'";			
			st.executeUpdate(sql);
		}catch(SQLException e){
			if(D)log.log(Level.WARNING,"-- DeleteSeatServlet --");
			e.printStackTrace();
			throw e;
		}finally{
			JdbcUtils.free(rs, st, conn);
			}
	}
	
	
	public boolean addSeat(String uid, String fid) throws Exception{
		Connection conn = JdbcUtils.getConnection();
		PreparedStatement ps = null;
		String state = "success";
		boolean flag = true;
		
		try{
			conn = JdbcUtils.getConnection();
			String sql = "insert into seat values (?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, fid);
			ps.setString(3, state);
			int row = ps.executeUpdate();
			if (row > 0) {
				if(D)
					log.log(Level.WARNING,uid+"添加成功");
					
			}else{
				if(D){
					log.log(Level.WARNING,uid+"添加失败");
					// TODO 应该自定义异常
					flag = false;
				}
				throw new Exception("添加失败");
			}
		}catch(SQLException e){
			if(D)log.log(Level.WARNING,"--seatDAO addSeat() --");
			e.printStackTrace();
			throw e;
		}finally{
			ps.close();
			conn.close();
			}
		return flag;
	}
	
	
	
	
	public Seat findById(String uid, String fid) throws Exception{
		Connection conn = JdbcUtils.getConnection();
		Seat seat = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			st = conn.createStatement();		
			String sql = "select * from seat where UserId ='"+uid+"' and FlightId='"+fid+"'";
			rs = st.executeQuery(sql);				
			while(rs.next()){
				seat = new Seat();
				seat.setFid(rs.getString("FlightId"));
				seat.setUid(rs.getString("UserId"));
				seat.setState(rs.getString("State"));
			}
		}catch(Exception e){
			if(D)log.log(Level.WARNING,"-- findSeatById() --");
			e.printStackTrace();
			throw e;
		}finally{
			JdbcUtils.free(rs, st, conn);
			}
		return seat;
	}
	
	public List<Seat> findAllSeat() throws SQLException{
		Connection conn = JdbcUtils.getConnection();
		List<Seat> list = new ArrayList<Seat>();
		Statement st = null;
		ResultSet rs = null;
		try{
			st = conn.createStatement();		
			String sql = "select * from seat";			
			rs = st.executeQuery(sql);				
			while(rs.next()){	
				Seat seat = new Seat();
				seat.setUid(rs.getString("UserId"));
				seat.setFid(rs.getString("FlightId"));
				seat.setState(rs.getString("State"));
				list.add(seat); 			
			}
		}catch(SQLException e){
			if(D)log.log(Level.WARNING,"-- findAllSeat() --");
			e.printStackTrace();
			throw e;
		}finally{
			JdbcUtils.free(rs, st, conn);
			}
		return list;
	}

}
