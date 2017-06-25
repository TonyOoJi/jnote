package com.jnote.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;

import jsx3.gui.TimePicker;

import net.sf.json.JSONObject;

import com.jnote.service.impl.ServiceManager;
import com.jnote.vo.Folder;
import com.jnote.vo.MdFile;
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
	private String fileResult;//文件信息
	private String resultMsg;
	private String shareUrl;

	
	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}
	
	public String getShareUrl(){
		return shareUrl;
	}	

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

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

	public String getFileResult() {
		return fileResult;
	}

	public void setFileResult(String fileResult) {
		this.fileResult = fileResult;
	}

	/**
	 * add root folder and refresh the root list
	 * @return
	 */
	public String addRootFolder() {
		Folder folder = new Folder();
		folder.setUser((User) session.getAttribute("user"));
		folder.setFoldername(request.getParameter("foldername"));
		if (folder.getUser() != null && folder.getFoldername() != null) {
			// 操作
			this.serviceManager.getFolderService().sava(folder);
			List rootList = getRootFolderList(folder);
			result = "add error";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", rootList);
			result = "add success";
			map.put("result", result);
			JSONObject json = JSONObject.fromObject(map);
			result = json.toString();
			// System.out.println("json.toString.ok"+result);
			return SUCCESS;
		}
		return INPUT;
	}
	/**
	 * get root folder
	 * @return
	 */
	public List getRootFolderList(Folder folder){
		// 得到所有根目录用于刷新
		rootFolderList = this.serviceManager.getFolderService().findRootFolder(folder.getUser().getUserid());
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
		return listTemp;
	}

	/**
	 * add child folder and refresh the child list
	 * @return
	 */
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
	
	/**
	 * add mdfile and refresh the child list
	 */
	public String addFile(){
//		System.out.println("come in addfile");
		int userid = ((User) session.getAttribute("user")).getUserid();
		Integer currentFolderId = Integer.parseInt(request.getParameter("currentFolderId"));
		String fileName = request.getParameter("fileName");
//		System.out.println(userid + "-" + currentFolderId+fileName);
		MdFile mf = new MdFile();
		mf.setUserid(userid);
		mf.setFilename(fileName);
		mf.setFolderid(currentFolderId);
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		mf.setAddtime(timeStamp);
		mf.setModifytime(timeStamp);
		if (mf.getUserid() != null && mf.getFilename() != null && mf.getFolderid() != null) {
			// 操作
//			System.out.println("save");
			serviceManager.getMdFileService().save(mf);
			// 调用函数得到所有子节点用于刷新
//			System.out.println("flush");
			getChildList(userid,currentFolderId);
			return SUCCESS;
		}else if(mf.getUserid() != null && mf.getFilename() != null && mf.getFolderid() == null){
			result = "没有选择文件夹";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", result);
			JSONObject json = JSONObject.fromObject(map);
			childListResult = json.toString();
//			System.out.println("json.toString.ok"+childListResult);
			return SUCCESS;
		}
		return INPUT;
	}
	
	/**
	 * when we add childfolder or add child file,we should refresh child folder and list
	 * this method can get the childlist message to json
	 * @param userid
	 * @param parentid
	 * @return
	 */
	public String getChildList(int userid,int parentid){
		session.setAttribute("parentid", Integer.parseInt(request.getParameter("parentid")));
		// 获取文件夹list，并封装
		childFolderList = serviceManager.getFolderService().findChildFolder(userid, parentid);
//		System.out.println(childFolderList.size());
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
		// 获取文件list，并封装
		childFileList = serviceManager.getMdFileService().findChildFiles(userid, parentid);
		List<FileAjax> listTempOfFile = new ArrayList<FileAjax>();
		if(childFileList != null){
			for (Object obj : childFileList) {
				MdFile mf = (MdFile) obj;
				FileAjax fileAjax = new FileAjax();
				fileAjax.setMdfileid(mf.getMdfileid());
				fileAjax.setFilename(mf.getFilename());
				fileAjax.setAddtime(mf.getAddtime().toString());
				fileAjax.setModifytime(mf.getModifytime().toString());
				listTempOfFile.add(fileAjax);
			}
		}
		result = "error";
		Map<String, Object> map = new HashMap<String, Object>();
		//folder add to map
		map.put("list", listTemp);
		//file add to map
		map.put("fileList", listTempOfFile);
		result = "success";
		map.put("result", result);
		JSONObject json = JSONObject.fromObject(map);
		childListResult = json.toString();
//		System.out.println("json.toString.ok"+childListResult);
		if(childListResult != null){
			return SUCCESS;
		}
		return INPUT;
	}

	/**
	 * click root folder and get the child folder and mdfile to json
	 * @return
	 */
	public String getChildListToJsonByFolderid() {
//		System.out.println("ajaxaction-getchildlisttojsonbyfolderid-in");
		session.setAttribute("parentid", Integer.parseInt(request.getParameter("parentid")));
//		System.out.println("p"+Integer.parseInt(request.getParameter("parentid")));
		Integer userid = ((User) session.getAttribute("user")).getUserid();
//		System.out.println("u"+userid);
		int parentid = Integer.parseInt(request.getParameter("parentid"));
//		System.out.println(userid+","+parentid);
		// 获取文件夹list，并封装
		childFolderList = serviceManager.getFolderService().findChildFolder(userid, parentid);
//		System.out.println("s"+childFolderList.size());
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
		// 获取文件list，并封装
		childFileList = serviceManager.getMdFileService().findChildFiles(userid, parentid);
//		System.out.println("getFileLists:"+childFileList.size());
		List<FileAjax> listTempOfFile = new ArrayList<FileAjax>();
		if(childFileList != null){
			for (Object obj : childFileList) {
				MdFile mf = (MdFile) obj;
				FileAjax fileAjax = new FileAjax();
				fileAjax.setMdfileid(mf.getMdfileid());
				fileAjax.setFilename(mf.getFilename());
				fileAjax.setAddtime(mf.getAddtime().toString());
				fileAjax.setModifytime(mf.getModifytime().toString());
//				System.out.println(fileAjax.getAddtime());
//				System.out.println(fileAjax.getFilename()+fileAjax.getMdfileid());
				listTempOfFile.add(fileAjax);
			}
		}
		result = "error";
		Map<String, Object> map = new HashMap<String, Object>();
		//folder add to map
		map.put("list", listTemp);
		//file add to map
		map.put("fileList", listTempOfFile);
		result = "success";
		map.put("result", result);
		//map to json to string
		JSONObject json = JSONObject.fromObject(map);
		childListResult = json.toString();
//		System.out.println("json.toString.ok"+childListResult);
		if(childListResult != null){	
			return SUCCESS;
		}
		return INPUT;
	}
	/**
	 * get mdfile content
	 * @return
	 */
	public String getMdFile(){
//		System.out.println("come in getMd");
		Integer fileId = Integer.parseInt(request.getParameter("mdFileId"));
//		System.out.println(fileId);
		MdFile mf = new MdFile();
		mf.setMdfileid(fileId);
		MdFile resultFile = serviceManager.getMdFileService().findByFileExample(mf);
//		System.out.println(resultFile);
		mdFileContentAjax mfca = new mdFileContentAjax();
		mfca.setFileId(resultFile.getMdfileid());
		mfca.setContent(resultFile.getContent());
		mfca.setTitle(resultFile.getFilename());
		Map<String,mdFileContentAjax> map = new HashMap<String,mdFileContentAjax>();
		map.put("file",mfca);
		JSONObject json = JSONObject.fromObject(map);
		fileResult = json.toString();
		if(fileResult != null){
//			System.out.println(fileResult);
			return SUCCESS;
		}
		return INPUT;
	}
	
	/**
	 * get mdfile content html
	 * @return
	 */
	public String getMdFileHtml(){
//		System.out.println("come in getMd");
		Integer fileId = Integer.parseInt(request.getParameter("mdFileId"));
//		System.out.println(fileId);
		MdFile mf = new MdFile();
		mf.setMdfileid(fileId);
		MdFile resultFile = serviceManager.getMdFileService().findByFileExample(mf);
//		System.out.println(resultFile);
		mdFileContentAjax mfca = new mdFileContentAjax();
		mfca.setFileId(resultFile.getMdfileid());
		mfca.setContent(resultFile.getMdhtml());
		mfca.setTitle(resultFile.getFilename());
		Map<String,mdFileContentAjax> map = new HashMap<String,mdFileContentAjax>();
		map.put("file",mfca);
		JSONObject json = JSONObject.fromObject(map);
		fileResult = json.toString();
		if(fileResult != null){
			return SUCCESS;
		}
		return INPUT;
	}
	
	/**
	 * update file
	 * @author YJ
	 * @return
	 */
	public String updataFile(){
//		System.out.println("updata in");
		Integer mdFileId = Integer.parseInt(request.getParameter("mdFileId"));
		String title = request.getParameter("fileTitle");
		String content = request.getParameter("content");
		String mdhtml = request.getParameter("mdhtml");
//		System.out.println(mdFileId + title + content + mdhtml);
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		MdFile mdFile = new MdFile();
		mdFile.setMdfileid(mdFileId);
		mdFile.setFilename(title);
		mdFile.setContent(content);
		mdFile.setModifytime(ts);
		mdFile.setMdhtml(mdhtml);
//		System.out.println("mdhtml:"+ mdFile.getMdhtml());
		serviceManager.getMdFileService().updataMdFile(mdFile);
		resultMsg = "保存成功";
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", resultMsg);
		JSONObject json = JSONObject.fromObject(map);
		resultMsg = json.toString();
		if(resultMsg != null){
//			System.out.println(resultMsg);
			return SUCCESS;
		}
		return INPUT;
	}
	
	/**
	 * delete root folder
	 */
	public String deleteFolder(){
		Folder folder = new Folder();
		folder.setUser((User) session.getAttribute("user"));
		folder.setFolderid(Integer.parseInt(request.getParameter("folderId")));
//		System.out.println(folder.getFolderid());
		String delResult = "删除失败";
		//delete folder
		int resultLine = serviceManager.getFolderService().delete(folder.getFolderid());//受影响的行数
//		System.out.println("ajaxaction-deletefolder-resultLine"+resultLine);
		if (resultLine == 1) {
			// 生成数据刷新
			List rootList = getRootFolderList(folder);
//			System.out.println("ajaxaction-deletefolder-rootlist-size"+rootList.size());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", rootList);
			delResult = "删除成功";
			map.put("result", delResult);
			JSONObject json = JSONObject.fromObject(map);
			result = json.toString();
//			System.out.println("json.toString.ok"+result);
			return SUCCESS;
		}
		return INPUT;
	}
	/**
	 * delete child folder
	 * @return
	 */
	public String deleteChildFolder(){
		Folder folder = new Folder();
		folder.setUser((User) session.getAttribute("user"));
		folder.setFolderid(Integer.parseInt(request.getParameter("folderId")));
		String delResult = "删除失败";
		//delete folder
		int resultLine = serviceManager.getFolderService().delete(folder.getFolderid());//受影响的行数
		Integer userid = folder.getUser().getUserid();
		Integer parentid = Integer.parseInt(request.getParameter("parentid"));
		if(resultLine == 1){
			if(userid != null && parentid != null){
				getChildList(userid,parentid);
				return SUCCESS;
			}
		}	
		return INPUT;
	}
	
	/**
	 * delete mdfile
	 */
	public String deleteFile(){
		int fileId = Integer.parseInt(request.getParameter("fileId"));
		Integer userid = ((User) session.getAttribute("user")).getUserid();
		Integer parentid = Integer.parseInt(request.getParameter("parentid"));
//		System.out.println("ajaxaction-delfile-data"+fileId+" "+userid+" "+parentid);
		//删除操作
		int resultLine = serviceManager.getMdFileService().deleteMdFile(fileId);
//		System.out.println("ajaxaction-delfile-resultLine:"+resultLine);
		if(resultLine == 1){
			if(userid != null && parentid != null){
				getChildList(userid,parentid);
				return SUCCESS;
			}
		}
		return INPUT;
	}
	
	/**
	 * delete mdfile Shared
	 */
	public String deleteFileShare(){
		int fileId = Integer.parseInt(request.getParameter("fileId"));
		Integer userid = ((User) session.getAttribute("user")).getUserid();
		//删除操作
		int resultLine = serviceManager.getMdFileService().deleteMdFileShare(fileId);
		if(resultLine == 1){
			List sharedList = serviceManager.getMdFileService().getSharedFile(userid);
			List<FileAjax> listTempOfFile = new ArrayList<FileAjax>();
			if(sharedList != null){
				for (Object obj : sharedList) {
					MdFile mf = (MdFile) obj;
					FileAjax fileAjax = new FileAjax();
					fileAjax.setMdfileid(mf.getMdfileid());
					fileAjax.setFilename(mf.getFilename());
					fileAjax.setAddtime(mf.getAddtime().toString());
					fileAjax.setModifytime(mf.getModifytime().toString());
					listTempOfFile.add(fileAjax);
				}
			}
			result = "取消分享成功";
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("fileList", listTempOfFile);
			map.put("result", result);
			JSONObject json = JSONObject.fromObject(map);
			result = json.toString();
			return SUCCESS;
		}
		return INPUT;
	}
	
	/**
	 * build shared url and return
	 * @return
	 */
	public String buildShareUrl(){
//		System.out.println("conein getShareUrl");
		int fileId = Integer.parseInt(request.getParameter("fileId"));
//		System.out.println(fileId);
		shareUrl = "note/share?fileid=" + fileId;
		Integer resultLine = serviceManager.getMdFileService().updataMdFileUrl(fileId,shareUrl);
//		System.out.println(shareUrl);
		Map<String, String> map = new HashMap<String, String>();
		map.put("shareUrl", shareUrl);
		JSONObject json = JSONObject.fromObject(map);
		shareUrl = json.toString();
//		System.out.println(shareUrl);
		if(shareUrl != null && resultLine == 1){
			return SUCCESS;
		}
		return INPUT;
	}
	
	/**
	 * update user information
	 * @author YJ
	 * @return
	 */
	public String updateUserInfo(){
		//
		//
		//
		//
		
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
		public String addtime;
	    public String modifytime;

		public String getAddtime() {
			return addtime;
		}

		public void setAddtime(String addtime) {
			this.addtime = addtime;
		}

		public String getModifytime() {
			return modifytime;
		}

		public void setModifytime(String modifytime) {
			this.modifytime = modifytime;
		}

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
	
	public class mdFileContentAjax{
		public Integer fileId;
		public String title;
		public String content;
		public Integer getFileId() {
			return fileId;
		}
		public void setFileId(Integer fileId) {
			this.fileId = fileId;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
	}

}
