package com.rlg.crm.dao;

public class CriteriaServe {
	private String customerId;
	private String serResume; 
	private String serType;
	private String serCreaterDate;
	private String serState;

	public CriteriaServe(String customerId, String serResume, String serType, String serCreaterDate, String serState) {
		super();
		this.customerId = customerId;
		this.serResume = serResume;
		this.serType = serType;
		this.serCreaterDate = serCreaterDate;
		this.serState = serState;
	}

	public String getCustomerId() {
		if(customerId == null) {
			customerId = "%%";
		}else {
			customerId = "%"+ customerId +"%";
		}
		
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getSerResume() {
		if(serResume == null) {
			serResume = "%%";
		}else {
			serResume = "%"+ serResume +"%";
		}
		
		return serResume;
	}

	public void setSerResume(String serResume) {
		this.serResume = serResume;
	}

	public String getSerType() {
		if(serType == null || "全部".equals(serType)) {
			serType = "%%";
		}else {
			serType = "%"+ serType +"%";
		}
		return serType;
	}

	public void setSerType(String serType) {
		this.serType = serType;
	}

	public String getSerCreaterDate() {
		if(serCreaterDate == null) {
			serCreaterDate = "%%";
		}else {
			serCreaterDate = "%"+ serCreaterDate +"%";
		}
		return serCreaterDate;
	}

	public void setSerCreaterDate(String serCreaterDate) {
		this.serCreaterDate = serCreaterDate;
	}

	public String getSerState() {
		if(serState == null || "0".equals(serState)) {
			serState = "%%";
		}else {
			serState = "%"+ serState +"%";
		}
		return serState;
	}

	public void setSerState(String serState) {
		this.serState = serState;
	}
	
	
}
