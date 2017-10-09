package com.rlg.crm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rlg.crm.dao.ConsumerDao;
import com.rlg.crm.dao.impl.ConsumerDaoImpl;
import com.rlg.crm.domain.Consumer;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");//文本信息
		response.setCharacterEncoding("utf-8");
		String flag = request.getParameter("flag");
		 PrintWriter out = response.getWriter();
		System.out.println(flag + "方法");
		ConsumerDao con=new ConsumerDaoImpl();
		if("login".equals(flag)){
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			
			int temp=con.login(name, password);
			if(temp>0){
				Consumer user=con.user(name, password);
				request.setAttribute("user", user);
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
			}else{
				
				response.sendRedirect("login.jsp?errMSG=4");
			}
		}
		if("check".equals(flag)){
			String name = new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");
			System.out.println("name="+name);
			
			boolean temp=con.check(name);
			//有名字返回的ture
			if(temp){
				 out.write("1");		 
				 
			 }else{
				 out.write("2");
			 }
			 
			 out.close();
		}

	}
	

}
