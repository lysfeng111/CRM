package com.rlg.crm.dao;

import java.util.List;

import com.rlg.crm.domain.SalChance;

public interface SaleOpportunityDao {
	public List<SalChance> getByWhere(String chc_cust_name,String chc_title,String chc_linkman);
}
