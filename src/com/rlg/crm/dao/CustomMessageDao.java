package com.rlg.crm.dao;

import java.util.List;

import com.rlg.crm.domain.Area;
import com.rlg.crm.domain.Assort;
import com.rlg.crm.domain.Customer;
import com.rlg.crm.domain.CustomerRank;
import com.rlg.crm.domain.Linkman;
import com.rlg.crm.domain.Orders;
import com.rlg.crm.domain.SalChance;

public interface CustomMessageDao {
	public void  addCustomer(Customer customer);
	public void updateCustomer(Customer customer);
	
	public List<Customer> getCustomByWhere(String where);
	public Customer getCustomerById(int id);
	
	public List<Linkman> getLinkmanByWhere(String where);
	public List<Assort> getAssortsByWhere(String where);
	public List<Orders> getOrdersByWhere(String where);
	
	public List<Area> getAreaByWhere();
	public List<CustomerRank> getCustomerRankByWhere();

}
