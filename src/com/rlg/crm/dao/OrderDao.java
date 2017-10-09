package com.rlg.crm.dao;

import java.util.List;

import com.rlg.crm.domain.Orders;
import com.rlg.crm.domain.OrdersDetail;

public interface OrderDao {
	public Orders selectOrdersById(int id);
	public List<OrdersDetail> selectOrdersDetailById(int id);
	
	public List<Orders> selectAllOrders(int cus_id);

}
