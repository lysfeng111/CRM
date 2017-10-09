package com.rlg.crm.domain;

import java.sql.Timestamp;


/**
 * Serve entity. @author MyEclipse Persistence Tools
 */

public class Serve implements java.io.Serializable {

	// Fields
//serId,serType,customer,serCreaterId,serCreaterName,serCreaterDate,serRequest,serAllotId,serAllotName,
//serAllotDate,serHandle,serHandleId,serHandleName,serHandleDate,serResult,serSatisfcing,serResume,serState	
	
//SER_ID,SER_TYPE,SER_CUS_ID,SER_CREATER_ID,SER_CREATER_NAME,SER_CREATER_DATE,SER_REQUEST,SER_ALLOT_ID,SER_ALLOT_NAME,
//SER_ALLOT_DATE,SER_HANDLE,SER_HANDLE_ID,SER_HANDLE_NAME,SER_HANDLE_DATE,SER_RESULT,SER_SATISFCING,SER_RESUME,SER_STATE	
	
	private Integer serId;        // 客户服务编号（系统自动生成）          
	private ServeType serveType;    // 服务类型,用se_id关联
	
	private Customer customer;    // 客户，用客户id关联
	
	private Integer serCreaterId; // 服务创建人编号
	private String serCreaterName;//
	private Timestamp serCreaterDate;//服务创建时间
	
	private String serRequest;//服务请求
	
	private Integer serAllotId;   // 服务分配给的人的id
	private String serAllotName;  // 
	private Timestamp serAllotDate;// 服务分配时间
	private String serHandle;     // 服务处理
	private Integer serHandleId;  // 服务处理人编号
	private String serHandleName;//
	private Timestamp serHandleDate;//服务处理时间
	private String serResult;     // 服务处理结果
	private Long serSatisfcing;   // 服务满意度
	private String serResume;     // 服务概要
	private Long serState;        // 服务状态（新创建，已分配，已处理，已归档）
	// Constructors

	/** default constructor */
	public Serve() {
	}


	public Serve(Integer serId, ServeType serveType, Customer customer, Integer serCreaterId, String serCreaterName,
			Timestamp serCreaterDate, String serRequest, Integer serAllotId, String serAllotName,
			Timestamp serAllotDate, String serHandle, Integer serHandleId, String serHandleName,
			Timestamp serHandleDate, String serResult, Long serSatisfcing, String serResume, Long serState) {
		super();
		this.serId = serId;
		this.serveType = serveType;
		this.customer = customer;
		this.serCreaterId = serCreaterId;
		this.serCreaterName = serCreaterName;
		this.serCreaterDate = serCreaterDate;
		this.serRequest = serRequest;
		this.serAllotId = serAllotId;
		this.serAllotName = serAllotName;
		this.serAllotDate = serAllotDate;
		this.serHandle = serHandle;
		this.serHandleId = serHandleId;
		this.serHandleName = serHandleName;
		this.serHandleDate = serHandleDate;
		this.serResult = serResult;
		this.serSatisfcing = serSatisfcing;
		this.serResume = serResume;
		this.serState = serState;
	}


	public Integer getSerId() {
		return serId;
	}

	public void setSerId(Integer serId) {
		this.serId = serId;
	}

	public ServeType getServeType() {
		return serveType;
	}


	public void setServeType(ServeType serveType) {
		this.serveType = serveType;
	}


	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getSerCreaterId() {
		return serCreaterId;
	}

	public void setSerCreaterId(Integer serCreaterId) {
		this.serCreaterId = serCreaterId;
	}

	public String getSerCreaterName() {
		return serCreaterName;
	}

	public void setSerCreaterName(String serCreaterName) {
		this.serCreaterName = serCreaterName;
	}

	public Timestamp getSerCreaterDate() {
		return serCreaterDate;
	}

	public void setSerCreaterDate(Timestamp serCreaterDate) {
		this.serCreaterDate = serCreaterDate;
	}

	public String getSerRequest() {
		return serRequest;
	}

	public void setSerRequest(String serRequest) {
		this.serRequest = serRequest;
	}

	public Integer getSerAllotId() {
		return serAllotId;
	}

	public void setSerAllotId(Integer serAllotId) {
		this.serAllotId = serAllotId;
	}

	public String getSerAllotName() {
		return serAllotName;
	}

	public void setSerAllotName(String serAllotName) {
		this.serAllotName = serAllotName;
	}

	public Timestamp getSerAllotDate() {
		return serAllotDate;
	}

	public void setSerAllotDate(Timestamp serAllotDate) {
		this.serAllotDate = serAllotDate;
	}

	public String getSerHandle() {
		return serHandle;
	}

	public void setSerHandle(String serHandle) {
		this.serHandle = serHandle;
	}

	public Integer getSerHandleId() {
		return serHandleId;
	}

	public void setSerHandleId(Integer serHandleId) {
		this.serHandleId = serHandleId;
	}

	public String getSerHandleName() {
		return serHandleName;
	}

	public void setSerHandleName(String serHandleName) {
		this.serHandleName = serHandleName;
	}

	public Timestamp getSerHandleDate() {
		return serHandleDate;
	}

	public void setSerHandleDate(Timestamp serHandleDate) {
		this.serHandleDate = serHandleDate;
	}

	public String getSerResult() {
		return serResult;
	}

	public void setSerResult(String serResult) {
		this.serResult = serResult;
	}

	public Long getSerSatisfcing() {
		return serSatisfcing;
	}

	public void setSerSatisfcing(Long serSatisfcing) {
		this.serSatisfcing = serSatisfcing;
	}

	public String getSerResume() {
		return serResume;
	}

	public void setSerResume(String serResume) {
		this.serResume = serResume;
	}

	public Long getSerState() {
		return serState;
	}

	public void setSerState(Long serState) {
		this.serState = serState;
	}


	@Override
	public String toString() {
		return "Serve [serId=" + serId + ", serveType=" + serveType + ", customer=" + customer + ", serCreaterId="
				+ serCreaterId + ", serCreaterName=" + serCreaterName + ", serCreaterDate=" + serCreaterDate
				+ ", serRequest=" + serRequest + ", serAllotId=" + serAllotId + ", serAllotName=" + serAllotName
				+ ", serAllotDate=" + serAllotDate + ", serHandle=" + serHandle + ", serHandleId=" + serHandleId
				+ ", serHandleName=" + serHandleName + ", serHandleDate=" + serHandleDate + ", serResult=" + serResult
				+ ", serSatisfcing=" + serSatisfcing + ", serResume=" + serResume + ", serState=" + serState + "]";
	}

	
}