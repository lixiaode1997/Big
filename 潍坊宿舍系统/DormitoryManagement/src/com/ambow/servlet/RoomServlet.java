package com.ambow.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ambow.dao.BuildingDao;
import com.ambow.dao.RoomDao;
import com.ambow.dao.StudentDao;
import com.ambow.dao.StudentMiddleDao;
import com.ambow.entity.BuildingEntity;
import com.ambow.entity.RoomEntity;
import com.ambow.entity.StudentEntity;
import com.ambow.entity.StudentMiddleEntity;
import com.ambow.service.BuildingService;
import com.ambow.service.RoomService;

public class RoomServlet extends HttpServlet {
	RoomService roomservice=new RoomService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		String method = request.getParameter("method");
		if(method.equals("del")){
			del(request, response);
		}else if(method.equals("getAllRoom")){
			getAllRoom(request, response);
		}else if(method.equals("getByID")){
			getByID(request, response);
		}else if(method.equals("updateRoom")){ 
			updateRoom(request, response);
		}else if(method.equals("addRoom")){
			addRoom(request, response);
		}else if(method.equals("getAllAdd")){
			getAllAdd(request, response);
		}else if(method.equals("getAllByBname")){
			getAllByBname(request, response);
		}else if(method.equals("getRoomByRoomNum")){
			getRoomByRoomNum(request, response);
		}else if(method.equals("getAllbybuild")){
			getAllbybuild(request, response);
		}
		
		String menthod=request.getParameter("method");
		
		if(menthod.equals("showRoomToStu")){
		}else if(menthod.equals("showRoomToStu")){
			showRoomToStu(request,response);
		}else if(menthod.equals("getById")){
			getById(request,response);
		}else if(menthod.equals("getById")){
			getById(request,response);
		}else if(menthod.equals("selectDouble")){
			selectDouble(request,response);
		}else if(menthod.equals("change")){
			changeRoomByStuno(request,response);
		}else if(menthod.equals("selectsingle")){
			selectsingle(request,response);
		}else if(menthod.equals("change1")){
			changeRoomByStuno1(request,response);
		}else if(menthod.equals("docheck")){
			doCheck(request,response);
		}else if(menthod.equals("check2")){
			changeroomByRoom(request,response);
		}else if(menthod.equals("docheckstu")){
			doCheckstu(request,response);
		}else if(menthod.equals("change4")){
			welcome(request,response);
		}
	}
	
	



	private void getAllbybuild(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
	String buildname = request.getParameter("buildname");
	List<RoomEntity> list=new RoomService().getRoomBybuildName(buildname);
	System.out.println("进入方法");
	request.setAttribute("buildList", list);	
	request.getRequestDispatcher("jsp_roommanage/getAllRoomByBuild.jsp.jsp").forward(request, response);

	}


	private void getAllAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*HttpSession session=request.getSession();*/
		BuildingService buildService=new BuildingService();
		List<BuildingEntity> list=buildService.getAllBuild();
		System.out.println("进入方法");
		request.setAttribute("buildList", list);
		request.getRequestDispatcher("jsp_roommanage/roomAdd.jsp").forward(request, response);
		
	}


private void addRoom(HttpServletRequest request,HttpServletResponse response) throws IOException  {
		
		String roomno = request.getParameter("roomno");
		String buildid=request.getParameter("buildname");
		BuildingEntity build=new BuildingDao().getBuildById(Integer.parseInt(buildid));
		RoomEntity room=new RoomEntity(Integer.parseInt(roomno),8,0,build);
		roomService.addRoom(room);
		response.sendRedirect("RoomServlet?method=getAllRoom");
		}


		//修改
		private void updateRoom(HttpServletRequest request,
				HttpServletResponse response) throws IOException {
			String roomid = request.getParameter("roomid");
			String roomno = request.getParameter("roomno");
			String buildid = request.getParameter("buildid");
			BuildingService buildingService = new BuildingService();
			BuildingEntity be=buildingService.getAllBuildById(buildid);
			RoomEntity roomentity = new RoomEntity(Integer.parseInt(roomid),Integer.parseInt(roomno),be);
			roomService.updateRoom(roomentity);
			response.sendRedirect("RoomServlet?method=getAllRoom");
		}

 

	private void welcome(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("jsp_superadmin/welcome.jsp").forward(request, response);
	}


	private void doCheckstu(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String shenqingstuno = request.getParameter("shenqingstuno");
		System.out.println("shenqingstuno..."+shenqingstuno);
		StudentEntity stu1 = new StudentDao().getStudentByStuno(Integer.parseInt(shenqingstuno));
		if(stu1 == null){
			out.print("0");
		}else{
			out.print("1");
		}
		
	}


	private void getByID(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		RoomEntity room = roomService.getAllRoomById(id);
		 List<BuildingEntity> list=new BuildingDao().getAllBuild();
		request.setAttribute("room", room);
		request.setAttribute("buildList", list);
		//request.setAttribute("room",room);
		request.getRequestDispatcher("jsp_roommanage/roomUpdate.jsp").forward(request, response);
	}

 

	private void getAllRoom(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<RoomEntity> list = roomService.getAllRoom();
		List<BuildingEntity> list1=new BuildingDao().getAllBuild();
		request.setAttribute("all", list);
		request.setAttribute("all2", list1);
		request.getRequestDispatcher("jsp_roommanage/roomList.jsp").forward(request, response);
	}

	
	
	
	private void changeroomByRoom(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String oldroomno = request.getParameter("oldroomno");
		String oldbuildno = request.getParameter("oldbuildno");
		
		String newroomno = request.getParameter("newroomno");
		String newbuildno = request.getParameter("newbuildno");
		
		//根据楼号和宿舍号查到宿舍实体，取到楼住的人群性别
		RoomEntity oldroom = new RoomDao().getRoomBybuildid(Integer.parseInt(oldbuildno), Integer.parseInt(oldroomno));
		int oldbuildsex = oldroom.getBuildid().getBuildsex();//原住楼性别
		System.out.println("旧楼的id："+oldroom.getBuildid());
		RoomEntity newroom = new RoomDao().getRoomBybuildid(Integer.parseInt(newbuildno), Integer.parseInt(newroomno));
		int newbuildsex = newroom.getBuildid().getBuildsex();//新楼性别
		System.out.println("新楼的id："+newroom.getBuildid());
		
		//取到宿舍里住的学生集合
		List<StudentEntity> oldstulist = new StudentMiddleDao().getAllStudentEntityByRoomId(oldroom.getRoomid());
		List<StudentEntity> newstulist = new StudentMiddleDao().getAllStudentEntityByRoomId(newroom.getRoomid());
		
		
		//符合换宿舍条件，遍历到当前宿舍所有学生，把宿舍号修改为新的宿舍号
		if(oldbuildsex == newbuildsex){
			for(int i=0;i<oldstulist.size();i++){
				//根据宿舍李的学生的id获取学生的中间实体
				StudentMiddleEntity oldmid = new StudentMiddleDao().getStudentMiddleEntityByStuid(oldstulist.get(i).getStuid());
				
				//新宿舍人数
				System.out.println("新宿舍的人数;--------------------------"+newroom.getRoomnum());
				System.out.println("旧宿舍的人数;--------------------------"+oldroom.getRoomnum());
				
				//变更实体的宿舍
				oldmid.setRoomid(newroom);
				oldmid.setBuildid(newroom.getBuildid());
				
				//修改学生的宿舍
				StudentMiddleDao midDao = new StudentMiddleDao();
				midDao.updateStudentMiddleEntity(oldmid);
			}
		}else{
			//不可以换
			System.out.println("两个楼的入住性别要求不同，不能入住");
		}
		if(oldbuildsex == newbuildsex){
			for(int i=0;i<newstulist.size();i++){
				//根据宿舍李的学生的id获取学生的中间实体
				StudentMiddleEntity newmid = new StudentMiddleDao().getStudentMiddleEntityByStuid(newstulist.get(i).getStuid());
				
				//变更实体的宿舍
				newmid.setRoomid(oldroom);
				newmid.setBuildid(oldroom.getBuildid());
				
				//修改学生的宿舍
				StudentMiddleDao midDao = new StudentMiddleDao();
				midDao.updateStudentMiddleEntity(newmid);
			}
		}else{
			//不可以换
			System.out.println("两个楼的入住性别要求不同，不能入住");
		}
		
		request.setAttribute("oldroom", oldroom);
		request.setAttribute("newroom", newroom);
		request.setAttribute("oldstulist", oldstulist);
		request.setAttribute("newstulist", newstulist);
		request.getRequestDispatcher("jsp_superadmin/showChange3.jsp").forward(request, response);
	}


	//检查宿舍是否存在
	private void doCheck(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String shenqingroomno = request.getParameter("shenqingroomno");
		RoomEntity room = new RoomDao().getRoomByno(Integer.parseInt(shenqingroomno));
		if(room == null){
			out.print("0");
			return;
		}else{
			out.print("1");
			return;
		}
	}


	private void selectsingle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String shenqingpname = request.getParameter("shenqingpname");
		String shenqingstuno = request.getParameter("shenqingstuno");
		int shenqingroomno = Integer.parseInt(request.getParameter("shenqingroomno"));
		
		//根据学号查到学生对象
		StudentEntity stu = new StudentDao().getStudentByStuno(Integer.parseInt(shenqingstuno));
		//根据宿舍号查到宿舍对象
		RoomEntity room = new RoomDao().getRoomByno(shenqingroomno);
		
		request.setAttribute("stu", stu);
		request.setAttribute("room", room);
		request.setAttribute("shenqingroomno", shenqingroomno);
		request.getRequestDispatcher("jsp_superadmin/showChange1.jsp").forward(request, response);
		
	}


	private void changeRoomByStuno(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String shenqingstuno = request.getParameter("shenqingstuno");
		String tiaohuanstuno = request.getParameter("tiaohuanstuno");

		//根据学生号查到学生实体
		StudentEntity shenqingstu = new StudentDao().getStudentByStuno(Integer.parseInt(shenqingstuno));
		StudentEntity tiaohuanstu = new StudentDao().getStudentByStuno(Integer.parseInt(tiaohuanstuno));
		
		//根据学生id查中间实体
		StudentMiddleEntity shenqingmid = new StudentMiddleDao().getStudentMiddleEntityByStuid(shenqingstu.getStuid());
		StudentMiddleEntity tiaohuanmid = new StudentMiddleDao().getStudentMiddleEntityByStuid(tiaohuanstu.getStuid());
		
		//创建一个临时宿舍对象
		RoomEntity itemroom = shenqingmid.getRoomid();
		
		//变更实体的宿舍
		shenqingmid.setRoomid(tiaohuanmid.getRoomid());
		tiaohuanmid.setRoomid(itemroom);
				
		//修改学生的宿舍
		StudentMiddleDao midDao = new StudentMiddleDao();
		midDao.updateStudentMiddleEntity(shenqingmid);
		midDao.updateStudentMiddleEntity(tiaohuanmid);	
		request.getRequestDispatcher("jsp_superadmin/welcome.jsp").forward(request, response);
	}
	
	private void changeRoomByStuno1(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String shenqingstuno = request.getParameter("shenqingstuno");
		String shenqingroomno = request.getParameter("shenqingroomno");
		
		System.out.println(shenqingstuno);//学生号
		System.out.println(shenqingroomno);//申请调至哪个宿舍
		
		//根据学生号查到学生实体
		StudentEntity shenqingstu = new StudentDao().getStudentByStuno(Integer.parseInt(shenqingstuno));
		
		//根据房间号 查到房间实体
		RoomEntity room = new RoomDao().getRoomByno(Integer.parseInt(shenqingroomno));
		
		//根据学生号 查到中间表实体
		StudentMiddleEntity mid = new StudentMiddleDao().getStudentMiddleEntityByStuid(shenqingstu.getStuid());
		System.out.println(mid.getSmid()+""+mid.getStuid().getStuname()+""+mid.getDeptid().getDeptname());

		int oldroomid = mid.getRoomid().getRoomid();
		
		
		//宿舍总人数+1  
		int num = room.getRoomnum();
		room.setRoomnum(num+1);
		
		//变更实体的宿舍
		mid.setRoomid(room);
		
		
		
		//修改学生的宿舍
		StudentMiddleDao midDao = new StudentMiddleDao();
		midDao.updateStudentMiddleEntity(mid);
		//改变宿舍的总人数
		RoomDao roomDao = new RoomDao();
		roomDao.updateRoomnum(room.getRoomid());
	
		RoomDao roomDao1 = new RoomDao();
		roomDao1.updateRoomnum(oldroomid);
		
		request.getRequestDispatcher("jsp_superadmin/welcome.jsp").forward(request, response);
	}

	RoomService roomService = new RoomService();
	private void del(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String id=request.getParameter("id");
		roomService.delRoom(id);
		
		response.sendRedirect("RoomServlet?method=getAllRoom");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

	/*	String method = request.getParameter("method");
		if(method.equals("selectDouble")){
			selectDouble(request,response);
		}else if(method.equals("del")){
			delRecordById(request,response);
		}else if(method.equals("add")){
			addRecord(request,response);
		}else if(method.equals("time")){
			getTime(request,response);
		}
	
		String menthod=request.getParameter("method");
		
		if(menthod.equals("showRoomToStu")){
			showRoomToStu(request,response);
		}else if(menthod.equals("getById")){
			getById(request,response);
		}*/
		
	}

	private void getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		RoomEntity room=roomservice.getAllRoomById(id);
		request.setAttribute("r", room);
		request.getRequestDispatcher("jsp_student/getRoom.jsp").forward(request, response);
	}

	private void selectDouble(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String shenqingstuno = request.getParameter("shenqingstuno");
		String tiaohuanstuno = request.getParameter("tiaohuanstuno");
		
		StudentEntity shenqingstu = new StudentDao().getStudentByStuno(Integer.parseInt(shenqingstuno));
		StudentEntity tiaohuanstu = new StudentDao().getStudentByStuno(Integer.parseInt(tiaohuanstuno));
		
		//申请换宿的学生中间实体
		StudentMiddleEntity shenqingmid = new StudentMiddleDao().getStudentMiddleEntityByStuid(shenqingstu.getStuid());
		//调换宿舍的学生中间实体
		StudentMiddleEntity tiaohuanmid = new StudentMiddleDao().getStudentMiddleEntityByStuid(tiaohuanstu.getStuid());
		
		
		request.setAttribute("shenqingmid", shenqingmid);
		request.setAttribute("tiaohuanmid", tiaohuanmid);
		request.getRequestDispatcher("/jsp_superadmin/showChange2.jsp").forward(request, response);
	}
	
	private void showRoomToStu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<RoomEntity> list=roomservice.getAllRoom();
		request.setAttribute("list", list);
		request.getRequestDispatcher("jsp_student/addNotice.jsp").forward(request, response);
	}
	
	
	//按照楼号查询
		private void getAllByBname(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException  {
			System.out.println("执行方法");
			String buildName = request.getParameter("build");
			
			if(buildName.equals("")){
				
				request.setAttribute("name", "搜索不能为空");
				request.getRequestDispatcher("RoomServlet?method=getAllRoom").forward(request, response);
			}else{
				//List<RoomEntity> list=new RoomDao().getRoomBybuildName(buildName);
				List<RoomEntity> list = roomservice.getRoomBybuildName(buildName);
				request.setAttribute("rList", list);
				System.out.println(list);
				try {
					request.getRequestDispatcher("jsp_roommanage/getAllRoomByBuild.jsp").forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			
			
		}	
		 	
		
		//按照房间人数查询
		private void getRoomByRoomNum(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			String roomnum = request.getParameter("rNum");
			if(roomnum.equals("")){
				request.setAttribute("name1", "搜索不能为空");
				request.getRequestDispatcher("RoomServlet?method=getAllRoom").forward(request, response);
			}else{
			List<RoomEntity> list = roomservice.getRoomByRoomNum(roomnum);
			request.setAttribute("rList", list);
			request.getRequestDispatcher("jsp_roommanage/showByNum.jsp").forward(request, response);		}
			
		}
}

