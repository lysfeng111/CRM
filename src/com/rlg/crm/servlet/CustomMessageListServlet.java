package com.rlg.crm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rlg.crm.dao.CustomMessageDao;
import com.rlg.crm.dao.impl.CustomMessageDaoImpl;
import com.rlg.crm.domain.Area;
import com.rlg.crm.domain.Customer;
import com.rlg.crm.domain.CustomerRank;
import com.rlg.crm.util.Page;

public class CustomMessageListServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String flag=request.getParameter("flag");
		System.out.println(flag);
		String forward="customerlist?flag=query";
		CustomMessageDao dao = new CustomMessageDaoImpl();
		if("query".equals(flag)){
			String CusName = request.getParameter("CusName");
			String CusManager = request.getParameter("CusManager");
			String CusState = request.getParameter("CusState");
			String sql = "";
			if(CusName!=null && !"".equals(CusName)){
				sql = sql+" and CUS_NAME like '%"+CusName+"%' "; 
			}
			if(CusManager!=null && !"".equals(CusManager)){
				sql = sql+" and chc_title like '%"+CusManager+"%' "; 
			}
			if(CusState!=null && !"".equals(CusState)){
				sql = sql+" and CUS_STATE like '%"+CusState+"%' "; 
			}
			
			List<Customer> list = dao.getCustomByWhere(sql);
			list = Page.paging(request, list);
			request.setAttribute("cusList", list);
			request.getRequestDispatcher("/page/cusManage.jsp").forward(request, response);
		}
		if("update".equals(flag)){
			int id=Integer.parseInt(request.getParameter("cus_id"));
			System.out.println("cus_id"+id);
			Customer cus= dao.getCustomerById(id);
			List<Area> arelist=dao.getAreaByWhere();
			List<CustomerRank> ranklist=dao.getCustomerRankByWhere();
			
			request.setAttribute("cusId", cus.getCusId());
			request.setAttribute("arealist", arelist);
			request.setAttribute("ranklist", ranklist);
			request.setAttribute("customer", cus);
			forward="page/cusManageUpdate.jsp";
			request.getRequestDispatcher(forward).forward(request, response);
		}
		if("save".equals(flag)){
			int id=Integer.parseInt(request.getParameter("id"));
			Customer customer=dao.getCustomerById(id);
			customer.setCusName(request.getParameter("custName"));
			Area area=new Area();
			area.setAreId(Integer.parseInt(request.getParameter("custArea")));
			customer.setArea(area);
			customer.setCusManager(request.getParameter("custManagerName"));
			CustomerRank customerRank=new CustomerRank();
			customerRank.setCkId(Integer.parseInt(request.getParameter("custRank")));
			customer.setCustomerRank(customerRank);
			customer.setCusSuccessProbability(request.getParameter("custSatisfy"));
			customer.setCusChangeDescribe(request.getParameter("custCredit"));
			customer.setCusAddress(request.getParameter("custAddr"));
			customer.setCusPostalCode(request.getParameter("postCode"));
			customer.setCusTelephone(request.getParameter("custTel"));
			customer.setCusFasimile(request.getParameter("custFax"));
			customer.setCusUrl(request.getParameter("custWebsite"));
			customer.setCusRegisterNumber(request.getParameter("custLicenceNo"));
			customer.setCusLegalPerson(request.getParameter("custLegalPeson"));
			customer.setCusRegisterMoney(Double.valueOf(request.getParameter("custBankMoney")));
			customer.setCusRmb(Double.valueOf(request.getParameter("custRmb")));
			customer.setCusOpeningBank(request.getParameter("custBank"));
			customer.setCusBankAccount(request.getParameter("custBankAccount"));
			customer.setCusCrownRent(request.getParameter("custLocalTaxNo"));
			customer.setCusStateTaxes(request.getParameter("custNationalTaxNo"));
			
			dao.updateCustomer(customer);
			request.getRequestDispatcher(forward).forward(request, response);
		}
		//request.getRequestDispatcher(forward).forward(request, response);
	}
	
}
