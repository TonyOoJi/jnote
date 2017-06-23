package com.jnote.vo;
// default package

import java.sql.Timestamp;


/**
 * AbstractMdFile entity provides the base persistence definition of the MdFile entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMdFile  implements java.io.Serializable {


    // Fields    

     private Integer mdfileid;
     private String filename;
     private String content;
     private Timestamp addtime;
     private Timestamp modifytime;
     private Integer userid;
     private Integer folderid;
     private String shareurl;
     private String mdhtml;


    // Constructors

    /** default constructor */
    public AbstractMdFile() {
    }

	/** minimal constructor */
    public AbstractMdFile(Timestamp modifytime, Integer userid) {
        this.modifytime = modifytime;
        this.userid = userid;
    }
    
    /** full constructor */
    public AbstractMdFile(String filename, String content, Timestamp addtime, Timestamp modifytime, Integer userid, Integer folderid, String shareurl ,String mdhtml) {
        this.filename = filename;
        this.content = content;
        this.addtime = addtime;
        this.modifytime = modifytime;
        this.userid = userid;
        this.folderid = folderid;
        this.shareurl = shareurl;
        this.mdhtml = mdhtml;
    }

   
    // Property accessors

    public Integer getMdfileid() {
        return this.mdfileid;
    }
    
    public void setMdfileid(Integer mdfileid) {
        this.mdfileid = mdfileid;
    }

    public String getFilename() {
        return this.filename;
    }
    
    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getAddtime() {
        return this.addtime;
    }
    
    public void setAddtime(Timestamp addtime) {
        this.addtime = addtime;
    }

    public Timestamp getModifytime() {
        return this.modifytime;
    }
    
    public void setModifytime(Timestamp modifytime) {
        this.modifytime = modifytime;
    }

    public Integer getUserid() {
        return this.userid;
    }
    
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getFolderid() {
        return this.folderid;
    }
    
    public void setFolderid(Integer folderid) {
        this.folderid = folderid;
    }

    public String getShareurl() {
        return this.shareurl;
    }
    
    public void setShareurl(String shareurl) {
        this.shareurl = shareurl;
    }

	public String getMdhtml() {
		return mdhtml;
	}

	public void setMdhtml(String mdhtml) {
		this.mdhtml = mdhtml;
	}
    
    
   








}