package com.rlg.crm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rlg.crm.conn.UtilConnect;
import com.rlg.crm.dao.SalChanceDao;
import com.rlg.crm.domain.SalChance;

public class SalChanceDaoImpl implements SalChanceDao {

	@Override
	public List<SalChance> getSalChancesByWhere(String where) {
		// TODO Auto-generated method stub
		List<SalChance> list = new ArrayList<SalChance>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select   chc_id,chc_source,chc_cust_name,chc_title,chc_rate,chc_linkman,chc_tel,chc_desc,chc_create_id"+
		"chc_create_name,chc_create_date,chc_due_id,chc_due_name,chc_due_date,chc_status from sal_chance where 1=1 " + where;
				
		try {
			conn = UtilConnect.getConn();
			// 负责执行SQL语句
			st = conn.prepareStatement(sql);
			
			rs = st.executeQuery();
			// ResultSet是个集合，里面存的是数据库的表数据，以二维表的形式呈现，
			// rs.next也是一个游标，默认情况下游标是指的第一行数据之前，当第一次调用。next()的时候游标指的是第一行数据，再次调用，指的是下一行数据
			while (rs.next()) {
				SalChance sal = new SalChance();
				sal.setChcId(rs.getLong(1));
				sal.setChcSource(rs.getString(2));
				sal.setChcCustName(rs.getString(3));
				sal.setChcTitle(rs.getString(4));
				sal.setChcRate(rs.getInt(5));
				sal.setChcLinkman(rs.getString(6));
				sal.setChcTel(rs.getString(7));
				sal.setChcDesc(rs.getString(8));
				sal.setChcCreateId(rs.getInt(9));
				sal.setChcCreateName(rs.getString(10));
				sal.setChcCreateDate(rs.getTimestamp(11));
				sal.setChcDueId(rs.getInt(12));
				sal.setChcDueName(rs.getString(13));
				sal.setChcDueDate(rs.getTimestamp(14));
				sal.setChcStatus(rs.getString(15));				
				list.add(sal);
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
