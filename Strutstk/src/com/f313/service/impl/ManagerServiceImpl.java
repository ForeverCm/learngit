package com.f313.service.impl;

import java.sql.SQLException;

import com.f313.dao.ManagerDAO;
import com.f313.dao.UserDAO;
import com.f313.entity.Manager;
import com.f313.entity.User;
import com.f313.service.ManagerService;
import com.f313.service.UserService;

public class ManagerServiceImpl implements ManagerService{
	//private static final PassagerDAO passagerDAO;
	private static final ManagerDAO managerDao;
	
	static{
		managerDao = new ManagerDAO();
	}
	@Override
	public boolean login(String id,String psw) throws Exception{
		if(id==null || psw==null || id.equals("") || psw.equals(""))
			return false;
		try {
			Manager p = managerDao.findById(id);
			if(p == null)
				return false;
			if(p.getManagerPass().equals(psw))
				return true;
		} catch (Exception e) {
			// TODO ≤‚ ‘
			System.out.println("---  ManagerServiceImpl login()---");
			e.printStackTrace();
			throw e;
		}
		return false;
	}
}
