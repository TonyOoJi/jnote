package com.jnote.action;

import java.util.List;

import com.jnote.service.impl.ServiceManager;
import com.jnote.vo.Folder;
import com.jnote.vo.User;
import com.opensymphony.xwork2.ModelDriven;

public class HomeAction extends BaseAction implements ModelDriven<Folder>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServiceManager serviceManager;
	private List rootFolderList;
	private Folder folder;
	
	public Folder getModel() {
		return folder;
	}

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
		if(userid!=null){
			rootFolderList = serviceManager.getFolderService().findRootFolder(userid);
			return SUCCESS;
		}
		return INPUT;
	}

}
