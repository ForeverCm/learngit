package com.f313.service;

import java.sql.SQLException;
import java.util.List;

import com.f313.entity.Flight;
import com.f313.entity.User;

public interface UserService {
	// µÇÂ½·þÎñ
	public boolean login(String id,String psw) throws Exception;
	public List<User> findAllUser() throws SQLException;
	public boolean register(String id, String psw)throws Exception;
	public User findUserById(String userId) throws Exception;
	
}
