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

public class FlightDAO {
	
	private static final boolean D = true;
	private static final Logger log = Logger.getLogger("UserDAO");
	
	
	public void deleteFlight(String flightId) throws SQLException{
		Connection conn = JdbcUtils.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try{
			st = conn.createStatement();		
			String sql = "delete from flight where FlightId='"+flightId+"'";			
			st.executeUpdate(sql);
		}catch(SQLException e){
			if(D)log.log(Level.WARNING,"-- DeleteFlightServlet --");
			e.printStackTrace();
			throw e;
		}finally{
			JdbcUtils.free(rs, st, conn);
			}
	}
	
	public boolean addFlight(String id, String time, String start,
			String destination, int remainSeat, float price) throws Exception{
		Connection conn = JdbcUtils.getConnection();
		PreparedStatement ps = null;
		boolean flag = true;
		
		try{
			conn = JdbcUtils.getConnection();
			String sql = "insert into flight values (?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, time);
			ps.setString(3, start);
			ps.setString(4, destination);
			ps.setInt(5, remainSeat);
			ps.setFloat(6, price);
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
			if(D)log.log(Level.WARNING,"--flightDAO addFlight() --");
			e.printStackTrace();
			throw e;
		}finally{
			ps.close();
			conn.close();
			}
		return flag;
	}
	
	public List<Flight> findAllFlight() throws SQLException{
		List<Flight> list = new ArrayList<Flight>();
		
		Connection conn = JdbcUtils.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try{
			st = conn.createStatement();		
			String sql = "select * from flight";			
			rs = st.executeQuery(sql);				
			while(rs.next()){	
				Flight flight = new Flight();
				flight.setFlightId(rs.getString("FlightId"));
				flight.setStartTime(rs.getString("StartTime"));
				flight.setStartPoint(rs.getString("StartPoint"));
				flight.setDestination(rs.getString("Destination"));
				flight.setRemainSead(rs.getInt("RemainSeat"));
				flight.setPrice(rs.getFloat("Price"));
				list.add(flight); 			
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
	
	public Flight findById(String flightId) throws Exception{
		Connection conn = JdbcUtils.getConnection();
		Flight flight = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			st = conn.createStatement();		
			String sql = "select * from flight where FlightId='"+flightId+"'";
			rs = st.executeQuery(sql);				
			while(rs.next()){
			    flight = new Flight();
			    flight.setFlightId(rs.getString("FlightId"));
			    flight.setStartTime(rs.getString("StartTime"));
			    flight.setStartPoint(rs.getString("StartPoint"));
			    flight.setDestination(rs.getString("Destination"));
			    flight.setRemainSead(rs.getInt("RemainSeat"));
			    flight.setPrice(rs.getFloat("Price"));
			}
		}catch(Exception e){
			if(D)log.log(Level.WARNING,"-- findFlightById() --");
			e.printStackTrace();
			throw e;
		}finally{
			JdbcUtils.free(rs, st, conn);
			}
		return flight;
	}

}
