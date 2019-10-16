package com.ambow.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ambow.entity.DeptEntity;
import com.ambow.service.DeptService;

public class DeptServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method.equals("getAll")) {
			getAll(request, response);
		}

	}

	DeptService deptService = new DeptService();

	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<DeptEntity> dept=deptService.getAllDept();
		request.setAttribute("uio", dept);
		request.getRequestDispatcher("jsp_admin/ruzhu.jsp").forward(request, response);
	}

}
