package com.rlg.crm.dao;

public class ResultForContri {
	private int rfcCusId;
	private String rfcCusName;
	private double rfcSumMoney;
	public ResultForContri(int rfcCusId, String rfcCusName, double rfcSumMoney) {
		super();
		this.rfcCusId = rfcCusId;
		this.rfcCusName = rfcCusName;
		this.rfcSumMoney = rfcSumMoney;
	}
	public ResultForContri() {
		super();
	}
	public int getRfcCusId() {
		return rfcCusId;
	}
	public void setRfcCusId(int rfcCusId) {
		this.rfcCusId = rfcCusId;
	}
	public String getRfcCusName() {
		return rfcCusName;
	}
	public void setRfcCusName(String rfcCusName) {
		this.rfcCusName = rfcCusName;
	}
	public double getRfcSumMoney() {
		return rfcSumMoney;
	}
	public void setRfcSumMoney(double rfcSumMoney) {
		this.rfcSumMoney = rfcSumMoney;
	}
	
}
