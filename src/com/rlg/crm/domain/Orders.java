package com.rlg.crm.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Orders entity. @author MyEclipse Persistence Tools
 */

public class Orders implements java.io.Serializable {

	// Fields

	private Integer ordId;
	private Customer customer;
	private Timestamp ordDate;
	private Long ordState;
	private String ordAddress;
	private Set ordersDetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public Orders() {
	}

	/** full constructor */
	public Orders(Customer customer, Timestamp ordDate, Long ordState,
			String ordAddress, Set ordersDetails) {
		this.customer = customer;
		this.ordDate = ordDate;
		this.ordState = ordState;
		this.ordAddress = ordAddress;
		this.ordersDetails = ordersDetails;
	}

	// Property accessors

	public Integer getOrdId() {
		return this.ordId;
	}

	public void setOrdId(Integer ordId) {
		this.ordId = ordId;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Timestamp getOrdDate() {
		return this.ordDate;
	}

	public void setOrdDate(Timestamp ordDate) {
		this.ordDate = ordDate;
	}

	public Long getOrdState() {
		return this.ordState;
	}

	public void setOrdState(Long ordState) {
		this.ordState = ordState;
	}

	public String getOrdAddress() {
		return this.ordAddress;
	}

	public void setOrdAddress(String ordAddress) {
		this.ordAddress = ordAddress;
	}

	public Set getOrdersDetails() {
		return this.ordersDetails;
	}

	public void setOrdersDetails(Set ordersDetails) {
		this.ordersDetails = ordersDetails;
	}

}