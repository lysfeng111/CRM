package com.rlg.crm.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rlg.crm.dao.NewSaleOpportuntiyDao;
import com.rlg.crm.dao.impl.NewSaleOpportuntiyDaoImpl;
import com.rlg.crm.domain.SalChance;

/**
 * Servlet implementation class ServletNSO
 */
public class ServletNSO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletNSO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 设置响应内容类型
		response.setContentType("text/html;charset=utf-8");
		//设置请求内容类型
		request.setCharacterEncoding("utf-8");
		
		//PrintWriter out = response.getWriter();
		SalChance sal = new SalChance();
		//sal.setChcId(UtilTools.StringToLong(request.getParameter("chcId")));
		sal.setChcSource(request.getParameter("chcSource"));
		//sal.setChcCreateId(UtilTools.StringToInt(request.getParameter("chcCreateId")));
		sal.setChcCreateName(request.getParameter("chcCreateName"));
		//sal.setChcCreateDate(Timestamp.valueOf(request.getParameter("chcCreateDate")));
		sal.setChcCustName(request.getParameter("chcCustName"));
		sal.setChcTitle(request.getParameter("chcTitle"));
		//sal.setChcRate(UtilTools.StringToInt(request.getParameter("chcRate")));
		sal.setChcLinkman(request.getParameter("chcLinkman"));
		sal.setChcTel(request.getParameter("chcTel"));
		sal.setChcDesc(request.getParameter("chcDesc"));	
		sal.setChcStatus("未分配");
		NewSaleOpportuntiyDao newsaleopportuntiy = new NewSaleOpportuntiyDaoImpl();
		newsaleopportuntiy.save(sal);
		
	}

}
