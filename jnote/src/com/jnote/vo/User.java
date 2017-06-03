package com.jnote.vo;
// default package

import java.util.Set;


/**
 * User entity. @author MyEclipse Persistence Tools
 */
public class User extends AbstractUser implements java.io.Serializable {

	private String md5Password;
    // Constructors

    /** default constructor */
    public User() {
    }

	/** minimal constructor */
    public User(String username, String password) {
        super(username, password);        
    }
    
    /** full constructor */
    public User(String username, String password, Set folders, Set userInfos) {
        super(username, password, folders, userInfos);        
    }

	public String getMd5Password() throws Exception {
			return com.jnote.common.Encrypter.md5Encrypt(super.getPassword());
		
	}

	public void setMd5Password(String md5Password) {
		this.md5Password = md5Password;
	}
   
}
