package com.rlg.crm.dao;

import java.util.List;

import com.rlg.crm.domain.Customer;
import com.rlg.crm.domain.Serve;
import com.rlg.crm.domain.ServeType;

public interface ServeDao {
	public void addServe(Serve serve);
	
	public List<Serve> getListServeFromCs(CriteriaServe cs);
	public void updateServe(Serve serve);
	public Serve getServeForId(int id);
	public void delete(int id);
	
	public List<ServeType> getServeType();
	public List<Customer> getCustomer();
}
