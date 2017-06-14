package com.jnote.service;

import java.util.List;

import com.jnote.vo.Folder;

public interface IFolderService {
	public List findRootFolder(int userid);
	public void sava(Folder folder);
}
