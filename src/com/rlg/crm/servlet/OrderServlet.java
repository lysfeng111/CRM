package com.rlg.crm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rlg.crm.dao.CustomMessageDao;
import com.rlg.crm.dao.LinkManDao;
import com.rlg.crm.dao.OrderDao;
import com.rlg.crm.dao.impl.CustomMessageDaoImpl;
import com.rlg.crm.dao.impl.LinkManDaoImpl;
import com.rlg.crm.dao.impl.OrderDaoImpl;
import com.rlg.crm.domain.Orders;
import com.rlg.crm.domain.OrdersDetail;

public class OrderServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String flag=request.getParameter("flag");
		System.out.println(flag+"方法");
		String forward="/page/linkMan.jsp";
		OrderDao ord=new OrderDaoImpl();
		CustomMessageDao cmd=new CustomMessageDaoImpl();
		
		if("query".equals(flag)){
			int cus_id=Integer.parseInt(request.getParameter("cusId"));
			List<Orders> list=ord.selectAllOrders(cus_id);
			request.setAttribute("orderslist", list);
			forward="/page/orders.jsp";
		}
		if("detil".equals(flag)){
			int id=Integer.parseInt(request.getParameter("id"));
			List<OrdersDetail> list=ord.selectOrdersDetailById(id);
			Orders orders=ord.selectOrdersById(id);
			
			request.setAttribute("orderDetilList", list);
			request.setAttribute("orders", orders);
			
			forward="/page/ordersDetil.jsp";
			
		}
		
		request.getRequestDispatcher(forward).forward(request, response);
	}
	

}
