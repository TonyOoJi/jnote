package com.jnote.service;

import java.util.List;

import com.jnote.vo.MdFile;

public interface IMdFileService {
	public List findChildFiles(int userid, int folderid);
	public void save(MdFile mf);
	public MdFile findByFileExample(MdFile mf);
}
