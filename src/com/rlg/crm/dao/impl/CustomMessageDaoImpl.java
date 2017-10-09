package com.rlg.crm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rlg.crm.conn.UtilConnect;
import com.rlg.crm.dao.CustomMessageDao;
import com.rlg.crm.domain.Area;
import com.rlg.crm.domain.Assort;
import com.rlg.crm.domain.Customer;
import com.rlg.crm.domain.CustomerRank;
import com.rlg.crm.domain.Linkman;
import com.rlg.crm.domain.Orders;
import com.rlg.crm.servlet.CustomMessageListServlet;

public class CustomMessageDaoImpl implements CustomMessageDao {

	@Override
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}
	public Customer getCustomerById(int id){
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Customer cus = new Customer();

		String sql = "select CUS_ID, CUS_ARE_ID,ARE_NAME,CUS_CK_ID,CK_NAME,CUS_NAME,CUS_NUMBER,CUS_ADDRESS,CUS_CHANGE_ORIGIN,CUS_CHANGE_DESCRIBE, "+
"CUS_SUCCESS_PROBABILITY,CUS_MANAGER,CUS_ALLOT_NAME,CUS_ALLOT_DATE,CUS_POSTAL_CODE,CUS_TELEPHONE,CUS_FASIMILE,CUS_URL,CUS_REGISTER_NUMBER,CUS_LEGAL_PERSON,"+
"CUS_REGISTER_MONEY,CUS_RMB,CUS_OPENING_BANK,CUS_BANK_ACCOUNT,CUS_CROWN_RENT,CUS_STATE_TAXES,CUS_RESUME,CUS_CREDITWORTHINESS,CUS_STATISFCING,CUS_STATE from "+
"customer c inner join area on area.ARE_ID = c.CUS_ARE_ID inner join customer_rank cr on cr.CK_ID =c.CUS_CK_ID "+
" where 1=1 and cus_id= " + id;
				
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			
			rs = st.executeQuery();
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			while (rs.next()) {
				Area area = new Area();
				CustomerRank cr=new CustomerRank();
				cus.setCusId(rs.getInt(1));	
				area.setAreId(rs.getInt(2));
				area.setAreName(rs.getString(3));
				cus.setArea(area);
				cr.setCkId(rs.getInt(4));
				cr.setCkName(rs.getString(5));
				cus.setCustomerRank(cr);
				cus.setCusName(rs.getString(6));
				cus.setCusNumber(rs.getString(7));
				cus.setCusAddress(rs.getString(8));
				cus.setCusChangeOrigin(rs.getString(9));
				cus.setCusChangeDescribe(rs.getString(10));
				cus.setCusSuccessProbability(rs.getString(11));
				cus.setCusManager(rs.getString(12));
				cus.setCusAllotName(rs.getString(13));
				cus.setCusAllotDate(rs.getTimestamp(14));
				cus.setCusPostalCode(rs.getString(15));
				cus.setCusTelephone(rs.getString(16));
				cus.setCusFasimile(rs.getString(17));
				cus.setCusUrl(rs.getString(18));
				cus.setCusRegisterNumber(rs.getString(19));
				cus.setCusLegalPerson(rs.getString(20));
				cus.setCusRegisterMoney(rs.getDouble(21));
				cus.setCusRmb(rs.getDouble(22));
				cus.setCusOpeningBank(rs.getString(23));
				cus.setCusBankAccount(rs.getString(24));
				cus.setCusCrownRent(rs.getString(25));
				cus.setCusStateTaxes(rs.getString(26));
				cus.setCusResume(rs.getString(27));
				cus.setCusCreditworthiness(rs.getLong(28));
				cus.setCusStatisfcing(rs.getLong(29));
				cus.setCusState(rs.getLong(30));
				
	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			UtilConnect.close(rs);
			UtilConnect.close(st);
			UtilConnect.close(conn);
		}
		List<Linkman> linkmans =this.getLinkmanByWhere(" and lin_cus_id="+cus.getCusId());
		cus.setLinkmans(linkmans);
		List<Assort> assorts=this.getAssortsByWhere(" and ass_cus_id="+cus.getCusId());
		cus.setAssorts(assorts);
		List<Orders> orderses=this.getOrdersByWhere(" and ord_cust_id="+cus.getCusId());
		cus.setOrderses(orderses);
		
		return cus;
		
	}

	@Override
	public List<Customer> getCustomByWhere(String where) {
				List<Customer> list = new ArrayList<Customer>();
				Connection conn = null;
				PreparedStatement st = null;
				ResultSet rs = null;
				String sql = "select CUS_ID,CUS_NAME,CUS_ADDRESS,CUS_TELEPHONE,CUS_STATE from customer where 1=1 " + where;
						
				try {
					conn = UtilConnect.getConn();
					// 负责执行SQL语句
					st = conn.prepareStatement(sql);
					
					rs = st.executeQuery();
					// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
					// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
					while (rs.next()) {
						Customer cus = new Customer();
						Area area = new Area();
						cus.setCusId(rs.getInt(1));	
//						area.setAreId(rs.getInt(2));
//						area.setAreName(rs.getString(3));
//						cus.setArea(area);
						cus.setCusName(rs.getString(2));
						cus.setCusAddress(rs.getString(3));
						cus.setCusTelephone(rs.getString(4));
						cus.setCusState(rs.getLong(5));
						list.add(cus);
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
	public List<Linkman> getLinkmanByWhere(String where) {
		List<Linkman> list = new ArrayList<Linkman>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from linkman lk inner join customer on lk.LIN_CUS_ID=customer.CUS_ID  where 1=1 " + where;
				
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			
			rs = st.executeQuery();
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			while (rs.next()) {
				Linkman lk = new Linkman();
				Customer cus=new Customer();
				lk.setLinId(rs.getInt(1));
				cus.setCusId(rs.getInt(2));
				lk.setCustomer(cus);
				lk.setLinName(rs.getString(3));
				lk.setLinSex(rs.getString(4));
				lk.setLinTelephone(rs.getString(5));
				lk.setLinPost(rs.getString(6));
				lk.setLinMobile(rs.getString(7));
				lk.setLinMemo(rs.getString(8));
				list.add(lk);
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
	
	
	public List<Assort> getAssortsByWhere(String where) {
		List<Assort> list = new ArrayList<Assort>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from assort ass inner join customer on ass.ASS_CUS_ID=customer.CUS_ID  where 1=1 " + where;
				
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			
			rs = st.executeQuery();
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			while (rs.next()) {
				Assort ass = new Assort();
				Customer cus=new Customer();
				ass.setAssId(rs.getInt(1));
				cus.setCusId(rs.getInt(2));
				ass.setCustomer(cus);
				ass.setAssDate(rs.getTimestamp(3));
				ass.setAssPalce(rs.getString(4));
				ass.setAssResume(rs.getString(5));
				ass.setAssDetail(rs.getString(6));
				ass.setAssMemo(rs.getString(7));
				list.add(ass);
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
	public List<Orders> getOrdersByWhere(String where) {
		List<Orders> list = new ArrayList<Orders>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from orders ord inner join customer on ord.ORD_CUST_ID=customer.CUS_ID  where 1=1 " + where;
				
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			
			rs = st.executeQuery();
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			while (rs.next()) {
				Orders ord = new Orders();
				Customer cus=new Customer();
				ord.setOrdId(rs.getInt(1));
				cus.setCusId(rs.getInt(2));
				ord.setCustomer(cus);
				ord.setOrdDate(rs.getTimestamp(3));
				ord.setOrdState(rs.getLong(4));
				ord.setOrdAddress(rs.getString(5));
				list.add(ord);
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
	public List<Area> getAreaByWhere() {
		List<Area> list = new ArrayList<Area>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from area ";
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			
			rs = st.executeQuery();
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			while (rs.next()) {
				Area are=new Area();
				are.setAreId(rs.getInt(1));
				are.setAreName(rs.getString(2));
				list.add(are);
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
	public List<CustomerRank> getCustomerRankByWhere() {
		List<CustomerRank> list = new ArrayList<CustomerRank>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from customer_rank ";
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			
			rs = st.executeQuery();
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			while (rs.next()) {
				CustomerRank rank=new CustomerRank();
				rank.setCkId(rs.getInt(1));
				rank.setCkName(rs.getString(2));
				list.add(rank);
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
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement st = null;
		//ResultSet rs = null;
		String sql = "update customer set CUS_ARE_ID=?,CUS_NAME=?,CUS_CK_ID=?,CUS_SUCCESS_PROBABILITY=?,CUS_CHANGE_DESCRIBE=?,CUS_ADDRESS=?,CUS_POSTAL_CODE=?, "
				+ "CUS_TELEPHONE=?,CUS_FASIMILE=?,CUS_URL=?,CUS_REGISTER_NUMBER=?,CUS_LEGAL_PERSON=?,CUS_REGISTER_MONEY=?,CUS_RMB=?,CUS_OPENING_BANK=?,"
				+ "CUS_BANK_ACCOUNT=?,CUS_CROWN_RENT=?,CUS_STATE_TAXES=? where CUS_ID = "+customer.getCusId();
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			st.setInt(1,customer.getArea().getAreId() );
			st.setString(2,customer.getCusName() );
			st.setInt(3,customer.getCustomerRank().getCkId() );
			st.setString(4,customer.getCusSuccessProbability() );
			st.setString(5,customer.getCusChangeDescribe() );
			st.setString(6,customer.getCusAddress() );
			st.setString(7,customer.getCusPostalCode() );
			st.setString(8,customer.getCusTelephone() );
			st.setString(9,customer.getCusFasimile() );
			st.setString(10,customer.getCusUrl() );
			st.setString(11,customer.getCusRegisterNumber() );
			st.setString(12, customer.getCusLegalPerson());
			st.setDouble(13,customer.getCusRegisterMoney() );
			st.setDouble(14, customer.getCusRmb());
			st.setString(15,customer.getCusOpeningBank());
			st.setString(16, customer.getCusBankAccount());
			st.setString(17, customer.getCusCrownRent());
			st.setString(18, customer.getCusStateTaxes());
			
			st.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//UtilConnect.close(rs);
			UtilConnect.close(st);
			UtilConnect.close(conn);
		}
	}

	
}
