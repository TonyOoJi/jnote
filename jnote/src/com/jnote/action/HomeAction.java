package com.jnote.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.jnote.action.HomeAction.Row;
import com.jnote.service.impl.ServiceManager;
import com.jnote.vo.AbstractFolder;
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
		Folder folder = new Folder();
		folder.setUser((User) session.getAttribute("user"));
		folder.setFoldername(request.getParameter("foldername"));
		if(folder.getUser()!=null&&folder.getFoldername()!=null){
			//操作
			this.serviceManager.getFolderService().sava(folder);
			//得到所有根目录用于刷新
			rootFolderList = this.serviceManager.getFolderService().findRootFolder(folder.getUser().getUserid());
			List<Row> listTemp = new ArrayList();
			for(Object obj : rootFolderList){
				Folder f = (Folder)obj;
				int folderid = f.getFolderid();
				String foldername = f.getFoldername();
//				String row =  "<a href=\"#\" class=\"list-group-item glyphicon glyphicon-folder-close a-list\" name=\""+folderid+"\">&nbsp"+foldername+"</a>"; 
//				String rowTemp = new String();
//				rowTemp = row;
				Row row = new Row();
				row.folderid = folderid;
				row.foldername = foldername;
				listTemp.add(row);
			}
			result = "add success";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", result);
			map.put("list",listTemp);
//			System.out.println("maplist"+((AbstractFolder) ((List)map.get("list")).get(0)).getFolderid());
			JSONObject json = JSONObject.fromObject(map);
			result = json.toString();
//			System.out.println("json.toString.ok"+result);
			return SUCCESS;
		}
		return INPUT;
	}

	public class Row{
		public int folderid;
		public String foldername;
		public int getFolderid() {
			return folderid;
		}
		public void setFolderid(int folderid) {
			this.folderid = folderid;
		}
		public String getFoldername() {
			return foldername;
		}
		public void setFoldername(String foldername) {
			this.foldername = foldername;
		}
	}
	
}
