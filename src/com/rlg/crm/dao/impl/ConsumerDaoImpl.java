package com.rlg.crm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rlg.crm.conn.UtilConnect;
import com.rlg.crm.dao.ConsumerDao;
import com.rlg.crm.domain.Assort;
import com.rlg.crm.domain.Consumer;
import com.rlg.crm.domain.Customer;
import com.rlg.crm.domain.Role;

public class ConsumerDaoImpl implements ConsumerDao{

	@Override
	public int login(String name, String password) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from consumer where con_name= '"+name+"' and con_password= '"+password+"'";
	
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			rs=st.executeQuery();
			
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			while(rs.next()){
			
				if(rs.getInt(1)>0){
					return 1;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			UtilConnect.close(rs);
			UtilConnect.close(st);
			UtilConnect.close(conn);
		}
		return 0;
	}

	@Override
	public boolean check(String name) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select count(1) from consumer where con_name= '"+name+"'";
		
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			rs=st.executeQuery();
			
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			while(rs.next()){
			
				if(rs.getInt(1)>0){
					return true;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			UtilConnect.close(rs);
			UtilConnect.close(st);
			UtilConnect.close(conn);
		}
		return false;
	}

	@Override
	public Consumer user(String name, String password) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from consumer where con_name= '"+name+"' and con_password= '"+password+"'";
		Consumer user=new Consumer();		
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			rs=st.executeQuery();
			
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			while(rs.next()){
			user.setConId(rs.getInt(1));
			Role role=new Role();
			role.setRolId(rs.getInt(2));
			user.setRole(role);
			user.setConName(rs.getString(3));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			UtilConnect.close(rs);
			UtilConnect.close(st);
			UtilConnect.close(conn);
		}
		return user;
	}

}
