package com.jnote.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.jnote.action.AjaxAction.FolderAjax;
import com.jnote.service.impl.ServiceManager;
import com.jnote.vo.AbstractUser;
import com.jnote.vo.Folder;
import com.jnote.vo.User;

public class AjaxAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServiceManager serviceManager;
	private List rootFolderList; // 根目录文件夹
	private List childFolderList; // 子节点文件夹
	private List childFileList; // 子节点文件
	private String result; // 根目录json结果
	private String childListResult; // 子节点json结果

	public List getChildFolderList() {
		return childFolderList;
	}

	public void setChildFolderList(List childFolderList) {
		this.childFolderList = childFolderList;
	}

	public List getChildFileList() {
		return childFileList;
	}

	public void setChildFileList(List childFileList) {
		this.childFileList = childFileList;
	}

	public String getChildListResult() {
		return childListResult;
	}

	public void setChildListResult(String childListResult) {
		this.childListResult = childListResult;
	}

	public ServiceManager getServiceManager() {
		return serviceManager;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public List getRootFolderList() {
		return rootFolderList;
	}

	public void setRootFolderList(List rootFolderList) {
		this.rootFolderList = rootFolderList;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String addRootFolder() {
		Folder folder = new Folder();
		folder.setUser((User) session.getAttribute("user"));
		folder.setFoldername(request.getParameter("foldername"));
		if (folder.getUser() != null && folder.getFoldername() != null) {
			// 操作
			this.serviceManager.getFolderService().sava(folder);
			// 得到所有根目录用于刷新
			rootFolderList = this.serviceManager.getFolderService()
					.findRootFolder(folder.getUser().getUserid());
			List<FolderAjax> listTemp = new ArrayList();
			for (Object obj : rootFolderList) {
				Folder f = (Folder) obj;
				int folderid = f.getFolderid();
				String foldername = f.getFoldername();
				// String row =
				// "<a href=\"#\" class=\"list-group-item glyphicon glyphicon-folder-close a-list\" name=\""+folderid+"\">&nbsp"+foldername+"</a>";
				// String rowTemp = new String();
				// rowTemp = row;
				FolderAjax row = new FolderAjax();
				row.folderid = folderid;
				row.foldername = foldername;
				listTemp.add(row);
			}
			result = "add error";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", listTemp);
			result = "add success";
			map.put("result", result);
			JSONObject json = JSONObject.fromObject(map);
			result = json.toString();
			// System.out.println("json.toString.ok"+result);
			return SUCCESS;
		}
		return INPUT;
	}

	public String addChildFolder() {
//		System.out.println(request.getParameter("currentFolderId"));
		int userid = ((User) session.getAttribute("user")).getUserid();
		Integer currentFolderId = Integer.parseInt(request.getParameter("currentFolderId"));
		String foldername = request.getParameter("foldername");
//		System.out.println(currentFolderId+foldername);
		Folder folder = new Folder();
		folder.setUser((User) session.getAttribute("user"));
		folder.setFoldername(foldername);
		folder.setFolder(serviceManager.getFolderService().findFolderById(currentFolderId));
		if (folder.getUser() != null && folder.getFoldername() != null && folder.getFolder() != null) {
			// 操作
			serviceManager.getFolderService().sava(folder);
			// 调用函数得到所有子节点用于刷新
			getChildList(userid,currentFolderId);
			return SUCCESS;
		}
		return INPUT;
	}
	
	public String getChildList(int userid,int parentid){
		
		childFolderList = serviceManager.getFolderService().findChildFolder(userid, parentid);
//		System.out.println(childFolderList.size());
		// 获取文件夹list，并封装
		List<FolderAjax> listTemp = new ArrayList<FolderAjax>();
		if(childFolderList != null){
			for (Object obj : childFolderList) {
				Folder f = (Folder) obj;
				int folderid = f.getFolderid();
				String foldername = f.getFoldername();
				FolderAjax fa = new FolderAjax();
				fa.folderid = folderid;
				fa.foldername = foldername;
				listTemp.add(fa);
			}
		}
		try {
			result = "error";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", listTemp);
			//file add to map
			
			result = "success";
			map.put("result", result);
			JSONObject json = JSONObject.fromObject(map);
			childListResult = json.toString();
//			System.out.println("json.toString.ok"+childListResult);
			return SUCCESS;
		} catch (RuntimeException e) {
			System.out.println(e);
		}
		return INPUT;
	}

	public String getChildListToJsonByFolderid() {
//		System.out.println("comein getChild");
		session.setAttribute("parentid", Integer.parseInt(request.getParameter("parentid")));
//		System.out.println("p"+Integer.parseInt(request.getParameter("parentid")));
		Integer userid = ((User) session.getAttribute("user")).getUserid();
//		System.out.println("u"+userid);
		int parentid = Integer.parseInt(request.getParameter("parentid"));
//		System.out.println(userid+","+parentid);
		childFolderList = serviceManager.getFolderService().findChildFolder(userid, parentid);
//		System.out.println("s"+childFolderList.size());
		// 获取文件夹list，并封装
		List<FolderAjax> listTemp = new ArrayList<FolderAjax>();
		if(childFolderList != null){
			for (Object obj : childFolderList) {
				Folder f = (Folder) obj;
				int folderid = f.getFolderid();
				String foldername = f.getFoldername();
				FolderAjax fa = new FolderAjax();
				fa.folderid = folderid;
				fa.foldername = foldername;
				listTemp.add(fa);
			}
		}
		try {
			result = "error";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", listTemp);
			//file add to map
			
			result = "success";
			map.put("result", result);
			JSONObject json = JSONObject.fromObject(map);
			childListResult = json.toString();
//			System.out.println("json.toString.ok"+childListResult);
			return SUCCESS;
		} catch (RuntimeException e) {
			System.out.println(e);
		}
		return INPUT;
	}

	/**
	 * 
	 * @author yj
	 * 
	 */
	public class FolderAjax {
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

	public class FileAjax {
		public int mdfileid;
		public String filename;

		public int getMdfileid() {
			return mdfileid;
		}

		public void setMdfileid(int mdfileid) {
			this.mdfileid = mdfileid;
		}

		public String getFilename() {
			return filename;
		}

		public void setFilename(String filename) {
			this.filename = filename;
		}

	}

}
