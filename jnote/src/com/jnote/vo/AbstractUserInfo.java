package com.jnote.vo;
// default package



/**
 * AbstractUserInfo entity provides the base persistence definition of the UserInfo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserInfo  implements java.io.Serializable {


    // Fields    

     private Integer userinfoid;
     private Integer userid;
     private String email;
     private String tel;
     private String headurl;


    // Constructors

    /** default constructor */
    public AbstractUserInfo() {
    }

	/** minimal constructor */
    public AbstractUserInfo(Integer userid) {
        this.userid = userid;
    }
    
    /** full constructor */
    public AbstractUserInfo(Integer userid, String email, String tel, String headurl) {
        this.userid = userid;
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

    public Integer getUserid() {
        return this.userid;
    }
    
    public void setUserid(Integer userid) {
        this.userid = userid;
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