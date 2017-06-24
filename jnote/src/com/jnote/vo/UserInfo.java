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
    public UserInfo(Integer userid) {
        super(userid);        
    }
    
    /** full constructor */
    public UserInfo(Integer userid, String email, String tel, String headurl) {
        super(userid, email, tel, headurl);        
    }
   
}
