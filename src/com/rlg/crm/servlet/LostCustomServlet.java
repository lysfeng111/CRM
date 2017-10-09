package com.rlg.crm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rlg.crm.dao.CustomMessageDao;
import com.rlg.crm.dao.LostCustomDao;
import com.rlg.crm.dao.impl.CustomMessageDaoImpl;
import com.rlg.crm.dao.impl.LostCustomDaoImpl;
import com.rlg.crm.domain.Customer;
import com.rlg.crm.domain.Lost;
import com.rlg.crm.util.Page;

public class LostCustomServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String flag=request.getParameter("flag");
		//HttpSession flag=request.getSession();
		//String ss=(String)flag.getAttribute("flag");
		String forward="/page/lost.jsp";
		
		
		
		System.out.println(flag);
		 LostCustomDao dao = new LostCustomDaoImpl();
		if("query".equals(flag)){
			String losCustomer = request.getParameter("losCustomer");
			System.out.println(losCustomer);
			String losManager = request.getParameter("losManager");
			System.out.println(losManager);
			String losState = request.getParameter("losState");
			System.out.println(losState);
			String sql = "";
			if(losCustomer!=null && !"".equals(losCustomer)){
				sql = sql+" and LOS_CUSTOMER like '%"+losCustomer+"%' "; 
			}
			if(losManager!=null && !"".equals(losManager)){
				sql = sql+" and LOS_MANAGER like '%"+losManager+"%' "; 
			}
			if(losState!=null && !"".equals(losState)){
				sql = sql+" and LOS_STATE like '%"+losState+"%' "; 
			}
			List<Lost> list = dao.selectByWhere(sql);
			//list = Page.paging(request, list);
			request.setAttribute("lostList", list);		
			}
		
		
		if("stopLost".equals(flag)){
				//int id =Integer.parseInt(request.getParameter("lostId"));
			int id=Integer.parseInt(request.getParameter("lostId"));
			System.out.println(id+"ididid");
				Lost lost=dao.selectById(id);
				request.setAttribute("lost", lost);
				forward="/page/stopLost.jsp";
			}
		if("save".equals(flag)){
			
			int id=Integer.parseInt(request.getParameter("losId"));
			
			Lost lost=dao.selectById(id);
			String losRespite=lost.getLosRespite();
			losRespite+=request.getParameter("losRespite");
			System.out.println(losRespite);
			lost.setLosRespite(losRespite);
			dao.update(lost);
			request.setAttribute("lost", lost);
			forward="/page/stopLost.jsp";
			System.out.println(flag);
		}
		if("sure".equals(flag)){
			String lstReason=request.getParameter("lstReason");
			int id=Integer.parseInt(request.getParameter("lostId"));
			Lost lost=dao.selectById(id);
			lost.setLosReason(lstReason);
			dao.update(lost);
			request.setAttribute("lostsure", lost);
			forward="/page/sureLost.jsp";
		}
		request.getRequestDispatcher(forward).forward(request, response);	
	}
}
	


