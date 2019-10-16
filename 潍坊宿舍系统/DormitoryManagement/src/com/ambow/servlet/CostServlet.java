package com.ambow.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.ambow.dao.StudentDao;
import com.ambow.entity.CostEntity;
import com.ambow.entity.StudentEntity;
import com.ambow.service.CostService;

public class CostServlet extends HttpServlet {
	CostService costService=new CostService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter("method");
		if (method.equals("showstudentcost")) {
			showstudentcost(request, response);
		}else if (method.equals("addstudentcost")) {
			addstudentcost(request, response);
		}
	}


	private void addstudentcost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String costid=request.getParameter("id");
		String stuid=request.getParameter("stuid");
		if(costService.addStudentCost(stuid, costid)){
			showstudentcost1(request,response);
		}
	}

	private void showstudentcost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		//首先获得当前登录学生
		String id = request.getParameter("id");
		StudentEntity student = new StudentDao().getStudentById(Integer.parseInt(id));
		List<CostEntity> list=costService.getStudentCostNum(student.getStuid());//学生2需要缴费条数
		//List<StudentCost> list1 = costService.getAllStudentCost();//学生已缴费记录
		List<Integer> list1=costService.getAlreadyCost(id, list);
		HashMap<CostEntity, Integer> map = new LinkedHashMap<CostEntity,Integer>();
		for(int i = 0;i<list.size();i++){
			map.put(list.get(i), list1.get(i));
		}
		request.setAttribute("map",map);
		request.getRequestDispatcher("jsp_student/showstudentcost.jsp").forward(request, response);	
	}
	private void showstudentcost1(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		//首先获得当前登录学生
		HttpSession session=request.getSession();
		StudentEntity student1 =(StudentEntity) session.getAttribute("login");
		StudentEntity student = new StudentDao().getStudentById(student1.getStuid());
		List<CostEntity> list=costService.getStudentCostNum(student.getStuid());//学生2需要缴费条数
		//List<StudentCost> list1 = costService.getAllStudentCost();//学生已缴费记录
		List<Integer> list1=costService.getAlreadyCost(String.valueOf(student1.getStuid()),list);
		HashMap<CostEntity, Integer> map = new LinkedHashMap<CostEntity,Integer>();
		for(int i = 0;i<list.size();i++){
			map.put(list.get(i), list1.get(i));
		}
		request.setAttribute("map",map);
		request.getRequestDispatcher("jsp_student/showstudentcost.jsp").forward(request, response);	
	}

}
