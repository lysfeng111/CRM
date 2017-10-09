package com.rlg.crm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rlg.crm.conn.UtilConnect;
import com.rlg.crm.dao.CriteriaServe;
import com.rlg.crm.dao.ServeDao;
import com.rlg.crm.domain.Customer;
import com.rlg.crm.domain.Serve;
import com.rlg.crm.domain.ServeType;

public class ServeDaoImpl implements ServeDao {

	@Override
	public Serve getServeForId(int id) {
		Serve serve = new Serve();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select SER_ID,CUS_ID,SER_RESUME,SE_ID,SER_CREATER_NAME,SER_CREATER_DATE, "
				+ "SER_CREATER_ID,SER_REQUEST,SER_ALLOT_ID,SER_ALLOT_NAME,SER_ALLOT_DATE,SER_HANDLE,SER_HANDLE_ID,"
				+ "SER_HANDLE_NAME,SER_HANDLE_DATE,SER_RESULT,SER_SATISFCING,SER_STATE,CUS_NAME,SE_NAME	"
				+ " from serve inner join customer on serve.SER_CUS_ID = customer.CUS_ID "
				+ "inner join serve_type on serve.SER_SE_ID = serve_type.SE_ID "
				+ "where SER_ID = ?"; 
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			st.setInt(1,id);
		
			rs = st.executeQuery();
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			while (rs.next()) {
				
				Customer customer = new Customer();
				ServeType serveType = new ServeType();
				customer.setCusId(rs.getInt(2));
				customer.setCusName(rs.getString(19));
				serve.setCustomer(customer);
				
				serveType.setSeName(rs.getString(20));
				serveType.setSeId(rs.getInt(4));
				serve.setServeType(serveType);
				
				serve.setSerId(rs.getInt(1));
				
				
				serve.setSerResume(rs.getString(3));
				
				serve.setSerCreaterName(rs.getString(5));
				serve.setSerCreaterDate(rs.getTimestamp(6));
				serve.setSerCreaterId(rs.getInt(7));
				serve.setSerRequest(rs.getString(8));
				serve.setSerAllotId(rs.getInt(9));
				serve.setSerAllotName(rs.getString(10));
				serve.setSerAllotDate(rs.getTimestamp(11));
				serve.setSerHandle(rs.getString(12));
				serve.setSerHandleId(rs.getInt(13));
				serve.setSerHandleName(rs.getString(14));
				serve.setSerHandleDate(rs.getTimestamp(15));
				serve.setSerResult(rs.getString(16));
				serve.setSerSatisfcing(rs.getLong(17));
				serve.setSerState(rs.getLong(18));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			UtilConnect.close(rs);
			UtilConnect.close(st);
			UtilConnect.close(conn);
		}
		return serve;
	}
	
	@Override
	public void addServe(Serve serve) {
		Connection conn = null;
		PreparedStatement st = null;
		String sql = "insert into serve (SER_SE_ID,SER_RESUME,SER_CUS_ID,SER_STATE,"
				+ "SER_REQUEST,SER_CREATER_NAME,SER_CREATER_DATE) "
				+ "values (?,?,?,?,?,?,?)";
		try {
			conn = UtilConnect.getConn();
			
			st = conn.prepareStatement(sql);
			
			st.setInt(1, serve.getServeType().getSeId());
			st.setString(2, serve.getSerResume());
			st.setInt(3, serve.getCustomer().getCusId());
			st.setLong(4, serve.getSerState());
			st.setString(5, serve.getSerRequest());
			st.setString(6, serve.getSerCreaterName());
			st.setTimestamp(7, serve.getSerCreaterDate());
			//st.setInt(3, serve.getSerCreaterId());
			//st.setString(4, serve.getSerCreaterName());
			//st.setTimestamp(5, serve.getSerCreaterDate());
			
			//st.setInt(7, serve.getSerAllotId());
			
			//st.setString(10, serve.getSerHandle());
			//st.setInt(11, serve.getSerHandleId());
			//st.setString(12, serve.getSerHandleName());
			//st.setTimestamp(13, serve.getSerHandleDate());
			//st.setString(14, serve.getSerResult());
			//st.setLong(15, serve.getSerSatisfcing());
			
			
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			UtilConnect.close(st);
			UtilConnect.close(conn);
		}

	}

	@Override
	public List<Serve> getListServeFromCs(CriteriaServe cs) {
		List<Serve> list = new ArrayList<Serve>();
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select SER_ID,CUS_NAME,SER_RESUME,SE_NAME,SER_CREATER_NAME,SER_CREATER_DATE,SER_STATE "
				+ " from serve inner join customer on serve.SER_CUS_ID = customer.CUS_ID "
				+ "inner join serve_type on serve.SER_SE_ID = serve_type.SE_ID "
				+ "where CUS_NAME like ? and SER_RESUME like ? and SE_NAME like ? and SER_CREATER_DATE like ? and SER_STATE like ? "
				+ "order by SER_ID "; 
		
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			st.setString(1, cs.getCustomerId());
			st.setString(2, cs.getSerResume());
			st.setString(3, cs.getSerType());
			st.setString(4, cs.getSerCreaterDate());
			st.setString(5, cs.getSerState());
			
			rs = st.executeQuery();
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			while (rs.next()) {
				Serve serve = new Serve();
				Customer customer = new Customer();
				ServeType serveType = new ServeType();
				
				serve.setSerId(rs.getInt(1));
				
				customer.setCusName(rs.getString(2));
				serve.setCustomer(customer);
				
				serve.setSerResume(rs.getString(3));
				serveType.setSeName(rs.getString(4));
				serve.setServeType(serveType);
				serve.setSerCreaterName(rs.getString(5));
				serve.setSerCreaterDate(rs.getTimestamp(6));
				serve.setSerState(rs.getLong(7));
				
				list.add(serve);
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
	public void updateServe(Serve serve) {
		Connection conn = null;
		PreparedStatement st = null;
		String sql = "update serve set SER_SE_ID=?,SER_CUS_ID=?,SER_RESUME=?,SER_CREATER_NAME=?,"
					+ "SER_CREATER_DATE=?,SER_STATE=?,SER_REQUEST=?,SER_ALLOT_NAME=?,SER_ALLOT_DATE=?,SER_HANDLE=?,"
					+ "SER_HANDLE_NAME=?,SER_HANDLE_DATE=?,SER_RESULT=?,SER_SATISFCING=? "
					+"where SER_ID=?";
	
		try {
			conn = UtilConnect.getConn();
			
			st = conn.prepareStatement(sql);
			
			st.setInt(1, serve.getServeType().getSeId());
			st.setString(3, serve.getSerResume());
			st.setInt(2, serve.getCustomer().getCusId());
			st.setLong(6, serve.getSerState());
			st.setString(7, serve.getSerRequest());
			st.setString(4, serve.getSerCreaterName());
			st.setTimestamp(5, serve.getSerCreaterDate());
			//st.setInt(3, serve.getSerCreaterId());
			//st.setInt(7, serve.getSerAllotId());
			st.setString(8, serve.getSerAllotName());
			st.setTimestamp(9, serve.getSerAllotDate());
			st.setString(10, serve.getSerHandle());
			//st.setInt(11, serve.getSerHandleId());
			st.setString(11, serve.getSerHandleName());
			st.setTimestamp(12, serve.getSerHandleDate());
			st.setString(13, serve.getSerResult());
			st.setLong(14, serve.getSerSatisfcing());
			st.setInt(15, serve.getSerId());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			UtilConnect.close(st);
			UtilConnect.close(conn);
		}
		
	}

	@Override
	public void delete(int id) {
		Connection conn = null;
		PreparedStatement st = null;
		String sql = "delete from serve where SER_ID=?";
	
		try {
			conn = UtilConnect.getConn();
			
			st = conn.prepareStatement(sql);
			
			st.setInt(1, id);
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			UtilConnect.close(st);
			UtilConnect.close(conn);
		}
		
	}
	
	@Override
	public List<ServeType> getServeType() {
		List<ServeType> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select SE_ID,SE_NAME from serve_type  "; 
		
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			while (rs.next()) {
				ServeType serveType = new ServeType();
				
				serveType.setSeId(rs.getInt(1));
				serveType.setSeName(rs.getString(2));
						
				list.add(serveType);
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
	public List<Customer> getCustomer() {
		List<Customer> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select CUS_ID,CUS_NAME from customer  "; 
		
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			while (rs.next()) {
				Customer customer = new Customer();
				
				customer.setCusId(rs.getInt(1));
				customer.setCusName(rs.getString(2));
						
				list.add(customer);
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
