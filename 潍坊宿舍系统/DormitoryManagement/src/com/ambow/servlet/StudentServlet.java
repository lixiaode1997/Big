package com.ambow.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ambow.dao.StudentMiddleDao;
import com.ambow.entity.AdminEntity;
import com.ambow.entity.StudentEntity;
import com.ambow.entity.StudentMiddleEntity;
import com.ambow.entity.SuperEntity;
import com.ambow.service.AdminService;
import com.ambow.service.CostService;
import com.ambow.service.StudentMiddleService;
import com.ambow.service.StudentService;
import com.ambow.service.SuperService;

public class StudentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	StudentService service=new StudentService();
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String method=request.getParameter("method");
		
		if(method.equals("login")){
			login(request, response);
		}else if(method.equals("getById")){
			getById(request,response);
		}else if(method.equals("fenpei")){
			/*fenpei(request,response);*/
		}else if(method.equals("haveName")){
			haveName(request,response);
		}else if(method.equals("getAll")){
			getAllStudent(request,response);
		}else if(method.equals("getAllNull")){
			/*getAllNullStudent(request,response);*/
		}else if(method.equals("getWeiRuZhuStudent")){
			getWeiRuZhuStudent(request,response);
		}else if(method.equals("getWeiRuZhuStudent2")){
			getWeiRuZhuStudent2(request,response);
		}else if(method.equals("selectOne")){
			selectOne(request,response);
		}else if(method.equals("selectOne2")){
			selectOne2(request,response);
		}
	
	}


	private void selectOne2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stuno=request.getParameter("stuno");
		StudentEntity stu=service.getStuBuSno(stuno);
		int sid=stu.getStuid();
		List<String> flag=new ArrayList<String>();
		if(service.getOneRuZhuMeiRuZhu(sid)==0){
			//response.getWriter().write(0);
			flag.add("未入住");
		}else{
			//ruzhu.toString();
			flag.add("已入住");
		}
		request.setAttribute("f", flag);
		//System.out.println(flag.size());
		request.setAttribute("li",stu);
		request.getRequestDispatcher("jsp_stumanagement/getOneStudent2.jsp").forward(request, response);
	}


	private void getWeiRuZhuStudent2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<StudentEntity> stu=service.getWeiRuZhuStudent();
		request.setAttribute("list", stu);
		request.getRequestDispatcher("jsp_stumanagement/piliangruzhu.jsp").forward(request, response);
	}


	private void selectOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stuno=request.getParameter("stuno");
		StudentEntity stu=service.getStuBuSno(stuno);
		request.setAttribute("ss", stu); 
		request.getRequestDispatcher("jsp_stumanagement/getOneStudent.jsp").forward(request, response);
	}


	private void getWeiRuZhuStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<StudentEntity> stu=service.getWeiRuZhuStudent();
		request.setAttribute("list", stu);
		request.getRequestDispatcher("jsp_stumanagement/ruzhu.jsp").forward(request, response);
	}


	private void getAllStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<StudentEntity> stu=service.getAll();
		List<StudentMiddleEntity> mid=new StudentMiddleService().getAll();
		List<String> flag = new ArrayList<String>();
		HashMap<StudentEntity,String> map = new LinkedHashMap<StudentEntity,String>();
		for (StudentEntity s : stu) {	
			s.setStupay(new CostService().isCostAllOver(s.getStuid()));	
				if(new StudentMiddleDao().getStudentMiddleEntityByStuid(s.getStuid())!=null){
					flag.add("已入住");
				}else{
					flag.add("未入住");
				}
		}
		
		for(int i=0;i<stu.size();i++){
			map.put(stu.get(i), flag.get(i));
		}
		request.setAttribute("map", map);
		request.getRequestDispatcher("jsp_stumanagement/getAllStudent.jsp").forward(request, response);
	}


	/*private void getAllNullStudent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String dept=request.getParameter("dept");
		String sex=request.getParameter("sex");
		List<StudentEntity> list=service.nullStudent(Integer.parseInt(dept), Integer.parseInt(sex));
		request.setAttribute("yu", list);
		request.getRequestDispatcher("shownullstu.jsp").forward(request, response);
	}*/

	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// TODO Auto-generated method stub
		List<StudentEntity> list=service.getAll();
		request.setAttribute("all", list);
		request.getRequestDispatcher("jsp_admin/showfenpei.jsp").forward(request, response);
	}

	/*private void fenpei(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String sex=request.getParameter("sex");
		String dept=request.getParameter("dept");
		System.out.println(dept);
		//System.out.println(sex);
		//System.out.println(dept);
		String a=new DeptDao().getDeptById(Integer.parseInt(dept)).getDeptname();
		int c=a.indexOf("系");
		String b=a.substring(0,c+1 );
		System.out.println(b);
		System.out.println("这是系别："+(new DeptDao().getDeptById(Integer.parseInt(dept)).getDeptname()));
		System.out.println("这是性别："+new DeptDao().getDeptById(Integer.parseInt(dept)).getBuildid().getBuildsex());
		System.out.println("这是楼号："+new DeptDao().getDeptById(Integer.parseInt(dept)).getBuildid().getBuildid());
		service.fenpei(Integer.parseInt(dept),//需要学生的实体类返回值
				new DeptDao().getDeptById(Integer.parseInt(dept)).getBuildid().getBuildsex(),
				new DeptDao().getDeptById(Integer.parseInt(dept)).getBuildid().getBuildid());
		//request.setAttribute("", arg1);
		request.getRequestDispatcher("StudentServlet?method=getAll").forward(request, response);
	}

@Test
public void siis(){
	String a="美术系女生公寓";
	int c=a.indexOf("系");
	String b=a.substring(0,c+1);
	System.out.println(b);
}*/
	private void haveName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name=request.getParameter("loginName");
		service.haveName(name);
		if(service.haveName(name)==1){
			response.getWriter().write("success");
		}else{
			response.getWriter().write("error");
		}
	}

	private void getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		StudentEntity res=service.getAllById(id);
		res.setStupay(new CostService().isCostAllOver(Integer.parseInt(id)));
		//request.setAttribute("lol", new StudentMiddleDao().getAllById(Integer.parseInt(id)));
		//request.setAttribute("lol", new MiddleDao().getAllById(Integer.parseInt(id)));
		request.setAttribute("li", res);
		request.getRequestDispatcher("jsp_student/getAllStudent.jsp").forward(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String loginName=request.getParameter("loginName");
		String loginPwd=request.getParameter("loginPwd");
		String random=request.getParameter("random");
		String type=request.getParameter("type");
		HttpSession session=request.getSession();
		if(type.equals("student")){
			StudentEntity stu=service.login(loginName, loginPwd);
			if(stu!=null&&random.equals(session.getAttribute("checkcode_session"))){
			session.setAttribute("login", stu);
			//request.getRequestDispatcher("student_index.jsp").forward(request, response);
			request.getRequestDispatcher("BoradServlet?method=getAll3").forward(request, response);
			}else{
				response.sendRedirect("login.jsp");
			}
		}else if(type.equals("admin")){
			AdminEntity admin=new AdminService().login(loginName, loginPwd);
			if(admin!=null&&random.equals(session.getAttribute("checkcode_session"))){
				session.setAttribute("login", admin);
				request.getRequestDispatcher("admin_index.jsp").forward(request, response);
			}else{
				response.sendRedirect("login.jsp");
			}
			
		}else if(type.equals("super")){
			SuperEntity sup=new SuperService().login(loginName, loginPwd);
			if(sup!=null&&random.equals(session.getAttribute("checkcode_session"))){
				session.setAttribute("login", sup);
				//request.getRequestDispatcher("ShowMainServlet?method=showMainSuperAdmin").forward(request, response);
				request.getRequestDispatcher("bgindex.jsp").forward(request, response);
			}else{
				response.sendRedirect("login.jsp");
			}
			
		}else{
			response.sendRedirect("login.jsp");
		}
	
		
	}	

	
}
