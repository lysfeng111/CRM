package com.rlg.crm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rlg.crm.dao.CustomMessageDao;
import com.rlg.crm.dao.impl.CustomMessageDaoImpl;
import com.rlg.crm.domain.Customer;

public class SalChanceServlet extends HttpServlet{
	private static final long serialVersionUID=1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,responce);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		request.setCharacterEncoding("utf-8");
//		String flag=request.getParameter("flag");
//		System.out.println("flag");
//		CustomMessageDao dao = new CustomMessageDaoImpl();
//		if("query".equals(flag)){
//			String chcCustName = request.getParameter("chcCustName");
//			String chcTitle = request.getParameter("chcTitle");
//			String chcLinkman = request.getParameter("chcLinkman");
//			String sql = "";
//			if(chcCustName!=null && !"".equals(chcCustName)){
//				sql = sql+" and chc_cust_name like '%"+chcCustName+"%' "; 
//			}
//			if(chcTitle!=null && !"".equals(chcTitle)){
//				sql = sql+" and chc_title like '%"+chcTitle+"%' "; 
//			}
//			if(chcLinkman!=null && !"".equals(chcLinkman)){
//				sql = sql+" and chc_linkman like '%"+chcLinkman+"%' "; 
//			}
//			
//			List<Customer> list = dao.getCustomByWhere(sql);
//			//list = Page.paging(request, list);
//			request.setAttribute("salList", list);
//			
//		}
//		
//		request.getRequestDispatcher("salChanceList.jsp").forward(request, response);
//	}
//	
	}
}
