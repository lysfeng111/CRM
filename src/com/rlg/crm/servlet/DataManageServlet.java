package com.rlg.crm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DataManageServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String flag=request.getParameter("flag");
		System.out.println(flag+"方法");
		String forward="/page/linkMan.jsp";
		if("add".equals(flag)){
			
		}
		if("query".equals(flag)){
			
		}
		if("update".equals(flag)){
			
		}
	}
	

}
