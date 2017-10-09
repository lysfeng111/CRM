package com.rlg.crm.dao;

import java.util.List;

import org.jfree.data.category.CategoryDataset;

public interface OrdersDetailDao {
	public CategoryDataset getMoneyForCo(CriteriaOrders co);
}
