package com.jnote.vo;
// default package

import java.sql.Timestamp;


/**
 * MdFile entity. @author MyEclipse Persistence Tools
 */
public class MdFile extends AbstractMdFile implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public MdFile() {
    }

	/** minimal constructor */
    public MdFile(Timestamp modifytime, Integer userid) {
        super(modifytime, userid);        
    }
    
    /** full constructor */
    public MdFile(String filename, String content, Timestamp addtime, Timestamp modifytime, Integer userid, Integer folderid, String shareurl ,String mdhtml) {
        super(filename, content, addtime, modifytime, userid, folderid, shareurl ,mdhtml);        
    }
   
}
