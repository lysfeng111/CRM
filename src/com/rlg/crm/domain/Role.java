package com.rlg.crm.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */

public class Role implements java.io.Serializable {

	// Fields

	private Integer rolId;
	private String rolName;
	private Set consumers = new HashSet(0);

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** full constructor */
	public Role(String rolName, Set consumers) {
		this.rolName = rolName;
		this.consumers = consumers;
	}

	// Property accessors

	public Integer getRolId() {
		return this.rolId;
	}

	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

	public String getRolName() {
		return this.rolName;
	}

	public void setRolName(String rolName) {
		this.rolName = rolName;
	}

	public Set getConsumers() {
		return this.consumers;
	}

	public void setConsumers(Set consumers) {
		this.consumers = consumers;
	}

}