package com.jnote.vo;
// default package

import java.util.Set;



/**
 * Folder entity. @author MyEclipse Persistence Tools
 */
public class Folder extends AbstractFolder implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Folder() {
    }

	/** minimal constructor */
    public Folder(User user, String foldername) {
        super(user, foldername);        
    }
    
    /** full constructor */
    public Folder(Folder folder, User user, String foldername, Set folders) {
        super(folder, user, foldername, folders);        
    }
   
}
