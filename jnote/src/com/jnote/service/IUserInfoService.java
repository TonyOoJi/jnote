package com.jnote.service;

import com.jnote.vo.UserInfo;

public interface IUserInfoService {
	public int updateUserInfo(UserInfo ui);
	public UserInfo findUserInfoByUserId(int userid);
	public int updateUserInfoUrl(UserInfo ui);
}
