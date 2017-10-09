package com.rlg.crm.dao;

import java.util.List;

import com.rlg.crm.domain.Linkman;

public interface LinkManDao {
	public void add(Linkman linkman);
	public void del(int id);
	public void upd(Linkman linkman);
	public Linkman seleectById(int id);
	public List<Linkman> selectAll(int cusId);

}
