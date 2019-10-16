package com.ambow.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ambow.dao.RoomDao;
import com.ambow.dao.StudentDao;
import com.ambow.entity.AdminEntity;
import com.ambow.entity.BuildingEntity;
import com.ambow.entity.RoomEntity;
import com.ambow.entity.StudentEntity;
import com.ambow.service.AdminService;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class TuServlet2 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> maps=new HashMap<String, Object>();
		
	   List<Map<String,String>> maplist=new ArrayList<Map<String,String>>();
	   HttpSession session=request.getSession();
		AdminEntity admin =(AdminEntity) session.getAttribute("login");
	   BuildingEntity build = new AdminService().getAdminById(admin.getAdminid()).getBuildid();
	   List<RoomEntity> listnullroom=new RoomDao().getAllNullRoom1(build.getBuildid());
	   List<RoomEntity> listWeimanroom=new RoomDao().getAllWeiManRoomByBuildId(build.getBuildid());
	   List<RoomEntity> listYimanroom=new RoomDao().getAllYiManRoomByBuildId(build.getBuildid());

	  
	  
	   Map<String,String> map=new HashMap<String, String>();
	   Map<String,String> map2=new HashMap<String, String>();
	   Map<String,String> map3=new HashMap<String, String>();
	   map.put("name", "未满宿舍");
	   map.put("value", ""+listWeimanroom.size());
	   maplist.add(map);
	   map2.put("name", "空宿舍");
	   map2.put("value", ""+listnullroom.size());
	   maplist.add(map2);
	   map3.put("name", "已满宿舍");
	   map3.put("value", ""+listYimanroom.size());
	   maplist.add(map3);
	   maps.put("data", maplist);
	   Gson gson=new Gson();
		String str=gson.toJson(maps);
		response.getWriter().print(str);

	}

}
