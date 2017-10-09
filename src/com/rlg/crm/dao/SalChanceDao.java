package com.rlg.crm.dao;

import java.util.List;

import com.rlg.crm.domain.SalChance;

public interface SalChanceDao {
	public List<SalChance> getSalChancesByWhere(String where);

}
