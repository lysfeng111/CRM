package com.rlg.crm.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class Page {
	public  static List paging(HttpServletRequest request, List list){
		 
		List subMessList=null; 
        int showCount =5; 
        int showPage = 1;
        int size =list.size(); 
        int pageCount = (size-1)/showCount + 1;
        if(size<showCount)
        {
           subMessList = list;
         }
        String page = request.getParameter("page");
        if(page != null)
        { 
           showPage = Integer.parseInt(page);
           if(showPage<=0){
        	   showPage=1;
           }
        }
        if((showPage*showCount)<size)
        {
           subMessList = list.subList((showPage-1)*showCount,showPage*showCount);
         }
        else
       {
           subMessList=list.subList((showPage-1)*showCount,size);
        }
           request.setAttribute("showPage",new Integer(showPage));
           request.setAttribute("pageCount",new Integer(pageCount));
           request.setAttribute("size",new Integer(size));
           return subMessList;
	}
	
}
