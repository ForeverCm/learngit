package com.f313.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.f313.dao.UserDAO;
import com.f313.entity.Flight;
import com.f313.entity.User;
import com.f313.service.UserService;

public class UserServiceImpl implements UserService{
	//private static final PassagerDAO passagerDAO;
	private static final UserDAO userDao;
	
	static{
		userDao = new UserDAO();
	}
	
	public User findUserById(String userId) throws Exception{
		User user = null;
		user = userDao.findById(userId);
		return user;
	}
	public List<User> findAllUser() throws SQLException{
		List<User> list = null;
		list = userDao.findAllUser();
		return list;
	}
	@Override
	public boolean register(String id,String psw) throws Exception{
		if(id==null || psw==null || id.equals("") || psw.equals(""))
			return false;
		try {
			User p = userDao.findById(id);
			if(p == null){
				if( userDao.addUser(id, psw)){
					return true;
				}
			}
		} catch (Exception e) {
			// TODO ≤‚ ‘
			System.out.println("---  UserServiceImpl register()---");
			e.printStackTrace();
			throw e;
		}
		return false;
	}
	
	@Override
	public boolean login(String id,String psw) throws Exception{
		if(id==null || psw==null || id.equals("") || psw.equals(""))
			return false;
		try {
			User p = userDao.findById(id);
			if(p == null)
				return false;
			if(p.getUserPass().equals(psw))
				return true;
		} catch (Exception e) {
			// TODO ≤‚ ‘
			System.out.println("---  UserServiceImpl login()---");
			e.printStackTrace();
			throw e;
		}
		return false;
	}
}
