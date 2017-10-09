package com.rlg.crm.servlet;

import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.rlg.crm.dao.CriteriaOrders;
import com.rlg.crm.dao.OrdersDetailDao;
import com.rlg.crm.dao.ResultForContri;
import com.rlg.crm.dao.impl.OrdersDetailDaoImpl;
/**
 * Servlet implementation class StatisticalServlet
 */
public class StatisticalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
		String methodName = servletPath.substring(1, servletPath.length()-6);
		
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
	
	public void queryCusContribution(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrdersDetailDao ordersDetailDao = new OrdersDetailDaoImpl();
		String cusName = request.getParameter("cus_name");
		String ordDate = request.getParameter("ord_date");
		CriteriaOrders co = new CriteriaOrders(cusName, ordDate);
		//List<ResultForContri> lists = ordersDetailDao.getMoneyForCo(co);
		//request.setAttribute("lists", lists);
		CategoryDataset dataset = ordersDetailDao.getMoneyForCo(co); 
	
        JFreeChart chart = ChartFactory.createBarChart3D( 
                           "产品销量图", // 图表标题
                           "产品", // 目录轴的显示标签
                           "销量", // 数值轴的显示标签
                            dataset, // 数据集
                            PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                            true,  // 是否显示图例(对于简单的柱状图必须是 false)
                            false, // 是否生成工具
                            false  // 是否生成 URL 链接
                            ); 
        //中文乱码
        /*CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();  
        CategoryAxis domainAxis = categoryplot.getDomainAxis();  
        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(new Font("黑体", Font.PLAIN, 20));      
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));  
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));  
        numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
        numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));  
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
           */                
        FileOutputStream fos_jpg = null; 
        try { 
            fos_jpg = new FileOutputStream("F:\\code\\eclipse\\CrmSys\\WebContent\\productSales.jpg"); 
            ChartUtilities.writeChartAsJPEG(fos_jpg, 1.0f,chart,400,300,null); 
            String path = this.getServletContext().getContextPath();
            System.out.println(path+"mmmmmmmmmmm");
            this.getServletContext().getRequestDispatcher("/statistical_table/cus_contribution_analysis.jsp").forward(request, response);
        } finally { 
            try { 
                fos_jpg.close(); 
            } catch (Exception e) {} 
        } 
		request.getRequestDispatcher("/statistical_table/cus_contribution_analysis.jsp").forward(request, response);
	}

}
