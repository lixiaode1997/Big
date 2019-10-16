package com.ambow.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ambow.entity.BuildingEntity;
import com.ambow.service.BuildingService;

public class BuildingServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String method=request.getParameter("method");
		
		if(method.equals("getAll")){
			getAll(request,response);
		}
		
	}

	BuildingService service=new BuildingService();
	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*HttpSession session=request.getSession();*/
		List<BuildingEntity> list=service.getAllBuild();
		request.setAttribute("buildList", list);
		request.getRequestDispatcher("jsp_admin/add.jsp").forward(request, response);
		
	}
	
	
	
	

}
