package com.rlg.crm.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Customer entity. @author MyEclipse Persistence Tools
 */

public class Customer implements java.io.Serializable {

	// Fields

	private Integer cusId;//编号
	private CustomerRank customerRank;//客户等级
	private Area area;//地区  下拉
	private String cusName;//名称
	private String cusNumber;//编号
	private String cusAddress;//地址 手填
	private String cusChangeOrigin;//改变起源
	private String cusChangeDescribe;
	private String cusSuccessProbability;
	private String cusManager;//客户经理
	private String cusAllotName;//分配
	private Timestamp cusAllotDate;
	private String cusPostalCode;//邮箱
	private String cusTelephone;
	private String cusFasimile;//传真
	private String cusUrl;
	private String cusRegisterNumber;
	private String cusLegalPerson;
	private Double cusRegisterMoney;
	private Double cusRmb;
	private String cusOpeningBank;
	private String cusBankAccount;
	private String cusCrownRent;
	private String cusStateTaxes;
	private String cusResume;
	private Long cusCreditworthiness;
	private Long cusStatisfcing;
	private Long cusState;
	//private List<Serve> serves = new ArrayList<Serve>();
	private List<Linkman> linkmans = new ArrayList<>();
	private List<Assort> assorts = new ArrayList<>();
	private List<Orders> orderses = new ArrayList<>();

	// Constructors

	/** default constructor */
	public Customer() {
	}

	/** full constructor */
	public Customer(CustomerRank customerRank, Area area, String cusName,
			String cusNumber, String cusAddress, String cusChangeOrigin,
			String cusChangeDescribe, String cusSuccessProbability,
			String cusManager, String cusAllotName, Timestamp cusAllotDate,
			String cusPostalCode, String cusTelephone, String cusFasimile,
			String cusUrl, String cusRegisterNumber, String cusLegalPerson,
			Double cusRegisterMoney, Double cusRmb, String cusOpeningBank,
			String cusBankAccount, String cusCrownRent, String cusStateTaxes,
			String cusResume, Long cusCreditworthiness, Long cusStatisfcing,
			Long cusState) {
		this.customerRank = customerRank;
		this.area = area;
		this.cusName = cusName;
		this.cusNumber = cusNumber;
		this.cusAddress = cusAddress;
		this.cusChangeOrigin = cusChangeOrigin;
		this.cusChangeDescribe = cusChangeDescribe;
		this.cusSuccessProbability = cusSuccessProbability;
		this.cusManager = cusManager;
		this.cusAllotName = cusAllotName;
		this.cusAllotDate = cusAllotDate;
		this.cusPostalCode = cusPostalCode;
		this.cusTelephone = cusTelephone;
		this.cusFasimile = cusFasimile;
		this.cusUrl = cusUrl;
		this.cusRegisterNumber = cusRegisterNumber;
		this.cusLegalPerson = cusLegalPerson;
		this.cusRegisterMoney = cusRegisterMoney;
		this.cusRmb = cusRmb;
		this.cusOpeningBank = cusOpeningBank;
		this.cusBankAccount = cusBankAccount;
		this.cusCrownRent = cusCrownRent;
		this.cusStateTaxes = cusStateTaxes;
		this.cusResume = cusResume;
		this.cusCreditworthiness = cusCreditworthiness;
		this.cusStatisfcing = cusStatisfcing;
		this.cusState = cusState;
		
	}

	// Property accessors

	public Integer getCusId() {
		return this.cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public CustomerRank getCustomerRank() {
		return this.customerRank;
	}

	public void setCustomerRank(CustomerRank customerRank) {
		this.customerRank = customerRank;
	}

	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getCusName() {
		return this.cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusNumber() {
		return this.cusNumber;
	}

	public void setCusNumber(String cusNumber) {
		this.cusNumber = cusNumber;
	}

	public String getCusAddress() {
		return this.cusAddress;
	}

	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}

	public String getCusChangeOrigin() {
		return this.cusChangeOrigin;
	}

	public void setCusChangeOrigin(String cusChangeOrigin) {
		this.cusChangeOrigin = cusChangeOrigin;
	}

	public String getCusChangeDescribe() {
		return this.cusChangeDescribe;
	}

	public void setCusChangeDescribe(String cusChangeDescribe) {
		this.cusChangeDescribe = cusChangeDescribe;
	}

	public String getCusSuccessProbability() {
		return this.cusSuccessProbability;
	}

	public void setCusSuccessProbability(String cusSuccessProbability) {
		this.cusSuccessProbability = cusSuccessProbability;
	}

	public String getCusManager() {
		return this.cusManager;
	}

	public void setCusManager(String cusManager) {
		this.cusManager = cusManager;
	}

	public String getCusAllotName() {
		return this.cusAllotName;
	}

	public void setCusAllotName(String cusAllotName) {
		this.cusAllotName = cusAllotName;
	}

	public Timestamp getCusAllotDate() {
		return this.cusAllotDate;
	}

	public void setCusAllotDate(Timestamp cusAllotDate) {
		this.cusAllotDate = cusAllotDate;
	}

	public String getCusPostalCode() {
		return this.cusPostalCode;
	}

	public void setCusPostalCode(String cusPostalCode) {
		this.cusPostalCode = cusPostalCode;
	}

	public String getCusTelephone() {
		return this.cusTelephone;
	}

	public void setCusTelephone(String cusTelephone) {
		this.cusTelephone = cusTelephone;
	}

	public String getCusFasimile() {
		return this.cusFasimile;
	}

	public void setCusFasimile(String cusFasimile) {
		this.cusFasimile = cusFasimile;
	}

	public String getCusUrl() {
		return this.cusUrl;
	}

	public void setCusUrl(String cusUrl) {
		this.cusUrl = cusUrl;
	}

	public String getCusRegisterNumber() {
		return this.cusRegisterNumber;
	}

	public void setCusRegisterNumber(String cusRegisterNumber) {
		this.cusRegisterNumber = cusRegisterNumber;
	}

	public String getCusLegalPerson() {
		return this.cusLegalPerson;
	}

	public void setCusLegalPerson(String cusLegalPerson) {
		this.cusLegalPerson = cusLegalPerson;
	}

	public Double getCusRegisterMoney() {
		return this.cusRegisterMoney;
	}

	public void setCusRegisterMoney(Double cusRegisterMoney) {
		this.cusRegisterMoney = cusRegisterMoney;
	}

	public Double getCusRmb() {
		return this.cusRmb;
	}

	public void setCusRmb(Double cusRmb) {
		this.cusRmb = cusRmb;
	}

	public String getCusOpeningBank() {
		return this.cusOpeningBank;
	}

	public void setCusOpeningBank(String cusOpeningBank) {
		this.cusOpeningBank = cusOpeningBank;
	}

	public String getCusBankAccount() {
		return this.cusBankAccount;
	}

	public void setCusBankAccount(String cusBankAccount) {
		this.cusBankAccount = cusBankAccount;
	}

	public String getCusCrownRent() {
		return this.cusCrownRent;
	}

	public void setCusCrownRent(String cusCrownRent) {
		this.cusCrownRent = cusCrownRent;
	}

	public String getCusStateTaxes() {
		return this.cusStateTaxes;
	}

	public void setCusStateTaxes(String cusStateTaxes) {
		this.cusStateTaxes = cusStateTaxes;
	}

	public String getCusResume() {
		return this.cusResume;
	}

	public void setCusResume(String cusResume) {
		this.cusResume = cusResume;
	}

	public Long getCusCreditworthiness() {
		return this.cusCreditworthiness;
	}

	public void setCusCreditworthiness(Long cusCreditworthiness) {
		this.cusCreditworthiness = cusCreditworthiness;
	}

	public Long getCusStatisfcing() {
		return this.cusStatisfcing;
	}

	public void setCusStatisfcing(Long cusStatisfcing) {
		this.cusStatisfcing = cusStatisfcing;
	}

	public Long getCusState() {
		return this.cusState;
	}

	public void setCusState(Long cusState) {
		this.cusState = cusState;
	}

	public List<Linkman> getLinkmans() {
		return linkmans;
	}

	public void setLinkmans(List<Linkman> linkmans) {
		this.linkmans = linkmans;
	}

	public List<Assort> getAssorts() {
		return assorts;
	}

	public void setAssorts(List<Assort> assorts) {
		this.assorts = assorts;
	}

	public List<Orders> getOrderses() {
		return orderses;
	}

	public void setOrderses(List<Orders> orderses) {
		this.orderses = orderses;
	}
	

//	public List<Serve> getServes() {
//		return this.serves;
//	}
//
//	public void setServes(List<Serve> serves) {
//		this.serves = serves;
//	}


}