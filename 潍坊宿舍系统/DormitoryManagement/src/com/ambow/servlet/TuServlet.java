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

import com.ambow.entity.BuildingEntity;
import com.ambow.entity.StudentMiddleEntity;
import com.ambow.service.BuildingService;
import com.ambow.service.StudentMiddleService;
import com.google.gson.Gson;

/**
 * Servlet implementation class TuServlet
 */
public class TuServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> maps=new HashMap<String, Object>();
		
	   List<Map<String,String>> maplist=new ArrayList<Map<String,String>>();
	   
	   List<BuildingEntity> listlou=new BuildingService().getAllBuild();
	   List<StudentMiddleEntity> listMiddle=new StudentMiddleService().getAll();
	   List<Object> list=new ArrayList<Object>();
	   
	   for(BuildingEntity ll:listlou){
		   int num=0;
		   for(StudentMiddleEntity mm:listMiddle){
			   if(mm.getBuildid().getBuildname().equals(ll.getBuildname())){
				   num++;
			   }
			   
		   }
		   //list.add(num);
		   Map<String,String> map=new HashMap<String, String>();
		   map.put("name", ""+ll.getBuildname());
		   map.put("value", ""+num);
		   maplist.add(map);
		   
		   
	   }
	   
	   	maps.put("data", maplist);
		Gson gson=new Gson();
		String str=gson.toJson(maps);
		response.getWriter().print(str);

	}

}
