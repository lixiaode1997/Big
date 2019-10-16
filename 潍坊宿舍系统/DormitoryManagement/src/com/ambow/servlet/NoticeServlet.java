package com.ambow.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ambow.dao.RoomDao;
import com.ambow.dao.StudentDao;
import com.ambow.entity.NoticeEntity;
import com.ambow.entity.RoomEntity;
import com.ambow.entity.StudentEntity;
import com.ambow.entity.StudentMiddleEntity;
import com.ambow.service.NoticeService;
import com.ambow.service.StudentMiddleService;
import com.ambow.service.StudentService;

public class NoticeServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String method=request.getParameter("method");
		

		if(method.equals("add")){
			add(request, response);
		}else if(method.equals("getAll")){
			getAll(request, response);
		}else if(method.equals("adminGetAll")){
			adminGetAll(request, response);
		}else if(method.equals("updateFlag")){//学生修改状态为1  审核中
			updateFlag(request, response);
		}else if(method.equals("updateFlag2")){//管理员修改状态为2  
			updateFlag2(request, response);
		}else if(method.equals("del")){ 
			del(request, response);
		}else if(method.equals("getNoticeByRoomid")){ 
			getNoticeByRoomid(request, response);
		}else if(method.equals("getLoginRoomid")){
			getLoginRoomid(request,response);
		}
		
	}

	private void getLoginRoomid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		StudentMiddleEntity stu=new StudentMiddleService().getRoomBySid(id);
		request.setAttribute("room", stu);
		request.getRequestDispatcher("jsp_student/addNotices.jsp").forward(request, response);
	}


	private void del(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String id=request.getParameter("id");
		String roomid=request.getParameter("roomid");
		service.delNotice(id);
		request.setAttribute("rid", roomid);
		//response.sendRedirect("NoticeServlet?method=getAll");
		request.getRequestDispatcher("NoticeServlet?method=getNoticeByRoomid").forward(request, response);
	}


	private void updateFlag2(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id=request.getParameter("id");
		service.updateFlag(id, "2");
		response.sendRedirect("NoticeServlet?method=adminGetAll&flag=1");
	}


	private void adminGetAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag=request.getParameter("flag");
		List<NoticeEntity> list=service.getAllNoticeByFlag(flag);
		request.setAttribute("list11", list);
		request.getRequestDispatcher("jsp_stumanagement/getAllNotice.jsp").forward(request, response);;
	}


	private void updateFlag(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String roomid=request.getParameter("roomid");
		service.updateFlag(id, "1");
		request.setAttribute("rid", roomid);
		//response.sendRedirect("NoticeServlet?method=getNoticeByRoomid");
		request.getRequestDispatcher("NoticeServlet?method=getNoticeByRoomid").forward(request, response);
	}

	NoticeService service=new NoticeService();
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String content=request.getParameter("content");
		String flag=request.getParameter("flag");
		String room=request.getParameter("room");
		String roomid=request.getParameter("roomid");
		System.out.println("添加内容："+" "+content);
		System.out.println("添加flag："+" "+flag);
		System.out.println("添加房间："+" "+room);
		System.out.println("添加房间id："+" "+roomid);
		RoomEntity room1=new RoomDao().getRoomById(Integer.parseInt(room));
		NoticeEntity notice=new NoticeEntity(content,Integer.parseInt(flag),room1);
		service.insertNotice(notice);
		request.setAttribute("rid", roomid);
		//response.sendRedirect("NoticeServlet?method=getNoticeByRoomid");
		request.getRequestDispatcher("NoticeServlet?method=getNoticeByRoomid").forward(request, response);
		
	}
	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sid=request.getParameter("id");
		StudentMiddleEntity s=new StudentMiddleService().getRoomBySid(sid);
		List<NoticeEntity> list=service.getnoticeBYRoomid(s.getRoomid().getRoomid());
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("jsp_student/shownotice.jsp").forward(request, response);
	}
	
	private void getNoticeByRoomid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roomid=request.getParameter("roomid");
		List<NoticeEntity> list=service.getAllBYRoomid(roomid);
		request.setAttribute("list", list);
		request.getRequestDispatcher("jsp_student/shownotice.jsp").forward(request, response);
	}

}
