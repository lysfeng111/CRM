package com.rlg.crm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.rlg.crm.conn.UtilConnect;
import com.rlg.crm.dao.NewSaleOpportuntiyDao;
import com.rlg.crm.domain.SalChance;

public class NewSaleOpportuntiyDaoImpl implements NewSaleOpportuntiyDao {

	@Override
	public void save(SalChance sal) {
		// TODO Auto-generated method stub
		
		
		Connection conn = null;
		PreparedStatement st = null;
		String sql = "insert into sal_chance (chc_source,chc_cust_name,chc_title,chc_rate,chc_linkman,chc_tel,chc_desc,chc_create_id,"+
		"chc_create_name,chc_create_date,chc_due_id,chc_due_name,chc_due_date,chc_status) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		System.out.println(sql);
				
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			st.setString(1,sal.getChcSource());
			st.setString(2,sal.getChcCustName());
			st.setString(3,sal.getChcTitle());
			st.setInt(4,sal.getChcRate());
			st.setString(5,sal.getChcLinkman());
			st.setString(6,sal.getChcTel());
			st.setString(7,sal.getChcDesc());
			st.setInt(8,sal.getChcCreateId());
			st.setString(9,sal.getChcCreateName());
			
			st.setTimestamp(10,sal.getChcCreateDate());
			st.setInt(11,sal.getChcDueId()==null?0:sal.getChcDueId());
			st.setString(12,sal.getChcDueName());
			
			st.setTimestamp(13,sal.getChcDueDate());
			st.setString(14,sal.getChcStatus());	
			st.executeUpdate();
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			UtilConnect.close(conn);
			UtilConnect.close(st);
		}
	}

}
