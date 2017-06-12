package com.jnote.action;

import com.jnote.service.impl.ServiceManager;

public class HomeAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServiceManager serviceManager;
	
	public ServiceManager getServiceManager() {
		return serviceManager;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public String execute(){
		
		return SUCCESS;
	}
	
}
