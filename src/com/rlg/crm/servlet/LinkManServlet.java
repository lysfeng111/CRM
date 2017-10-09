package com.rlg.crm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rlg.crm.dao.CustomMessageDao;
import com.rlg.crm.dao.LinkManDao;
import com.rlg.crm.dao.impl.CustomMessageDaoImpl;
import com.rlg.crm.dao.impl.LinkManDaoImpl;
import com.rlg.crm.domain.Customer;
import com.rlg.crm.domain.Linkman;

public class LinkManServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String flag=request.getParameter("flag");
		System.out.println(flag+"方法");
		String forward="/page/linkMan.jsp";
		LinkManDao lmd=new LinkManDaoImpl();
		CustomMessageDao cmd=new CustomMessageDaoImpl();
		
		if("query".equals(flag)){
			int cusId=Integer.parseInt(request.getParameter("cusId"));
			
			
		    List<Linkman> list=cmd.getLinkmanByWhere(" and lin_cus_id="+cusId);
		    request.setAttribute("linkmanlist", list);
		    request.setAttribute("cusId",cusId );
		}
		if("save".equals(flag)){
			int id=Integer.parseInt(request.getParameter("id"));
			Linkman linkman=lmd.seleectById(id);
			
			linkman.setLinName(request.getParameter("lkmName"));
			linkman.setLinSex(request.getParameter("sex"));
			linkman.setLinPost(request.getParameter("lkmPostion"));
			linkman.setLinTelephone(request.getParameter("lkmTel"));
			linkman.setLinMobile(request.getParameter("lkmMobile"));
			linkman.setLinMemo(request.getParameter("lkmMemo"));
			
			lmd.upd(linkman);
			int cusId=linkman.getCustomer().getCusId();
			System.out.println("cusId="+cusId);
			List<Linkman> list=cmd.getLinkmanByWhere(" and lin_cus_id="+cusId);
			request.setAttribute("linkmanlist", list);
			request.setAttribute("cusId",cusId );
			//todo 跳转到list列表页
		}
		//增加
		if("add".equals(flag)){
			int cusId=Integer.parseInt(request.getParameter("cusId"));
			System.out.println("cusId="+cusId);
			Linkman linkman=new Linkman();
			Customer cus=new Customer();
			cus.setCusId(cusId);
			linkman.setCustomer(cus);
			linkman.setLinName(request.getParameter("lkmName"));
			linkman.setLinSex(request.getParameter("sex"));
			linkman.setLinPost(request.getParameter("lkmPostion"));
			linkman.setLinTelephone(request.getParameter("lkmTel"));
			linkman.setLinMobile(request.getParameter("lkmMobile"));
			linkman.setLinMemo(request.getParameter("lkmMemo"));
			
			lmd.add(linkman);
			
			//返回到list页面
			List<Linkman> list=cmd.getLinkmanByWhere(" and lin_cus_id="+cusId);
		    request.setAttribute("linkmanlist", list);
		    request.setAttribute("cusId",cusId );
		}
		if("del".equals(flag)){
			int cusId=Integer.parseInt(request.getParameter("cusId"));
			int id=Integer.parseInt(request.getParameter("id"));
			lmd.del(id);
			
		    List<Linkman> list=cmd.getLinkmanByWhere(" and lin_cus_id="+cusId);
		    request.setAttribute("linkmanlist", list);
		    request.setAttribute("cusId",cusId );
		}
		if("update".equals(flag)){
			int id=Integer.parseInt(request.getParameter("id"));
			System.out.println("linkman_ID+"+id);
			Linkman linkman=lmd.seleectById(id);
			request.setAttribute("linkman", linkman);
			forward="/page/updatelinkMan.jsp";
		}
		request.getRequestDispatcher(forward).forward(request, response);
		
	}


	
	
}
