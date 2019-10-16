package com.ambow.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.DateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ambow.dao.AdminDao;
import com.ambow.dao.RecordDao;
import com.ambow.dao.RoomDao;
import com.ambow.dao.StudentDao;
import com.ambow.entity.AdminEntity;
import com.ambow.entity.RecordEntity;
import com.ambow.entity.RoomEntity;
import com.ambow.entity.StudentEntity;
import com.ambow.util.ExcelUtil;

public class ExportServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String method=request.getParameter("method");
	         if(method.equals("admin")){
	        	exportAdmin(request,response,new AdminDao().getAllAdmin());  
	      
	         }else if(method.equals("room")){
	        	 exportRoom(request,response,new RoomDao().getAllRoom());
	        	 
	         }else if(method.equals("student")){
	        	 exportStudent(request,response,new StudentDao().getAllStudent());       	 
	         }else if(method.equals("record")){
	        	 exportRecord(request,response,new RecordDao().getAllRecord());
	        	 
	         }
	}

  //导出记录表
	private void exportRecord(HttpServletRequest request, HttpServletResponse response, List<RecordEntity> allRecord) {
		// TODO Auto-generated method stub
		List<RecordEntity> list = allRecord;	
		//excel标题
	     String[] title = {"recordid","leavetime","backtime","stuid"};

	     //excel文件名
	     String fileName = "学生计表" + System.currentTimeMillis() + ".xls";

	     //sheet名
	     String sheetName = "学生统计";

	     String[][] content=new String[list.size()][title.length];
	     for (int i = 0; i < list.size(); i++) {
	         content[i] = new String[title.length];
	         RecordEntity obj = list.get(i);
	         content[i][0] = String.valueOf(obj.getRecordid());  
	         content[i][1] = String.valueOf(obj.getBacktime());
	         content[i][2] = String.valueOf(obj.getLeavetime());  
	         content[i][3] = String.valueOf(obj.getStuid().getStuid());  
	        
	     }
	   //------------------------以下是向excel中导入查询的数据----------------------
	 	//创建HSSFWorkbook
	 	     HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);

	 	//响应到客户端
	 	     try {
	 	         this.setResponseHeader(response, fileName);
	 	         OutputStream os = response.getOutputStream();
	 	         wb.write(os);
	 	         os.flush();
	 	         os.close();
	 	     } catch (Exception e) {
	 	         e.printStackTrace();
	 	     }
		
	}

//导出学生表
	private void exportStudent(HttpServletRequest request, HttpServletResponse response,
			List<StudentEntity> allStudent) {
		List<StudentEntity> list = allStudent;

	     //excel标题
	     String[] title = {"stuid", "stuno", "stuname","stupwd","stusex","studept","stustarttime","stuyear","stupay"};

	     //excel文件名
	     String fileName = "学生统计表" + System.currentTimeMillis() + ".xls";

	     //sheet名
	     String sheetName = "学生统计";

	     String[][] content=new String[list.size()][title.length];
	     for (int i = 0; i < list.size(); i++) {
	         content[i] = new String[title.length];
	         StudentEntity obj = list.get(i);
	         content[i][0] = String.valueOf(obj.getStuid());    
	         content[i][1] = String.valueOf(obj.getStuno());  
	         content[i][2] = obj.getStuname();
	         content[i][3] = obj.getStupwd();
	         content[i][4] = String.valueOf(obj.getStusex()); 

	         content[i][5] = String.valueOf(obj.getStudept()); 


	         content[i][6] = String.valueOf(obj.getStustartime()); 
	         content[i][7] = String.valueOf(obj.getStuyear());
	         content[i][8] = String.valueOf(obj.getStupay());
	       
	        
	     }
	     
	   //------------------------以下是向excel中导入查询的数据----------------------
	 	//创建HSSFWorkbook
	 	     HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);

	 	//响应到客户端
	 	     try {
	 	         this.setResponseHeader(response, fileName);
	 	         OutputStream os = response.getOutputStream();
	 	         wb.write(os);
	 	         os.flush();
	 	         os.close();
	 	     } catch (Exception e) {
	 	         e.printStackTrace();
	 	     }
		
		
		
		
	}


	private void exportRoom(HttpServletRequest request, HttpServletResponse response, List<RoomEntity> allRoom) {
		 List<RoomEntity> list = allRoom;

	     //excel标题
	     String[] title = {"roomid", "roomname", "roombed","roomnum","buildid"};

	     //excel文件名
	     String fileName = "宿舍统计表" + System.currentTimeMillis() + ".xls";

	     //sheet名
	     String sheetName = "宿舍统计";

	     String[][] content=new String[list.size()][title.length];
	     for (int i = 0; i < list.size(); i++) {
	         content[i] = new String[title.length];
	         RoomEntity obj = list.get(i);
	         content[i][0] = String.valueOf(obj.getRoomid());
	         content[i][1] = String.valueOf(obj.getRoomno()); 
	         content[i][2] = String.valueOf(obj.getRoombed());
	         content[i][3] = String.valueOf(obj.getRoomnum());
	         content[i][4] = String.valueOf(obj.getBuildid().getBuildid());
	     }
	   //------------------------以下是向excel中导入查询的数据----------------------
	 	//创建HSSFWorkbook
	 	     HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);

	 	//响应到客户端
	 	     try {
	 	         this.setResponseHeader(response, fileName);
	 	         OutputStream os = response.getOutputStream();
	 	         wb.write(os);
	 	         os.flush();
	 	         os.close();
	 	     } catch (Exception e) {
	 	         e.printStackTrace();
	 	     }
	}


	private void exportAdmin(HttpServletRequest request, HttpServletResponse response, List<AdminEntity> allAdmin) {
		// TODO Auto-generated method stub
		
		
		
		 List<AdminEntity> list = allAdmin;

	     //excel标题
	     String[] title = {"adminid", "adminname", "adminpwd","buildid"};

	     //excel文件名
	     String fileName = "宿管统计表" + System.currentTimeMillis() + ".xls";

	     //sheet名
	     String sheetName = "宿管统计";

	     String[][] content=new String[list.size()][title.length];
	     for (int i = 0; i < list.size(); i++) {
	         content[i] = new String[title.length];
	         AdminEntity obj = list.get(i);
	         content[i][0] = String.valueOf(obj.getAdminid());
	         content[i][1] = obj.getAdminname();
	         content[i][2] = obj.getAdminpwd();
	         content[i][3] = String.valueOf(obj.getBuildid().getBuildid());
	        
	     }

	     
	     
	     
	//------------------------以下是向excel中导入查询的数据----------------------
	//创建HSSFWorkbook
	     HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);

	//响应到客户端
	     try {
	         this.setResponseHeader(response, fileName);
	         OutputStream os = response.getOutputStream();
	         wb.write(os);
	         os.flush();
	         os.close();
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	 }

	 //发送响应流方法
	 public void setResponseHeader(HttpServletResponse response, String fileName) {
	     try {
	         try {
	             fileName = new String(fileName.getBytes(), "utf-8");
	         } catch (UnsupportedEncodingException e) {
	             // TODO Auto-generated catch block
	             e.printStackTrace();
	         }
	         response.setContentType("application/octet-stream;charset=utf-8");
	         response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
	         response.addHeader("Pargam", "no-cache");
	         response.addHeader("Cache-Control", "no-cache");
	     } catch (Exception ex) {
	         ex.printStackTrace();
	     }
	 }
		

}
