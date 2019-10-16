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

import com.ambow.dao.DeptDao;
import com.ambow.dao.RoomDao;
import com.ambow.dao.StudentDao;
import com.ambow.dao.StudentMiddleDao;
import com.ambow.entity.AdminEntity;
import com.ambow.entity.DeptEntity;
import com.ambow.entity.RoomEntity;
import com.ambow.entity.StudentEntity;
import com.ambow.entity.StudentMiddleEntity;
import com.ambow.service.AdminService;
import com.ambow.service.StudentMiddleService;
import com.ambow.service.StudentService;

public class StudentMidServlet extends HttpServlet {

	StudentMiddleService service = new StudentMiddleService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	StudentService service1 = new StudentService();

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter("method");

		if (method.equals("getRoomBySid")) {
			getRoomBySid(request, response);
		} else if (method.equals("deleteStudentMiddle")) {
			deleteStudentMiddle(request, response);
		} else if (method.equals("getAllRuzhu2")) {
			getAllRuzhu2(request, response);
		} else if (method.equals("getAllRuZhu")) {
			getAllRuzhu(request, response);
		}else if (method.equals("piliangruzhu")) {
			piliangruzhu(request, response);
		} else if (method.equals("addMid")) {
			addMid(request, response);
		}else if(method.equals("tuiSuSelectOne")){
			tuiSuSelectOne(request,response);
		}else if(method.equals("tuiSuPiLiang")){
			tuiSuPiLiang(request,response);
		}else if(method.equals("deletepiliangtuisu")){
			deletepiliangtuisu(request,response);
		}else if(method.equals("deleteAll")){
			deleteAll(request,response);
		}else if(method.equals("deleteone")){
			deleteone(request,response);
		}
	}
	
	private void deleteone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("aaaaaaaaaaaaaaaaa");
		String year=request.getParameter("year");
		String adminid=request.getParameter("adminid");
		System.out.println(year);
		System.out.println(adminid);
		service.deleteOne(Integer.parseInt(year), Integer.parseInt(adminid));
		System.out.println("zhixingfangfa");
		request.getRequestDispatcher("StudentMidServlet?method=getAllRuZhu").forward(request, response);
	}

	private void deleteAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service.deleteAll();
		request.getRequestDispatcher("StudentMidServlet?method=getAllRuZhu").forward(request, response);
	}

	private void deletepiliangtuisu(HttpServletRequest request, HttpServletResponse response) {
		
	}

	private void tuiSuPiLiang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String year=request.getParameter("year");
		String id=request.getParameter("adminid");
		List<StudentEntity> list=new StudentService().getStudentbyYear(Integer.parseInt(year),Integer.parseInt(id));
		request.setAttribute("y", year);
		request.setAttribute("list", list);
		request.getRequestDispatcher("jsp_stumanagement/tuisubyyear.jsp").forward(request, response);
	}

	private void getAllRuzhu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("adminid");
		List<StudentMiddleEntity> list = new StudentMiddleService()
				.getAllByAdmin(new AdminService().getAdminById(Integer.parseInt(id)));
	
		request.setAttribute("list", list);
		request.getRequestDispatcher("jsp_stumanagement/getRuzhuStudentPiLiang.jsp")
				.forward(request, response);
	}

	private void tuiSuSelectOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stuno=request.getParameter("stuno");
		StudentEntity stu=new StudentService().getStuBuSno(stuno);
		request.setAttribute("ss", stu); 
		request.getRequestDispatcher("jsp_stumanagement/tuiSuSelectOne.jsp").forward(request, response);
	}
	private void addMid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String stuid=request.getParameter("stuid");
		StudentEntity stu=new StudentDao().getStudentById(Integer.parseInt(stuid));
		List<DeptEntity> allDept = new DeptDao().getAllDept();
		System.out.println("+++++++++++++++++++");
		for (int j = 0; j <allDept.size(); j++) {
			
		
		if (stu.getStudept().equals(allDept
				.get(j).getDeptname())&&allDept
				.get(j).getDeptid()%2!=0) {//如果学生系别和系别表里的id匹配，往下走
			

			if (stu.getStusex() == 0) {//男生
				//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
				List<DeptEntity> build= new RoomDao().getBoyBuild(allDept
						.get(j).getDeptname());
				List<RoomEntity> allRoom = new RoomDao()
						.getAllNullRoom(build.get(0).getBuildid().getBuildid());//查空房间
				service.addStudentMidle(stu.getStuid(), allRoom.get(0)
						.getRoomid(), allDept.get(j).getBuildid().getBuildid(),allDept.get(j).getDeptid());
				//修改宿舍表里的人数
				new RoomDao().updateRoomnum(allRoom.get(0)
						.getRoomid());
				//System.out.println("分配男");
			}}else if( stu.getStudept().equals(allDept
					.get(j).getDeptname())&&allDept
					.get(j).getDeptid()%2==0){{
			if (stu.getStusex() == 1) {//女生
				System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<");
				List<DeptEntity> build= new RoomDao().getGirlBuild(allDept
						.get(j).getDeptname());
				List<RoomEntity> allRoom = new RoomDao()
						.getAllNullRoom(build.get(0).getBuildid().getBuildid());
				service.addStudentMidle(stu.getStuid(), allRoom.get(0)
						.getRoomid(), allDept.get(j).getBuildid().getBuildid(),allDept.get(j).getDeptid());
				new RoomDao().updateRoomnum(allRoom.get(0)
						.getRoomid());
				//System.out.println("分配女");
			}}
		request.getRequestDispatcher("StudentServlet?method=getWeiRuZhuStudent").forward(request, response);
		}}}

	private void piliangruzhu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service.piLiangRuZhu();
		request.getRequestDispatcher(
				"StudentServlet?method=getWeiRuZhuStudent2").forward(request,
				response);
	}

	private void getAllRuzhu2(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		AdminEntity admin =(AdminEntity) session.getAttribute("login");
		//String id = request.getParameter("adminid");
		List<StudentMiddleEntity> list = new StudentMiddleService()
				.getAllByAdmin(new AdminService().getAdminById(admin.getAdminid()));
	
		request.setAttribute("list", list);
		request.getRequestDispatcher("jsp_stumanagement/getRuzhuStudent.jsp")
				.forward(request, response);
	}

	private void deleteStudentMiddle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		//String adminid = request.getParameter("adminid");
	//	System.out.println("csccscscscscscscsscscsscscsasscscscscscsc");
		service.deleteStudentMiddleEntity(new StudentMiddleDao()
				.getStudentMiddleEntityByStuid(Integer.parseInt(id)).getStuid().getStuid());
		request.getRequestDispatcher("StudentMidServlet?method=getAllRuzhu2")
				.forward(request, response);
		
	}

	private void getRoomBySid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String sid = request.getParameter("id");
		System.out.println("dvvsvsdvsvs");
		System.out.println(sid);
		RoomEntity room = service.getStudentEntityByStuId(sid);
		System.out.println(room.getRoomno());
		request.setAttribute("s", room);
		StudentMiddleEntity stu = service.getRoomBySid(sid);
		request.setAttribute("s", stu);
		request.getRequestDispatcher("jsp_student/getRoom.jsp").forward(
				request, response);
	}

}
