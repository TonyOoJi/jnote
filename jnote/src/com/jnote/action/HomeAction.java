package com.jnote.action;

import java.util.List;

import com.jnote.service.impl.ServiceManager;
import com.jnote.vo.Folder;
import com.jnote.vo.User;

public class HomeAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServiceManager serviceManager;
	private List rootFolderList;
	
	public List getRootFolderList() {
		return rootFolderList;
	}

	public void setRootFolderList(List rootFolderList) {
		this.rootFolderList = rootFolderList;
	}

	public ServiceManager getServiceManager() {
		return serviceManager;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public String homePage(){
		Integer userid = null;
		if(session.getAttribute("user")!=null){
			userid = ((User)session.getAttribute("user")).getUserid();
		}
//		System.out.println(userid);
		if(userid!=null){
			rootFolderList = serviceManager.getFolderService().findRootFolder(userid);
		}
//		if(rootFolderList.size()>0)System.out.println(((Folder)rootFolderList.get(0)).getFoldername());
		return SUCCESS;
	}
	
}
