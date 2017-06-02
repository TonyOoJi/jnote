package com.jnote.vo;
// default package

import java.util.Set;


/**
 * User entity. @author MyEclipse Persistence Tools
 */
public class User extends AbstractUser implements java.io.Serializable {

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
   
}
