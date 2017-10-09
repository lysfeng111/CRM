package com.rlg.crm.dao;

import java.util.List;

import com.rlg.crm.domain.Assort;

public interface AssortDao {
	public void add(Assort assort);
	public void del(int id);
	public void upd(Assort assort);
	public Assort seleectById(int id);
	public List<Assort> selectAll(int cusId);
}
