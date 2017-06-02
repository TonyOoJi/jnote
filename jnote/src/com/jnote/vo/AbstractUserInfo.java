package com.jnote.vo;
// default package



/**
 * AbstractUserInfo entity provides the base persistence definition of the UserInfo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserInfo  implements java.io.Serializable {


    // Fields    

     private Integer userinfoid;
     private User user;
     private String email;
     private String tel;
     private String headurl;


    // Constructors

    /** default constructor */
    public AbstractUserInfo() {
    }

	/** minimal constructor */
    public AbstractUserInfo(User user) {
        this.user = user;
    }
    
    /** full constructor */
    public AbstractUserInfo(User user, String email, String tel, String headurl) {
        this.user = user;
        this.email = email;
        this.tel = tel;
        this.headurl = headurl;
    }

   
    // Property accessors

    public Integer getUserinfoid() {
        return this.userinfoid;
    }
    
    public void setUserinfoid(Integer userinfoid) {
        this.userinfoid = userinfoid;
    }

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getHeadurl() {
        return this.headurl;
    }
    
    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }
   








}