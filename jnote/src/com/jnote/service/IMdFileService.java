package com.jnote.service;

import java.util.List;

import com.jnote.vo.MdFile;

public interface IMdFileService {
	public List findChildFiles(int userid, int folderid);
	public void save(MdFile mf);
	public MdFile findByFileExample(MdFile mf);
	public MdFile findFileById(int fileid);
	public void updataMdFile(MdFile mf);
	public int deleteMdFile(int mdFileId);
	public int updataMdFileUrl(int mdfileid , String url);
	public List getSharedFile(int userid);
	public int deleteMdFileShare(int fileId);
}
