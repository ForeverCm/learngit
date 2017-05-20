package com.f313.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.f313.dao.SeatDAO;
import com.f313.entity.Flight;
import com.f313.entity.Seat;
import com.f313.service.SeatService;

public class SeatServiceImpl implements SeatService{
	
	private static final SeatDAO seatDao;
	static{
		seatDao = new SeatDAO();
	}
	
	public List<Seat> findAllSeat() throws SQLException{
		List<Seat> list = null;
		list = seatDao.findAllSeat();
		return list;
	}
	public Seat findById(String uid, String fid) throws Exception{
		Seat seat = null;
		seat = seatDao.findById(uid, fid);
		return seat;
	}
	public boolean addSeat(String uid, String fid) throws Exception{
		return seatDao.addSeat(uid, fid);
	}
	public void deleteSeat(String uid,String fid) throws SQLException{
		seatDao.deleteSeat(uid, fid);
	}
}
