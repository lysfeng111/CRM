package com.rlg.crm.dao;

public class CriteriaOrders {
	private String cusName;
	private String odrDate;
	public CriteriaOrders(String cusName, String odrDate) {
		super();
		this.cusName = cusName;
		this.odrDate = odrDate;
	}
	public String getCusName() {
		if(cusName == null) {
			cusName = "%%";
		}else {
			cusName = "%"+ cusName +"%";
		}
		
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getOdrDate() {
		if(odrDate == null || "全部".equals(odrDate)) {
			odrDate = "%%";
		}else {
			odrDate = "%"+ odrDate +"%";
		}
		return odrDate;
	}
	public void setOdrDate(String odrDate) {
		this.odrDate = odrDate;
	}
	
}
