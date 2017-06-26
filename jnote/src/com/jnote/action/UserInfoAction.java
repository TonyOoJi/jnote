package com.jnote.action;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.util.ServletContextAware;
import com.jnote.vo.User;
import com.jnote.vo.UserInfo;
import com.opensymphony.xwork2.ModelDriven;

public class UserInfoAction extends BaseAction implements ModelDriven<UserInfo>,ServletContextAware{
	
	private static final long serialVersionUID = 1L;
	UserInfo userInfo = new UserInfo();//模型驱动的model
	private Integer userid;
	private File upload;//上传的文件名
	private String uploadFileName;//上传文件的文件名
	private String uploadContentType;//文件的类型
	private ServletContext context;//用来获取路径的context
	private UserInfo userInfoExist;//首次访问用户信息页时可以
	
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}
	
	public UserInfo getUserInfoExist() {
		return userInfoExist;
	}

	public void setUserInfoExist(UserInfo userInfoExist) {
		this.userInfoExist = userInfoExist;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.util.ServletContextAware#setServletContext(javax.servlet.ServletContext)
	 */
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public UserInfo getModel() {
		return userInfo;
	}
	
	/**
	 * 头像的上传
	 * @return
	 */
	public String uploadFile(){
		/*获取存放上传文件的路径：项目根目录upload文件夹*/
	    String path = context.getRealPath("/head");
//	    this.getServletContext().getRealPath("/WEB-INF/upload");
//	    System.out.println(path);
	    /*上传到head文件夹下，保存名字为用户的id
	     * getUploadFileName().substring(getUploadFileName().lastIndexOf(".")); 文件获取后缀
	    */
	    if(session.getAttribute("user") != null && getUploadFileName() != null){
	    	String realFileName = ((User) session.getAttribute("user")).getUserid().toString() + getUploadFileName().substring(getUploadFileName().lastIndexOf("."));
		    File savefile = new File(path, realFileName);
		    try {
				FileUtils.copyFile(upload, savefile);
			} catch (IOException e) {
				e.printStackTrace();
				//抛出异常处理，返回INPUT--跳转出错页面--返回之前的界面
				return INPUT;
			}
//	    	System.out.println(savefile.getAbsolutePath());
	    	//保存路径
//	    	userInfo.setUserid(((User)session.getAttribute("user")).getUserid());
		    userInfo.setHeadurl("head/" + userInfo.getUserid() + getUploadFileName().substring(getUploadFileName().lastIndexOf(".")));
			if(serviceManager.getUserInfoService().updateUserInfoUrl(userInfo) == 1 && savefile.getAbsolutePath() != null){
				return SUCCESS;
			}
	    }
	    return INPUT;
	}
	
	/**
	 * 用户信息的保存
	 * @return
	 */
	public String updateUserInfo(){
//		System.out.println(session.getAttribute("user"));
//		userInfo.setUserid(userid);
		if(serviceManager.getUserInfoService().updateUserInfo(userInfo) == 1){
			return SUCCESS;
		}
		return INPUT;
	}

	/**
	 * get user Information
	 * @return
	 */
	public String getUserInfo(){
		if(session.getAttribute("user") != null){
			userid = ((User) session.getAttribute("user")).getUserid();
			userInfoExist = serviceManager.getUserInfoService().findUserInfoByUserId(userid);
			if(userid != null){
				userInfo.setUserid(userid);
				return SUCCESS;
			}
		}
		return INPUT;
	}
	
}
