package com.jnote.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

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
	private String result;
	private Folder folder;
	
	public Folder getModel() {
		return folder;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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
//		System.out.println(userid);
		if(userid!=null){
			rootFolderList = serviceManager.getFolderService().findRootFolder(userid);
		}
//		if(rootFolderList.size()>0)System.out.println(((Folder)rootFolderList.get(0)).getFoldername());
		return SUCCESS;
	}
	
	public String addRootFolder(){
//		System.out.println("in ajax addrootfolder");
		Folder folder = new Folder();
		folder.setUser((User) session.getAttribute("user"));
//		String folderName1 = request.getParameter("foldername");
//		System.out.println(folderName1);
		folder.setFoldername(request.getParameter("foldername"));
//		System.out.println(folder.getUser().getUserid()+folder.getFoldername());
		if(folder.getUser()!=null&&folder.getFoldername()!=null){
//			System.out.println(this.serviceManager.getFolderService());
			this.serviceManager.getFolderService().sava(folder);
			result = "add success";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", result);
//			System.out.println("map.put");
			JSONObject json = JSONObject.fromObject(map);
			result = json.toString();
//			System.out.println("json.toString.ok"+result);
			return SUCCESS;
		}
		return INPUT;
	}

	
	
}
