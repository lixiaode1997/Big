package com.ambow.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.runner.Request;

import com.ambow.dao.StudentDao;
import com.ambow.entity.FenYe;
import com.ambow.entity.RecordEntity;
import com.ambow.entity.StudentEntity;
import com.ambow.service.RecordService;

public class RecordServlet extends HttpServlet {
	
	RecordService service = new RecordService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String method = request.getParameter("method");
		if(method.equals("all")){
			getAllRecord(request,response);
		}else if(method.equals("del")){
			delRecordById(request,response);
		}else if(method.equals("add")){
			addRecord(request,response);
		}else if(method.equals("time")){
			getTime(request,response);
		}else if(method.equals("importExcel")){//导入
		      importExcel(request, response);
	    }
	}


	private void getTime(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String str = service.getTime();
		request.setAttribute("time", str);
		request.getRequestDispatcher("/jsp_superadmin/addRecord.jsp").forward(request, response);
	}


	private void addRecord(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String stuno = request.getParameter("stuno");
		StudentEntity stu = new StudentDao().getStudentByStuno(Integer.parseInt(stuno));
		if(stu == null){
			List<RecordEntity> list = service.getAllRecord();
			request.setAttribute("recordlist", list);
			request.getRequestDispatcher("/jsp_superadmin/showRecord.jsp").forward(request, response);
		}else{
			String leavetime = request.getParameter("leavetime");
			String backtime = request.getParameter("backtime");
			RecordEntity record = new RecordEntity();
			record.setLeavetime(Date.valueOf(leavetime));
			record.setBacktime(Date.valueOf(backtime));
			record.setStuid(stu);
			service.addRecord(record);
			List<RecordEntity> list = service.getAllRecord();
			request.setAttribute("recordlist", list);
			request.getRequestDispatcher("/jsp_superadmin/showRecord.jsp").forward(request, response);
		}
		
	}


	public void delRecordById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		service.delRecordById(request.getParameter("recordid"));
		List<RecordEntity> list = service.getAllRecord();
		request.setAttribute("recordlist", list);
		request.getRequestDispatcher("/jsp_superadmin/showRecord.jsp").forward(request, response);
	}
	
	public void getAllRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String pageStr = request.getParameter("page");
		int page = 0;
		if(pageStr == null){
			page = 1;
		}else{
			page = Integer.parseInt(pageStr);
		}
		FenYe fenye = new FenYe();
		
		fenye.setCurrentPage(page);
		fenye.setRecordCount(service.getAllRecord().size());
		
		List<RecordEntity> list = service.getAllRecordByPage(fenye);
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getRecordid()+" "+list.get(i).getLeavetime()+" "+list.get(i).getBacktime()+" "+list.get(i).getStuid());
		}
		
		int allpage = fenye.getPageCount();
		request.setAttribute("page", page);
		request.setAttribute("allpage", allpage);
		request.setAttribute("recordlist",list);
		request.getRequestDispatcher("/jsp_superadmin/showRecord.jsp").forward(request, response);
		
	}
	//导入
	   private void importExcel(HttpServletRequest request, HttpServletResponse response) 
	     throws ServletException,IOException {
	    String filename = request.getParameter("file_input");
	    List<RecordEntity> list = readExcel(filename);
	    for(RecordEntity items: list){
	    	RecordEntity b=new RecordEntity();
	     b.setRecordid(items.getRecordid());
	     b.setLeavetime(items.getLeavetime());
	     b.setBacktime(items.getBacktime());
	     b.setStuid(items.getStuid());
	     service.excelBuiding(b);
	    }
	    request.getRequestDispatcher("RecordServlet?method=all").forward(request, response);
	   }
	   public  List<RecordEntity> readExcel(String filename) {

	    List<RecordEntity> list = new ArrayList<RecordEntity>();
	    HSSFWorkbook workbook = null;
	    try {
	     // 读取Excel文件
	     //String sep = System.getProperty("file.separator"); 
	     InputStream inputStream = new FileInputStream("D:\\"+filename);
	     workbook = new HSSFWorkbook(inputStream);
	     inputStream.close();

	    } catch (Exception e) {

	     e.printStackTrace();

	    }
	 // 循环工作表

	    for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {

	     HSSFSheet hssfSheet = workbook.getSheetAt(numSheet);

	     if (hssfSheet == null) {

	      continue;

	     }

	     // 循环行
	     for (int rowNum = 0; rowNum < hssfSheet.getLastRowNum()-1; rowNum++) {

	      HSSFRow hssfRow = hssfSheet.getRow(rowNum);

	      System.out.println("总行数："+hssfSheet.getLastRowNum());
	      if (hssfRow == null) {
	       continue;
	      }
	   // 将单元格中的内容存入集合

	      RecordEntity record = new RecordEntity();
	      
	      HSSFCell cell = hssfRow.getCell(0);
	      System.out.println(cell);
	      if (cell == null) {
	       continue;
	      }
	      record.setRecordid(Integer.parseInt(cell.toString()));
	      System.out.println("Recordid="+cell);
	      
	      cell = hssfRow.getCell(1);
	      if (cell == null) {
	       continue;
	      }
	      record.setLeavetime(Date.valueOf(cell.toString()));
	      System.out.println("Leavetime="+cell);
	      
	      cell = hssfRow.getCell(2);
	      if (cell == null) {
	       continue;
	      }
	      record.setBacktime(Date.valueOf(cell.toString()));
	      System.out.println("Backtime="+cell);
	      
	      
	      cell = hssfRow.getCell(3);
	      if (cell == null) {
	       continue;
	      }
	      int stuid = Integer.parseInt(cell.toString());
	      //返回stu
	      StudentEntity stu = new StudentEntity();
	      stu.setStuid(stuid);
	      record.setStuid(stu);
	      System.out.println("stuid="+cell);
	      list.add(record);
	     
	     }
	    }
	    return list;
	   }
}

