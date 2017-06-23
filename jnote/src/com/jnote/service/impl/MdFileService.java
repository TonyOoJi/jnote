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

	/**
	 * Actually this method find mdFile by ID is not by example
	 */
	public MdFile findByFileExample(MdFile mf) {
//		List list = this.mdFileDao.findByExample(mf);
		MdFile mdFile = this.mdFileDao.findFileById(mf.getMdfileid());
		return mdFile;
	}
	
	public MdFile findFileById(int fileid){
		return this.mdFileDao.findFileById(fileid);
	}

	public void updataMdFile(MdFile mf) {
		try{
			this.mdFileDao.updataFile(mf);
		}catch(RuntimeException e){
			throw e;
		}
	}

	public int deleteMdFile(int mdFileId) {
		return mdFileDao.deleteById(mdFileId);
	}

	public int updataMdFileUrl(int mdfileid , String url) {
		int resultLine = this.mdFileDao.updataFileUrl(mdfileid, url);
		return resultLine;
	}

}
