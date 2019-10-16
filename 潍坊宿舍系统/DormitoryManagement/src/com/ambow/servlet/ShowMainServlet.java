package com.ambow.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ambow.service.AdminService;

public class ShowMainServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String method = request.getParameter("method");
		if(method.equals("showMainStudent")){
			showMainStudent(request,response);
		}else if(method.equals("showMainAdmin")){
			showMainAdmin(request,response);
		}else if(method.equals("showMainSuperAdmin")){
			showMainSuperAdmin(request,response);
		}

	}


	private void showMainSuperAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int adminnum=new AdminService().getAllAdmin().size();
		request.setAttribute("adminnum", adminnum);
		System.out.println(adminnum);
		System.out.println("..............................");
		request.getRequestDispatcher("bgindex.jsp").forward(request, response);
		
	}


	private void showMainAdmin(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


	private void showMainStudent(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	
}
