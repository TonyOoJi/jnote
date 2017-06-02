package com.jnote.vo;
// default package


import java.util.HashSet;
import java.util.Set;


/**
 * AbstractFolder entity provides the base persistence definition of the Folder entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractFolder  implements java.io.Serializable {


    // Fields    

     private Integer folderid;
     private Folder folder;
     private User user;
     private String foldername;
     private Set folders = new HashSet(0);


    // Constructors

    /** default constructor */
    public AbstractFolder() {
    }

	/** minimal constructor */
    public AbstractFolder(User user, String foldername) {
        this.user = user;
        this.foldername = foldername;
    }
    
    /** full constructor */
    public AbstractFolder(Folder folder, User user, String foldername, Set folders) {
        this.folder = folder;
        this.user = user;
        this.foldername = foldername;
        this.folders = folders;
    }

   
    // Property accessors

    public Integer getFolderid() {
        return this.folderid;
    }
    
    public void setFolderid(Integer folderid) {
        this.folderid = folderid;
    }

    public Folder getFolder() {
        return this.folder;
    }
    
    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public String getFoldername() {
        return this.foldername;
    }
    
    public void setFoldername(String foldername) {
        this.foldername = foldername;
    }

    public Set getFolders() {
        return this.folders;
    }
    
    public void setFolders(Set folders) {
        this.folders = folders;
    }
   








}