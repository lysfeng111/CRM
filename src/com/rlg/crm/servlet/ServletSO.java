package com.rlg.crm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rlg.crm.dao.SaleOpportunityDao;
import com.rlg.crm.dao.impl.SaleOpportuntiyDaoImpl;
import com.rlg.crm.domain.SalChance;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ServletSO
 */
public class ServletSO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 设置响应内容类型
		response.setContentType("text/html;charset=utf-8");
		//设置请求内容类型
		request.setCharacterEncoding("utf-8");//post请求方案解决传输乱码
		//get请求方案解决传输乱码String name =new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8")
		//得到客户端请求,解决没有输入但值为""错误，赋值null,为后面做判断
		String chc_cust_name;
		String chc_title;
		String chc_linkman;
		if(request.getParameter("chc_cust_name").equals("")){
			chc_cust_name = null;
		}else{
			String chc_cust_nametmp = new String(request.getParameter("chc_cust_name").getBytes("ISO8859-1"),"UTF-8");
			chc_cust_name = chc_cust_nametmp;
		}
		if(request.getParameter("chc_title").equals("")){
			chc_title = null;
		}else{
			String chc_titletmp = new String(request.getParameter("chc_title").getBytes("ISO8859-1"),"UTF-8");
			chc_title = chc_titletmp;
		}
		if(request.getParameter("chc_linkman").equals("")){
			chc_linkman = null;
		}else{
			String chc_linkmantmp = new String(request.getParameter("chc_linkman").getBytes("ISO8859-1"),"UTF-8");
			chc_linkman = chc_linkmantmp;
		}

		System.out.println("输入是"+request.getParameter("chc_cust_name")+request.getParameter("chc_title")+request.getParameter("chc_linkman"));
		//从数据库查询
		SaleOpportunityDao saleopportuntiyd = new SaleOpportuntiyDaoImpl();
		List<SalChance> list = new ArrayList<SalChance>();
		//查询并返回结果，结果保存在list
		list = saleopportuntiyd.getByWhere(chc_cust_name,chc_title,chc_linkman);
		//System.out.println(list);
		//结果转换json
		JSONArray json = new JSONArray();
		JSONArray jsontmp = JSONArray.fromObject(list);//把list转为jsonarray
		//json数据的编辑
		for (int i = 0; i < list.size(); i++) {
			JSONObject jsonObj = jsontmp.getJSONObject(i);//从jsonarray获得jsonObject;
			//时间格式化
			JSONObject jsontime = (JSONObject) jsonObj.get("chcCreateDate");
			int s = jsontime.getInt("seconds");
			int min = jsontime.getInt("minutes");
			int h = jsontime.getInt("hours");
			int d = jsontime.getInt("day");
			int m = jsontime.getInt("month");
			int y = jsontime.getInt("year")-100+2000;
			String data = y+"-"+m+"-"+d+" "+h+":"+min+":"+s;
			//替换和添加数据
			jsonObj.put("chcCreateDate", data);
			jsonObj.put("operate", "<a href='EditSaleOpportunity.html'>修改 </a><a href='#'>删除 </a><a href='AssignSaleOpportunity.html'>指派 </a>");
			json.add(i, jsonObj);
		}
		String str = json.toString();//把json转换为String
		//System.out.println(json);
		//向页面返回数据
		PrintWriter out = response.getWriter();
		out.print(str);//可以输出对象
		//out.write(str);//write只能输出字符串
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
