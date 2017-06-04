package com.jnote.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jnote.dao.UserDAO;
import com.jnote.service.IUserService;
import com.jnote.vo.User;


public class UserService implements IUserService{
	private UserDAO userDao;

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public boolean saveUser(User user) {
//		System.out.println("username:"+user.getUsername());
		if(checkUser(user)){
			return false;
		}else{
			userDao.save(user);
			return true;
		}
	}

	public boolean login(User user) {
		List users = userDao.findByUsername(user.getUsername());
		if(users.size()==1){
			try {
//				System.out.println(user.getUsername()+user.getPassword());
//				System.out.println("servicelogin:"+((User)(users.get(0))).getPassword());
				if(user.getMd5Password().equals(((User)(users.get(0))).getPassword())){
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean checkUser(User user){
		List users = userDao.findByUsername(user.getUsername());
		if(users!=null&&users.size()==1){
			return true;
		}else 
			return false;
	}
}
