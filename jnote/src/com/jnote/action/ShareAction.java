package com.jnote.action;

import com.jnote.vo.MdFile;

public class ShareAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mdHtml;
	
	public String getMdHtml() {
		return mdHtml;
	}


	public void setMdHtml(String mdHtml) {
		this.mdHtml = mdHtml;
	}


	public String getSharePage(){
//		System.out.println("come in getSharePage");
		int fileid = Integer.parseInt(request.getParameter("fileid"));
		MdFile mf = this.serviceManager.getMdFileService().findFileById(fileid);
		if(mf == null){
			return INPUT;
		}else{
			if(mf.getMdhtml() == null){
				mdHtml = "<h1>禁止访问--没有访问权限，这篇文字没有被分享。</h1>";
				return SUCCESS;
			}else{
				mdHtml = mf.getMdhtml();
				return SUCCESS;
			}
		}
	}
}
