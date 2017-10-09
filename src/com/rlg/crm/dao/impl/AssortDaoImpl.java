package com.rlg.crm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.rlg.crm.conn.UtilConnect;
import com.rlg.crm.dao.AssortDao;
import com.rlg.crm.domain.Assort;
import com.rlg.crm.domain.Customer;
import com.rlg.crm.domain.Linkman;

public class AssortDaoImpl implements AssortDao{

	@Override
	public void add(Assort assort) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement st = null;
		//ResultSet rs = null;
		String sql = "insert into assort(ASS_CUS_ID,ASS_DATE,ASS_PALCE,ASS_RESUME,ASS_DETAIL,ASS_MEMO) values(?,?,?,?,?,?)";
				
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			
			
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			if(assort.getCustomer().getCusId()!=null){
				st.setInt(1, assort.getCustomer().getCusId());
			}
			
			st.setTimestamp(2, assort.getAssDate());
			st.setString(3, assort.getAssPalce());
			st.setString(4, assort.getAssResume());
			st.setString(5, assort.getAssDetail());
			st.setString(6,assort.getAssMemo());
			
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
		String sql = "delete from assort where ass_id="+id;
				
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
	public void upd(Assort assort) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement st = null;
		//ResultSet rs = null;
		String sql = "update assort set ASS_DATE=?,ASS_PALCE=?,ASS_RESUME=?,ASS_DETAIL=?,ASS_MEMO=? where ass_id="+assort.getAssId();
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			
			st.setTimestamp(1, assort.getAssDate());
			st.setString(2, assort.getAssPalce());
			st.setString(3, assort.getAssResume());
			st.setString(4, assort.getAssDetail());
			st.setString(5, assort.getAssMemo());
			
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
	public Assort seleectById(int id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from assort where ass_id="+id;
		Assort assort=new Assort();		
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			rs=st.executeQuery();
			
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			while(rs.next()){
			assort.setAssId(rs.getInt(1));
			Customer customer=new Customer();
			customer.setCusId(rs.getInt(2));
			assort.setCustomer(customer);
			assort.setAssDate(rs.getTimestamp(3));
			assort.setAssPalce(rs.getString(4));
			assort.setAssResume(rs.getString(5));
			assort.setAssDetail(rs.getString(6));
			assort.setAssMemo(rs.getString(7));
			
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			UtilConnect.close(rs);
			UtilConnect.close(st);
			UtilConnect.close(conn);
		}
		return assort;
	}

	@Override
	public List<Assort> selectAll(int cusId) {
		// TODO Auto-generated method stub
		return null;
	}

}
