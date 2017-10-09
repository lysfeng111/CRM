package com.rlg.crm.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rlg.crm.dao.AssortDao;
import com.rlg.crm.dao.CustomMessageDao;
import com.rlg.crm.dao.impl.AssortDaoImpl;
import com.rlg.crm.dao.impl.CustomMessageDaoImpl;
import com.rlg.crm.domain.Assort;
import com.rlg.crm.domain.Customer;

public class AssortServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("utf-8");
		String flag = request.getParameter("flag");
		System.out.println(flag + "方法");
		String forward = "/page/assort.jsp";
		AssortDao ass = new AssortDaoImpl();
		CustomMessageDao cmd = new CustomMessageDaoImpl();

		if ("query".equals(flag)) {
			int cusId = Integer.parseInt(request.getParameter("cusId"));
			System.out.println("assortQuery+cusId=" + cusId);

			List<Assort> list = cmd.getAssortsByWhere(" and ass_cus_id=" + cusId);
			if (list == null) {
				System.out.println("list为空");
			} else {
				System.out.println("list不为空");
			}
			request.setAttribute("assortlist", list);
			request.setAttribute("cusId", cusId);
		}
		if ("add".equals(flag)) {
			int cusId = Integer.parseInt(request.getParameter("cusId"));
			Assort assort = new Assort();
			String da = request.getParameter("atvDate");

			// 对String与data类型数据进行转换
			if (da != null && !(da.equals(""))) {
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date;
				try {
					date = format1.parse(da);
					// SimpleDateFormat format = new
					// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String sdate = format2.format(date);

					Timestamp fTimestamp = Timestamp.valueOf(sdate);
					assort.setAssDate(fTimestamp);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			Customer customer = new Customer();
			customer.setCusId(cusId);
			assort.setCustomer(customer);
			assort.setAssPalce(request.getParameter("atvPlace"));
			assort.setAssDetail(request.getParameter("detil"));
			assort.setAssMemo(request.getParameter("memo"));
			assort.setAssResume(request.getParameter("resume"));
			ass.add(assort);

			List<Assort> list = cmd.getAssortsByWhere(" and ass_cus_id=" + cusId);
			request.setAttribute("assortlist", list);
			request.setAttribute("cusId", cusId);
		}
		if ("update".equals(flag)) {
			int id = Integer.parseInt(request.getParameter("id"));
			Assort assort = ass.seleectById(id);
			request.setAttribute("assort", assort);
			forward = "page/updateAssort.jsp";
		}
		if ("del".equals(flag)) {
			int id = Integer.parseInt(request.getParameter("id"));
			ass.del(id);

			int cusId = Integer.parseInt(request.getParameter("cusId"));
			List<Assort> list = cmd.getAssortsByWhere(" and ass_cus_id=" + cusId);
			request.setAttribute("assortlist", list);
			request.setAttribute("cusId", cusId);
		}
		if ("save".equals(flag)) {
			int id = Integer.parseInt(request.getParameter("id"));
			Assort assort = ass.seleectById(id);

			String da = request.getParameter("atvDate");

			// 对String与data类型数据进行转换
			if (da != null && !(da.equals(""))) {
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date;
				try {
					date = format1.parse(da);
					// SimpleDateFormat format = new
					// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String sdate = format2.format(date);

					Timestamp fTimestamp = Timestamp.valueOf(sdate);
					assort.setAssDate(fTimestamp);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			assort.setAssPalce(request.getParameter("atvPlace"));
			assort.setAssResume(request.getParameter("memo"));
			assort.setAssDetail(request.getParameter("detail"));
			ass.upd(assort);

			int cusId = assort.getCustomer().getCusId();
			List<Assort> list = cmd.getAssortsByWhere(" and ass_cus_id=" + cusId);
			request.setAttribute("assortlist", list);
			request.setAttribute("cusId", cusId);
		}

		request.getRequestDispatcher(forward).forward(request, response);
	}

}
