package com.jnote.service.impl;

import java.util.List;

import com.jnote.dao.FolderDAO;
import com.jnote.service.IFolderService;


public class FolderService implements IFolderService {
	private FolderDAO folderDao;

	public FolderDAO getFolderDao() {
		return folderDao;
	}

	public void setFolderDao(FolderDAO folderDao) {
		this.folderDao = folderDao;
	}

	public List findRootFolder(int userid) {
		
		return folderDao.findRootFolder(userid);
	}
	
}
