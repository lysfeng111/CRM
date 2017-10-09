package com.rlg.crm.dao;

import com.rlg.crm.domain.Consumer;

public interface ConsumerDao {
	
	public int login(String name, String password);
	public Consumer user(String name, String password);
	public boolean check(String name);

}
