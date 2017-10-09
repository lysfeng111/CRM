package com.rlg.crm.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rlg.crm.dao.CriteriaServe;
import com.rlg.crm.dao.ServeDao;
import com.rlg.crm.dao.impl.ServeDaoImpl;
import com.rlg.crm.domain.Customer;
import com.rlg.crm.domain.Serve;
import com.rlg.crm.domain.ServeType;
import com.rlg.crm.util.Page;

/**
 * Servlet implementation class Service
 */
public class ServeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private ServeDao serveDao = new ServeDaoImpl();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//1. 获取ServletPath：/edit.do 或 /addCustomer.do
		String servletPath = request.getServletPath();
		//2.去除 / 和 .do ， 得到类似于 edit或 addCustomer 这样的字符串
		String methodName = servletPath.substring(1, servletPath.length()-8);
		
		try {
			//3.利用反射获取 methodName 对应的方法
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			//4.利用反射调用对应的方法
			method.invoke(this,request,response);
		} catch (Exception e) {
			e.printStackTrace();
			// 可以有一些响应
			response.sendRedirect("error.jsp");
		}
	}
	/**
	 * service_create.jsp中
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void initNew(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		List<ServeType> serTypes = serveDao.getServeType();
		request.setAttribute("serTypes", serTypes);
		
		List<Customer> customers = serveDao.getCustomer();
		request.setAttribute("customers", customers);
		request.getRequestDispatcher("/service_management/service_create.jsp").forward(request, response);
	}
	
	/**
	 * servic_create.jsp中
	 * 向数据库中新增服务
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addServe(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		String typeStr = request.getParameter("ser_type");
		String serResume = request.getParameter("ser_resume");
		String cusStr = request.getParameter("ser_cus");
		String stateStr = request.getParameter("ser_state");	
		String serRequest = request.getParameter("ser_request");
		String serCreaterName = request.getParameter("ser_creater_name");
		
		String serCreaterDate = request.getParameter("ser_creater_date");
		
		int serType = 0;
		if(typeStr != null){
			serType = Integer.parseInt(typeStr);
		}
		int serCus = 0;
		if(cusStr != null){
			serCus = Integer.parseInt(cusStr);
		}
		long serState = 0;
		if(stateStr != null){
			serState = 1;
		}
		
		Serve serve = new Serve();
		Customer customer = new Customer();
		customer.setCusId(serCus);
		ServeType serveType = new ServeType();
		serveType.setSeId(serType);
		
		serve.setServeType(serveType);
		serve.setSerResume(serResume);
		serve.setCustomer(customer);
		serve.setSerState(serState);
		serve.setSerRequest(serRequest);
		serve.setSerCreaterName(serCreaterName);
		serve.setSerCreaterDate(Timestamp.valueOf(serCreaterDate));
		
		serveDao.addServe(serve);
		
		response.sendRedirect(request.getContextPath()+"/service_management/service_create.jsp");
	}
	
	
	/**
	 * service_allocation.jsp中
	 * 服务分配
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void editAllot(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String idStr = request.getParameter("id");
		int id = 0;
		try{
			id = Integer.parseInt(idStr);
		}catch(Exception e){
			response.getWriter().write("id不存在");
		}
		if(id>0){
		
			Serve serve = serveDao.getServeForId(id);
			
			/*Serve serve = new Serve();
			Customer customer = new Customer();
			customer.setCusId(result.getCustomer().getCusId());
			ServeType serveType = new ServeType();
			serveType.setSeId(result.getServeType().getSeId());
			
			serve.setSerId(result.getSerId());
			serve.setCustomer(customer);
			serve.setSerResume(result.getSerResume());
			serve.setServeType(serveType);
			serve.setSerCreaterName(result.getSerCreaterName());
			serve.setSerCreaterDate(result.getSerCreaterDate());
			
			serve.setSerState(result.getSerState());
			serve.setSerRequest(result.getSerRequest());*/
			
			serve.setSerAllotName(request.getParameter("serAllotName"));
			serve.setSerAllotDate(Timestamp.valueOf(request.getParameter("ser_allot_date")));
			serve.setSerState(2l);
			serveDao.updateServe(serve);
		}
		response.sendRedirect(request.getContextPath()+"/queryAllocation.doServe");
	}
	/**
	 * serve_allocation.jsp中
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void moveServe(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

		String idStr = request.getParameter("id");
		int id = 0;
		try{
			id = Integer.parseInt(idStr);
		}catch(Exception e){
			response.getWriter().write("id不存在");
		}
		if(id>0){
			serveDao.delete(id);
		}
		response.sendRedirect(request.getContextPath()+"/queryAllocation.doServe");
	}
	/**
	 * service_allocation.jsp中
	 * 根据cs的条件进行模糊查询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void queryAllocation(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		String serCusName = request.getParameter("ser_cus_id");
		String serResume = request.getParameter("ser_resume");
		String serType = request.getParameter("ser_type");
		String serCreaterDate = request.getParameter("ser_creater_date");
		String serState = request.getParameter("ser_state");
		if(serState == null || serState == ""){
			serState = "1";
		}
		CriteriaServe cs = new CriteriaServe(serCusName,serResume,serType,serCreaterDate,serState);
		
		List<Serve> serves= serveDao.getListServeFromCs(cs);
		serves = Page.paging(request, serves);
		request.setAttribute("serves", serves);
		
		request.getRequestDispatcher("/service_management/service_allocation.jsp").forward(request, response);
	}
	/**
	 * serve_processing_list.jsp中
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void queryProcessing(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	
		String serCusId = request.getParameter("ser_cus_id");
		String serResume = request.getParameter("ser_resume");
		String serType = request.getParameter("ser_type");
		String serCreaterDate = request.getParameter("ser_creater_date");
		String serState = request.getParameter("ser_state");
		if(serState == null || serState == ""){
			serState = "2";
		}
		CriteriaServe cs = new CriteriaServe(serCusId,serResume,serType,serCreaterDate,serState);
		
		List<Serve> serves= serveDao.getListServeFromCs(cs);
		serves = Page.paging(request, serves);
		request.setAttribute("serves", serves);
		
		request.getRequestDispatcher("/service_management/service_processing_list.jsp").forward(request, response);
	}
	/**
	 * service_processing_list.jsp中
	 * 服务处理流程
     * 首先查询得到状态为“已分配”的服务单据，选择一个进行处理。
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void edit(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String idStr = request.getParameter("id");
		int id = 0;
		try{
			id = Integer.parseInt(idStr);
		}catch(Exception e){
			response.getWriter().write("id不存在");
		}
		if(id>0){
			Serve serve = serveDao.getServeForId(id);
		
			request.setAttribute("serve", serve);
		}
		request.getRequestDispatcher("/service_management/service_processing_edit.jsp").forward(request, response);
		
	}
	
	/**
	 * service_processing_edit.jsp中
	 * 客户经理手动处理
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void editServe(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		Serve serve = serveDao.getServeForId(Integer.parseInt(request.getParameter("id")));
		
		serve.setSerHandle(request.getParameter("ser_handle"));
		serve.setSerHandleName(request.getParameter("ser_handle_name"));
		serve.setSerHandleDate(Timestamp.valueOf(request.getParameter("ser_handle_date")));
		serve.setSerState(3l);
		
		serveDao.updateServe(serve);
		response.sendRedirect(request.getContextPath()+"/queryProcessing.doServe");
	}
	
	/**
	 * service_feedback_list.jsp中
	 * 首先查询得到状态为“已处理”的服务单据，
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void queryFeedback(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	
		String serCusId = request.getParameter("ser_cus_id");
		String serResume = request.getParameter("ser_resume");
		String serType = request.getParameter("ser_type");
		String serCreaterDate = request.getParameter("ser_creater_date");
		String serState = request.getParameter("ser_state");
		if(serState == null || serState == ""){
			serState = "3";
		}
		CriteriaServe cs = new CriteriaServe(serCusId,serResume,serType,serCreaterDate,serState);
		
		List<Serve> serves= serveDao.getListServeFromCs(cs);
		serves = Page.paging(request, serves);
		request.setAttribute("serves", serves);
		
		request.getRequestDispatcher("/service_management/service_feedback_list.jsp").forward(request, response);
	}
	/**
	 * service_feedback_list.jsp中
	 * 选择一个进行反馈。
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void editFeedbackList(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String idStr = request.getParameter("id");
		int id = 0;
		try{
			id = Integer.parseInt(idStr);
		}catch(Exception e){
			response.getWriter().write("id不存在");
		}
		if(id>0){
			Serve serve = serveDao.getServeForId(id);
		
			request.setAttribute("serve", serve);
		}
		request.getRequestDispatcher("/service_management/service_feedback_edit.jsp").forward(request, response);
		
	}
	/**
	 * service_feedback_edit.jsp中
	 * 填写处理结果和满意度后提交。
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void editFeedbackEdit(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		Serve serve = serveDao.getServeForId(Integer.parseInt(request.getParameter("id")));
		long satisfce = Long.parseLong(request.getParameter("ser_satisfcing"));
		serve.setSerResult(request.getParameter("ser_result"));
		serve.setSerSatisfcing(satisfce);
		if(satisfce>=3){
			serve.setSerState(4l);
		}else{
			serve.setSerState(2l);
		}
		
		serveDao.updateServe(serve);
		response.sendRedirect(request.getContextPath()+"/queryFeedback.doServe");
	}
	
	/**
	 * service_archive_list.jsp中
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void queryArchive(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	
		String serCusId = request.getParameter("ser_cus_id");
		String serResume = request.getParameter("ser_resume");
		String serType = request.getParameter("ser_type");
		String serCreaterDate = request.getParameter("ser_creater_date");
		String serState = request.getParameter("ser_state");
		
		CriteriaServe cs = new CriteriaServe(serCusId,serResume,serType,serCreaterDate,serState);
		
		List<Serve> serves= serveDao.getListServeFromCs(cs);
		serves = Page.paging(request, serves);
		request.setAttribute("serves", serves);
		
		request.getRequestDispatcher("/service_management/service_archive_list.jsp").forward(request, response);
	}
	/**
	 * service_archive_list.jsp中
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void editArchiveList(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String idStr = request.getParameter("id");
		int id = 0;
		try{
			id = Integer.parseInt(idStr);
		}catch(Exception e){
			response.getWriter().write("id不存在");
		}
		if(id>0){
			Serve serve = serveDao.getServeForId(id);
		
			request.setAttribute("serve", serve);
		}
		request.getRequestDispatcher("/service_management/service_archive_edit.jsp").forward(request, response);
		
	}
	
}
