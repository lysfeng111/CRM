package com.rlg.crm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.rlg.crm.conn.UtilConnect;
import com.rlg.crm.dao.CriteriaOrders;
import com.rlg.crm.dao.OrdersDetailDao;
import com.rlg.crm.dao.ResultForContri;

public class OrdersDetailDaoImpl implements OrdersDetailDao {

	/*@Override
	public List<ResultForContri> getMoneyForCo(CriteriaOrders co) {
		
		List<ResultForContri> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select CUS_ID,CUS_NAME,SUM(OL_PRICE) from orders inner join customer on orders.ORD_CUST_ID = customer.CUS_ID "
				+ "inner join orders_detail on orders.ORD_ID=orders_detail.OL_ORD_ID where ORD_STATE = 1 and ORD_DATE like ? and CUS_NAME like ?"
				+ "  group by CUS_ID ";
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			st.setString(1,co.getOdrDate());
			st.setString(2,co.getCusName());
			
			rs = st.executeQuery();
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			while (rs.next()) {
				ResultForContri rfc = new ResultForContri();
				rfc.setRfcCusId(rs.getInt(1));
				rfc.setRfcCusName(rs.getString(2));
				rfc.setRfcSumMoney(rs.getDouble(3));
				list.add(rfc);
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
	}*/
	
	@Override
	public CategoryDataset getMoneyForCo(CriteriaOrders co) {
		
		 DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select CUS_ID,CUS_NAME,SUM(OL_PRICE) from orders inner join customer on orders.ORD_CUST_ID = customer.CUS_ID "
				+ "inner join orders_detail on orders.ORD_ID=orders_detail.OL_ORD_ID where ORD_STATE = 1 and ORD_DATE like ? and CUS_NAME like ?"
				+ "  group by CUS_ID ";
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			st.setString(1,co.getOdrDate());
			st.setString(2,co.getCusName());
			
			rs = st.executeQuery();
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			while (rs.next()) {
				 dataset.addValue(rs.getDouble(3), "", rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			UtilConnect.close(rs);
			UtilConnect.close(st);
			UtilConnect.close(conn);
		}
		
		return dataset;
	}
}
