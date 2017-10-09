package com.rlg.crm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rlg.crm.conn.UtilConnect;
import com.rlg.crm.dao.LinkManDao;
import com.rlg.crm.domain.Customer;
import com.rlg.crm.domain.Linkman;

public class LinkManDaoImpl implements LinkManDao{

	@Override
	public void add(Linkman linkman) {
		// TODO Auto-generated method stub
		//List<Linkman> list = new ArrayList<Linkman>();
		Connection conn = null;
		PreparedStatement st = null;
		//ResultSet rs = null;
		String sql = "insert into linkman(LIN_CUS_ID,LIN_NAME,LIN_SEX,LIN_TELEPHONE,LIN_POST,LIN_MOBILE,LIN_MEMO) values(?,?,?,?,?,?,?)";
				
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			
			
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			if(linkman.getCustomer().getCusId()!=null){
				st.setInt(1, linkman.getCustomer().getCusId());
			}
			
			st.setString(2, linkman.getLinName());
			st.setString(3, linkman.getLinSex());
			st.setString(4, linkman.getLinTelephone());
			st.setString(5, linkman.getLinPost());
			st.setString(6,linkman.getLinMobile());
			st.setString(7, linkman.getLinMemo());	
			st.execute();
			System.out.println("添加成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//UtilConnect.close(rs);
			UtilConnect.close(st);
			UtilConnect.close(conn);
		}
	}

	@Override
	public void del(int id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement st = null;
		//ResultSet rs = null;
		String sql = "delete from linkman where lin_id="+id;
				
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			
			
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据

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

	@Override
	public void upd(Linkman linkman) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement st = null;
		//ResultSet rs = null;
		String sql = "update linkman set LIN_NAME=?,LIN_SEX=?,LIN_TELEPHONE=?,LIN_POST=?,LIN_MOBILE=?,LIN_MEMO=? where lin_id="+linkman.getLinId();
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);		
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			st.setString(1, linkman.getLinName());
			st.setString(2, linkman.getLinSex());
			st.setString(3, linkman.getLinTelephone());
			st.setString(4, linkman.getLinPost());
			st.setString(5,linkman.getLinMobile());
			st.setString(6, linkman.getLinMemo());	
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

	@Override
	public Linkman seleectById(int id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from linkman where lin_id="+id;
		Linkman linkman=new Linkman();		
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			rs=st.executeQuery();
			
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			while(rs.next()){
				
			linkman.setLinId(rs.getInt(1));
			Customer customer=new Customer();
			customer.setCusId(rs.getInt(2));
			linkman.setCustomer(customer);
			linkman.setLinName(rs.getString(3));
			linkman.setLinSex(rs.getString(4));
			linkman.setLinTelephone(rs.getString(5));
			linkman.setLinPost(rs.getString(6));
			linkman.setLinMobile(rs.getString(7));
			linkman.setLinMemo(rs.getString(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			UtilConnect.close(rs);
			UtilConnect.close(st);
			UtilConnect.close(conn);
		}
		return linkman;
	}

	@Override
	public List<Linkman> selectAll(int cusId) {
		// TODO Auto-generated method stub
		return null;
	}

}
