package com.ambow.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ambow.dao.AdminDao;
import com.ambow.entity.AdminEntity;
import com.ambow.entity.BoradEntity;
import com.ambow.service.AdminService;
import com.ambow.service.BoradService;
import com.ambow.service.RecordService;

public class BoradServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf-8");
		
		String method=request.getParameter("method");
		
		if(method.equals("getAll")){
			getAll(request,response);
		}else if(method.equals("del")){
			del(request,response);
		}else if(method.equals("add")){
			add(request,response);
		}else if(method.equals("update")){
			update(request,response);
		}else if(method.equals("getByid")){
			getByid(request,response);
		}else if(method.equals("getTime")){
			getTime(request,response);
		}else if(method.equals("getAll2")){
			getAll2(request,response);
		}else if(method.equals("getAll3")){
			getAll3(request,response);
		}else if(method.equals("getAll4")){
			getAll4(request,response);
		}
	}


	
	private void getAll4(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BoradEntity> list=service.getAll();
		request.setAttribute("list", list);
		request.getRequestDispatcher("studentBody.jsp").forward(request, response);
	}


	private void getAll3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BoradEntity> list=service.getAll();
		request.setAttribute("list", list);
		request.getRequestDispatcher("student_index.jsp").forward(request, response);	}
	
	private void getAll2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BoradEntity> list=service.getAll();
		request.setAttribute("list", list);
		request.getRequestDispatcher("Borad/studentGetAll.jsp").forward(request, response);
	}


	private void getTime(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str=new RecordService().getTime();
		
		request.setAttribute("time", str);
		request.getRequestDispatcher("Borad/add.jsp").forward(request, response);
	}


	private void getByid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		List<AdminEntity> list=new AdminService().getAllAdmin();
		request.setAttribute("li", list);
		request.setAttribute("d", service.getById(id));
		request.getRequestDispatcher("Borad/getById.jsp").forward(request, response);
	}


	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id=request.getParameter("id");
		String content=request.getParameter("boradcontent");
		String date=request.getParameter("boradtime");
		String person=request.getParameter("boradperson");
		
		BoradEntity borad=new BoradEntity();
		borad.setBoradId(Integer.parseInt(id));
		borad.setBoradCOntent(content);
		borad.setBoradDate(Date.valueOf(date));
		borad.setAdmin(new AdminDao().getAdminById(Integer.parseInt(person)));
		
		service.updateBorad(borad);
		response.sendRedirect("BoradServlet?method=getAll");
		
	}


	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content=request.getParameter("boradcontent");
		String date=request.getParameter("boradtime");
		String person=request.getParameter("boradperson");
		BoradEntity b=new BoradEntity();
		b.setBoradDate(Date.valueOf(date));
		b.setBoradCOntent(content);
		b.setAdmin(new AdminDao().getAdminById(Integer.parseInt(person)));
		
		service.add(b);
		
		List<AdminEntity> list=new AdminService().getAllAdmin();
		
		request.setAttribute("li", list);
		request.getRequestDispatcher("BoradServlet?method=getAll").forward(request, response);
	
		
		
	}


	private void del(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id=request.getParameter("id");
		service.delBorad(id);
		response.sendRedirect("BoradServlet?method=getAll");
	}


	BoradService service=new BoradService();
	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BoradEntity> list=service.getAll();
		request.setAttribute("list", list);
		request.getRequestDispatcher("Borad/getAll.jsp").forward(request, response);
	}

}
