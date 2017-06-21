package com.jnote.service;

import java.util.List;

import com.jnote.vo.Folder;

public interface IFolderService {
	public List findRootFolder(int userid);
	public void sava(Folder folder);
	public List findChildFolder(int userid,int parentid);
	public Folder findFolderById(int folderid);
	public int delete(Integer folderId);
}
