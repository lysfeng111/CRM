package com.rlg.crm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rlg.crm.conn.UtilConnect;
import com.rlg.crm.dao.LostCustomDao;
import com.rlg.crm.domain.Customer;
import com.rlg.crm.domain.Lost;

public class LostCustomDaoImpl implements LostCustomDao{

	
	public List<Lost> selectByWhere(String where) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				List<Lost> list = new ArrayList<Lost>();
				Connection conn = null;
				PreparedStatement st = null;
				ResultSet rs = null;
				String sql = "select LOS_ID,LOS_CUSTOMER,LOS_MANAGER,LOS_LAST_ORDER,LOS_RESPITE,LOS_DATE,LOS_REASON,LOS_STATE from lost where 1=1 " + where;
						
				try {
					conn = UtilConnect.getConn();
					// 负责执行SQL语句
					st = conn.prepareStatement(sql);
					
					rs = st.executeQuery();
					// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
					// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
					while (rs.next()) {
						Lost lost = new Lost();
//						Customer cus=new Customer();
						lost.setLosId(rs.getInt(1));
//						cus.setCusId(rs.getInt(2));
						lost.setLosCustomer(rs.getString(2));
						lost.setLosManager(rs.getString(3));
						lost.setLosLastOrder(rs.getTimestamp(4));
						lost.setLosRespite(rs.getString(5));
						lost.setLosDate(rs.getTimestamp(6));
						lost.setLosReason(rs.getString(7));
						lost.setLosState(rs.getLong(8));
						
						list.add(lost);
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
	public Lost selectById(int id) {
	//	List<Lost> list = new ArrayList<Lost>();
		Lost lost = new Lost();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select LOS_ID,LOS_CUSTOMER,LOS_MANAGER,LOS_LAST_ORDER,LOS_RESPITE,LOS_DATE,LOS_REASON,LOS_STATE from lost where LOS_ID= " + id;
				
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			
			rs = st.executeQuery();
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			while (rs.next()) {
				
//				Customer cus=new Customer();
				lost.setLosId(rs.getInt(1));
//				cus.setCusId(rs.getInt(2));
				lost.setLosCustomer(rs.getString(2));
				lost.setLosManager(rs.getString(3));
				lost.setLosLastOrder(rs.getTimestamp(4));
				lost.setLosRespite(rs.getString(5));
				lost.setLosDate(rs.getTimestamp(6));
				lost.setLosReason(rs.getString(7));
				lost.setLosState(rs.getLong(8));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			UtilConnect.close(rs);
			UtilConnect.close(st);
			UtilConnect.close(conn);
		}
		return lost;
	}

	@Override
	public void update(Lost lost) {
		// TODO Auto-generated method stub
		//Lost lost = new Lost();
		Connection conn = null;
		PreparedStatement st = null;
		
		String sql = "update lost set LOS_RESPITE=?,LOS_REASON=? where LOS_ID= " + lost.getLosId();
				
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			if(lost.getLosRespite()==null){
				lost.setLosRespite(" ");
			}
			if(lost.getLosReason()==null){
				lost.setLosReason(" ");
			}
			st.setString(1, lost.getLosRespite());
			st.setString(2, lost.getLosReason());
			st.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			UtilConnect.close(st);
			UtilConnect.close(conn);
		}
		
	}

}
