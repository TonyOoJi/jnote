package com.jnote.service;

import com.jnote.vo.User;

public interface IUserService {
	boolean saveUser(User user);
	boolean login(User user);
//	boolean checkUser(String username);
	boolean check(String username);
	User getUserById(Integer id);
}
