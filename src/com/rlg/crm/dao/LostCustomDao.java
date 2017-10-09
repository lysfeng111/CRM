package com.rlg.crm.dao;

import java.util.List;

import com.rlg.crm.domain.Lost;

public interface LostCustomDao {
	public List<Lost> selectByWhere(String where);
	public Lost selectById(int id);
	public void update(Lost lost);

}
