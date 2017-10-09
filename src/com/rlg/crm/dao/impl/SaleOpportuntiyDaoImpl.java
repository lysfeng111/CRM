package com.rlg.crm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rlg.crm.conn.UtilConnect;
import com.rlg.crm.dao.SaleOpportunityDao;
import com.rlg.crm.domain.SalChance;

public class SaleOpportuntiyDaoImpl implements SaleOpportunityDao {
	@Override
	public List<SalChance> getByWhere(String chc_cust_name,String chc_title,String chc_linkman) {
		// TODO Auto-generated method stub
		
		String where = "";
		if(chc_cust_name!=null){
			where = where + "AND chc_cust_name = '" + chc_cust_name + "' ";
		}
		if(chc_title != null){
			where = where + "AND chc_title = '" + chc_title + "' ";
		}
		if(chc_linkman!=null){
			where = where + "AND chc_linkman = '" + chc_linkman + "' ";
		}
		System.out.println(where);
		List<SalChance> list = new ArrayList<SalChance>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "SELECT chc_id,chc_source,chc_cust_name,chc_title,chc_rate,chc_linkman,chc_tel,chc_desc,chc_create_id,"+
				"chc_create_name,chc_create_date,chc_due_id,chc_due_name,chc_due_date,chc_status FROM sal_chance WHERE 1=1 " + where;
		conn = UtilConnect.getConn();
		try {
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				SalChance salchance = new SalChance();
				salchance.setChcId(rs.getLong(1));
				salchance.setChcSource(rs.getString(2));
				salchance.setChcCustName(rs.getString(3));
				salchance.setChcTitle(rs.getString(4));
				salchance.setChcRate(rs.getInt(5));
				salchance.setChcLinkman(rs.getString(6));
				salchance.setChcTel(rs.getString(7));
				salchance.setChcDesc(rs.getString(8));
				salchance.setChcCreateId(rs.getInt(9));
				salchance.setChcCreateName(rs.getString(10));
				salchance.setChcCreateDate(rs.getTimestamp(11));
				salchance.setChcDueId(rs.getInt(12));
				salchance.setChcDueName(rs.getString(13));
				salchance.setChcDueDate(rs.getTimestamp(14));
				salchance.setChcStatus(rs.getString(15));
				list.add(salchance);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			UtilConnect.close(rs);
			UtilConnect.close(conn);
			UtilConnect.close(st);
		}
		return list;
	}

}
