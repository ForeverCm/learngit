package com.f313.service;

import java.sql.SQLException;
import java.util.List;

import com.f313.entity.Seat;

public interface SeatService {
	public List<Seat> findAllSeat() throws SQLException;
	public Seat findById(String uid, String fid) throws Exception;
	public boolean addSeat(String uid, String fid) throws Exception;
	public void deleteSeat(String uid,String fid) throws SQLException;
}
