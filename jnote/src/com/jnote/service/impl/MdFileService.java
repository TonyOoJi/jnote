package com.jnote.service.impl;

import java.util.List;

import com.jnote.dao.MdFileDAO;
import com.jnote.service.IMdFileService;
import com.jnote.vo.MdFile;


public class MdFileService implements IMdFileService {
	private MdFileDAO mdFileDao;
	
	public MdFileDAO getMdFileDao() {
		return mdFileDao;
	}

	public void setMdFileDao(MdFileDAO mdFileDao) {
		this.mdFileDao = mdFileDao;
	}

	public List findChildFiles(int userid, int folderid) {
		return mdFileDao.getChildMdFile(userid,folderid);
	}

	public void save(MdFile mf) {
		this.mdFileDao.save(mf);
	}

}
