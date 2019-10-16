package com.ambow.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.alibaba.fastjson.JSONObject;
import com.ambow.entity.LianDongEntity;
import com.ambow.service.LianDongService;


public class LianDongServlet extends HttpServlet {

	private LianDongService areaService = new LianDongService();
	private static final long serialVersionUID = 1L;

	public LianDongServlet() {
		super();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String flag = request.getParameter("flag");
		String id = request.getParameter("id");
		if (flag == null && id == null) {
			province(request, response);
		} else {
			if (flag.equals("city")) {
				city(request, response, Integer.valueOf(id));
			} else {
				if(flag.equals("area")){
					area(request, response, Integer.valueOf(id));
				}
			}
		}
	}

	public void city(HttpServletRequest request, HttpServletResponse response, int id) {

		//List<Area> list = areaService.getAreaByParentno("code",0, number);
		List<LianDongEntity> list = areaService.getAreaByParentno(id);
		response.setContentType("application/json;charset=utf-8");
		try {
			response.getWriter().write(JSONObject.toJSONString(list));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void area(HttpServletRequest request, HttpServletResponse response, int id) {

		List<LianDongEntity> list = areaService.getAreaByParentno(id);
		response.setContentType("application/json;charset=utf-8");
		try {
			response.getWriter().write(JSONObject.toJSONString(list));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void province(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<LianDongEntity> list = areaService.getAreaByParentno(0);
		response.setContentType("application/json;charset=utf-8");
		try {
			System.out.println(JSONObject.toJSONString(list));
			response.getWriter().write(JSONObject.toJSONString(list));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
