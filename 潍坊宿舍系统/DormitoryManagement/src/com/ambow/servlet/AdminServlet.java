package com.ambow.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ambow.dao.AdminDao;
import com.ambow.dao.BuildingDao;
import com.ambow.entity.AdminEntity;
import com.ambow.entity.BuildingEntity;
import com.ambow.service.AdminService;
import com.ambow.service.BuildingService;



public class AdminServlet extends HttpServlet {
            AdminService adminService=new AdminService();
            BuildingDao dao=new BuildingDao();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf-8");
		String method=request.getParameter("method");
		if(method.equals("login")){
			login(request,response);
		}else if(method.equals("all")){
			getAllAdmin(request,response);
		}else if(method.equals("allDel")){
			getAllAdminDel(request,response);
		}else if(method.equals("allUpadate")){
			getAllAdminUpdate(request,response);
		}else if(method.equals("byid")){
			getById(request,response);
		}else if(method.equals("update")){
			updateAdmin(request,response);
		}else if(method.equals("add")){
			addAdmin(request,response);
		}else if(method.equals("delete")){
			deleteAdmin(request,response);
		}else if(method.equals("byid2")){
			getById2(request,response);
		}else if(method.equals("showAdmin")){
			showAdmin(request,response);
		}
		
	}
        private void showAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        	String adminid=request.getParameter("adminid");
        	AdminEntity admin = new AdminService().getAdminById(Integer.parseInt(adminid));
        	request.setAttribute("admin",admin);
    		request.getRequestDispatcher("jsp_admin/showAdmin.jsp").forward(request, response);

	}

		//得到所有（包含修改）
	private void getAllAdminUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AdminService adminService=new AdminService();
		List<AdminEntity> list=adminService.getAllAdmin();
		request.setAttribute("adminList",list);
		request.getRequestDispatcher("jsp_admin/showUpdate.jsp").forward(request, response);
	}

	
	//删除
	private void deleteAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		  String adminid=request.getParameter("adminid");
	      AdminService adminService=new AdminService();
	      adminService.deleteAdmin(adminid);
	      response.sendRedirect("AdminServlet?method=allDel");
		
	}
  //添加
	private void addAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String adminame=request.getParameter("adminname");
		String adminpwd=request.getParameter("adminpwd");
		String buildid=request.getParameter("buildname");
		List<AdminEntity> list=adminService.getAllAdmin();
		HttpSession session=request.getSession();
		for(AdminEntity o:list){
			if(adminame.equals(o.getAdminname())){
				
				request.setAttribute("pop", "已有该用户，请重新添加");
				request.getRequestDispatcher("BuildingServlet?method=getAll").forward(request, response);//跳转Servlet
				break;
			}else{
				BuildingEntity build=new BuildingDao().getBuildById(Integer.parseInt(buildid));
				AdminEntity admin=new AdminEntity(adminame,adminpwd,build);
				adminService.addAdmin(admin);
			    response.sendRedirect("AdminServlet?method=all");
			    break;
			}
			
		}
		
		
		
		
			
	}
	
	//修改
	private void updateAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adminid=request.getParameter("adminid");
		String adminname=request.getParameter("adminname");
		String adminpwd=request.getParameter("adminpwd");
		String buildname=request.getParameter("buildname");
		BuildingEntity build=dao.getBuildById(Integer.parseInt(buildname));
		AdminEntity admin=new AdminEntity(Integer.parseInt(adminid),adminname,adminpwd,build);
		adminService.updateAdmin(admin);
		response.sendRedirect("AdminServlet?method=all");
	/*	response.sendRedirect("");
		AdminEntity admin=new AdminEntity(Integer.parseInt(adminid),adminname,adminpwd,build);
		request.setAttribute("adminList", adminService.updateAdmin(admin));
		request.getRequestDispatcher("AdminServlet?method=all").forward(request, response);*/
		
	}

	private void getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  String adminid=request.getParameter("adminid");
		  AdminService adminService=new AdminService();
		  AdminEntity admin=adminService.getAdminById(Integer.parseInt(adminid));
		  request.setAttribute("adminid", admin);
		  request.getRequestDispatcher("jsp_admin/showDel.jsp").forward(request, response);
	}
	
	private void getById2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  String adminid=request.getParameter("adminid");
		  AdminService adminService=new AdminService();
		  AdminEntity admin=adminService.getAdminById(Integer.parseInt(adminid));
		  List<BuildingEntity> list=new BuildingDao().getAllBuild();
		  request.setAttribute("admi", admin);
		  request.setAttribute("bui", list);
		  request.getRequestDispatcher("jsp_admin/update.jsp").forward(request, response);
	}
	
	
	
    //显示所有
	private void getAllAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService adminService=new AdminService();
			List<AdminEntity> list=adminService.getAllAdmin();
			request.setAttribute("adminList",list);
			request.getRequestDispatcher("jsp_admin/show.jsp").forward(request, response);
		
		
		
		
		/*request.getRequestDispatcher("showAllBuild.jsp").forward(request, response);*/
		
		
	}
	
	 //显示所有(包含删除的jsp)
		private void getAllAdminDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			AdminService adminService=new AdminService();
				List<AdminEntity> list=adminService.getAllAdmin();
				request.setAttribute("adminList",list);
				request.getRequestDispatcher("jsp_admin/showDel.jsp").forward(request, response);
			
			
			
			
			/*request.getRequestDispatcher("showAllBuild.jsp").forward(request, response);*/
			
			
		}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String name = request.getParameter("loginName");
		String pwd = request.getParameter("loginPwd");
		/*String buildname=request.getParameter("buildname");
		BuildingEntity build=dao.getBuildById(Integer.parseInt(buildname));*/
		HttpSession session = request.getSession();
  
		AdminEntity admin = adminService.login(name, pwd);

		if(admin!=null){
			List<BuildingEntity> list=new BuildingDao().getAllBuild();
			request.setAttribute("buildList", list);
			session.setAttribute("admin", admin);
			request.getRequestDispatcher("*********").forward(request, response);
		}else{
			response.sendRedirect("jsp_admin/login.jsp");
		
	}}

}
