package com.jnote.service.impl;

import com.jnote.dao.UserInfoDAO;
import com.jnote.service.IUserInfoService;
import com.jnote.vo.UserInfo;


public class UserInfoService implements IUserInfoService {
	private UserInfoDAO userInfoDao;
	

	/* (non-Javadoc)
	 * @see com.jnote.service.IUserInfoService#updateUserInfo(com.jnote.vo.UserInfo)
	 */
	public UserInfoDAO getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(UserInfoDAO userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	public int updateUserInfo(UserInfo ui) {
		int resultLine = 0;
		if(ui.getUserid() != null){
			if(this.findUserInfoByUserId(ui.getUserid()) == null){
				this.userInfoDao.save(ui);
				return 1;
			}else{
				resultLine = this.userInfoDao.updateUserInfo(ui);
			}
		}
		return resultLine;
	}

	/* (non-Javadoc)
	 * @see com.jnote.service.IUserInfoService#findUserInfoByUserId(int)
	 */
	public UserInfo findUserInfoByUserId(int userid) {
		return this.userInfoDao.findByUserid(userid);
	}

}
