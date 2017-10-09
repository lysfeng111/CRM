package com.rlg.crm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rlg.crm.conn.UtilConnect;
import com.rlg.crm.dao.OrderDao;
import com.rlg.crm.domain.Customer;
import com.rlg.crm.domain.Orders;
import com.rlg.crm.domain.OrdersDetail;
import com.rlg.crm.domain.Wares;

public class OrderDaoImpl implements OrderDao{

	@Override
	public Orders selectOrdersById(int id) {
		// TODO Auto-generated method stub
		List<Orders> list = new ArrayList<Orders>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from orders where ord_id = "+id;
		Orders order = new Orders();		
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			
			rs = st.executeQuery();
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
				while(rs.next()){
				order.setOrdId(rs.getInt(1));
				Customer customer=new Customer();
				customer.setCusId(rs.getInt(2));
				order.setCustomer(customer);
				order.setOrdDate(rs.getTimestamp(3));
				order.setOrdState(rs.getLong(4));
				order.setOrdAddress(rs.getString(5));
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			UtilConnect.close(rs);
			UtilConnect.close(st);
			UtilConnect.close(conn);
		}
		return order;
	}

	@Override
	public List<OrdersDetail> selectOrdersDetailById(int id) {
		List<OrdersDetail> list = new ArrayList<OrdersDetail>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select OL_ID,OL_ORD_ID,OL_WAR_ID,OL_PRICE,OL_COUNT,OL_UNIT, WAR_NAME from orders_detail od inner join wares on OL_ORD_ID=WAR_ID where ol_ord_id = "+id;
		OrdersDetail orderDetail = new OrdersDetail();		
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			
			rs = st.executeQuery();
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			while (rs.next()) {
			orderDetail.setOlId(rs.getInt(1));
			Orders order = new Orders();
			order.setOrdId(rs.getInt(2));
			orderDetail.setOrders(order);
			Wares wares=new Wares();
			wares.setWarId(rs.getInt(3));			
			orderDetail.setOlPrice(rs.getDouble(4));
			orderDetail.setOlCount(rs.getLong(5));
			orderDetail.setOlUnit(rs.getString(6));
			wares.setWarName(rs.getString(7));
			orderDetail.setWares(wares);
			
			list.add(orderDetail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			UtilConnect.close(rs);
			UtilConnect.close(st);
			UtilConnect.close(conn);
		}
		return list;
	}

	@Override
	public List<Orders> selectAllOrders(int cus_id) {
		// TODO Auto-generated method stub
		List<Orders> list = new ArrayList<Orders>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from orders where ord_cust_id = "+cus_id;
				
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			
			rs = st.executeQuery();
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			while (rs.next()) {
				Orders order = new Orders();
				order.setOrdId(rs.getInt(1));
				Customer customer=new Customer();
				customer.setCusId(rs.getInt(2));
				order.setCustomer(customer);
				order.setOrdDate(rs.getTimestamp(3));
				order.setOrdState(rs.getLong(4));
				order.setOrdAddress(rs.getString(5));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			UtilConnect.close(rs);
			UtilConnect.close(st);
			UtilConnect.close(conn);
		}
		return list;
		
	}

}
