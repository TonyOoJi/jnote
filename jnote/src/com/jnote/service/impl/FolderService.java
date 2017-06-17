package com.jnote.service.impl;

import java.util.List;

import com.jnote.dao.FolderDAO;
import com.jnote.service.IFolderService;
import com.jnote.vo.Folder;


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
	
	public List findChildFolder(int userid,int parentid){
		return folderDao.findChildFolder(userid, parentid);
	}

	public void sava(Folder folder) {
		folderDao.save(folder);
	}

	public Folder findFolderById(int folderid) {
//		System.out.println("comeinserviceoffolder");
		return folderDao.findFolderById(folderid);
	}
	
}
