package com.jnote.vo;
// default package

import java.util.HashSet;
import java.util.Set;


/**
 * AbstractUser entity provides the base persistence definition of the User entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUser  implements java.io.Serializable {


    // Fields    

     private Integer userid;
     private String username;
     private String password;
     private Set folders = new HashSet(0);
     private Set userInfos = new HashSet(0);


    // Constructors

    /** default constructor */
    public AbstractUser() {
    }

	/** minimal constructor */
    public AbstractUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    /** full constructor */
    public AbstractUser(String username, String password, Set folders, Set userInfos) {
        this.username = username;
        this.password = password;
        this.folders = folders;
        this.userInfos = userInfos;
    }

   
    // Property accessors

    public Integer getUserid() {
        return this.userid;
    }
    
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public Set getFolders() {
        return this.folders;
    }
    
    public void setFolders(Set folders) {
        this.folders = folders;
    }

    public Set getUserInfos() {
        return this.userInfos;
    }
    
    public void setUserInfos(Set userInfos) {
        this.userInfos = userInfos;
    }
   








}