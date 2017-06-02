package com.jnote.vo;
// default package



/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */
public class UserInfo extends AbstractUserInfo implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public UserInfo() {
    }

	/** minimal constructor */
    public UserInfo(User user) {
        super(user);        
    }
    
    /** full constructor */
    public UserInfo(User user, String email, String tel, String headurl) {
        super(user, email, tel, headurl);        
    }
   
}
