package com.ambow.servlet;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.ambow.entity.StudentEntity;

import com.ambow.service.StudentService;

public class ImportServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、创建磁盘文件项工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//2、创建文件上传的核心类
		ServletFileUpload upload = new ServletFileUpload(factory);

		//3、解析request---获得文件项集合
		List<FileItem> parseRequest=null;
		//4、遍历文件项集合
		for(FileItem item : parseRequest){
			//5、判断普通表单项/文件上传项
			boolean formField = item.isFormField();//是否是一个普通表单项
			if(formField){
				//普通表单项
				String fieldName = item.getFieldName();
				String fieldValue = item.getString();
				System.out.println(fieldName+":"+fieldValue);
			}else{
				//文件上传项
				//获得上传文件的名称
				String fileName = item.getName();
				//获得上传文件的内容
				InputStream in = item.getInputStream();
				//将in中的数据拷贝服务器上
				String path = this.getServletContext().getRealPath("upload");
				OutputStream out = new FileOutputStream(path+"/"+fileName);
				int len = 0;
				byte[] buffer = new byte[1024];
				while((len=in.read(buffer))>0){
					out.write(buffer, 0, len);
				}

				in.close();
				out.close();
				// XSSF和HSSF虽然在不同的包里，但却引用了同一接口Workbook，可以用下面判断
				Workbook wb = null;


				FileInputStream fi = new FileInputStream(path+"/"+fileName);
				if (fileName.toLowerCase().endsWith("xls")) {
					wb = new HSSFWorkbook(fi);
				} else if (fileName.toLowerCase().endsWith("xlsx")) {
					wb = new XSSFWorkbook(fi);
				}
				Sheet sheet = wb.getSheetAt(0);
				int rowNum = sheet.getLastRowNum() + 1;
				List<StudentEntity> studentList = new ArrayList<StudentEntity>();
				// i 从1开始表示第一行为标题 不包含在数据中
				for (int i = 1; i < rowNum; i++) {
					StudentEntity student=new StudentEntity();
					Row row = sheet.getRow(i);
					int cellNum = row.getLastCellNum();
					for (int j = 0; j < cellNum; j++) {
						Cell cell = row.getCell(j);
						String cellValue = null;
						switch (cell.getCellType()) { // 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
						//  
						case 0:
							cellValue = String.valueOf((int) cell
									.getNumericCellValue());
							break;
						case 1:
							cellValue = cell.getStringCellValue();
							break;
						case 2:
							cellValue = String.valueOf(cell.getDateCellValue());
							break;
						case 3:
							cellValue = "";
							break;
						case 4:
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;
						case 5:
							cellValue = String.valueOf(cell.getErrorCellValue());
							break;
						}
						switch (j) {// 通过列数来判断对应插如的字段  
						// 数据中不应该保护ID这样的主键记录                     
						
						case 0:
							if(!cellValue.equals("")&&cellValue!=null){
								student.setStuid(Integer.valueOf(cellValue));
								
							}

							break;
						case 1:
							student.setStuno(Integer.valueOf(cellValue));
							

							break;
						case 2:
							
							student.setStuname(cellValue);
							break;
						case 3:
							student.setStupwd(cellValue);

							break;
						case 4:
							
                            student.setStusex(Integer.valueOf(cellValue));
							break;
						case 5:
							
                            student.setStudept(cellValue);
							break;
						case 6:
							student.setStustartime(Date.valueOf(cellValue));
						

							break;
						case 7:
							student.setStuyear(Integer.valueOf(cellValue));
					     
						case 8:
							student.setStupay(Integer.valueOf(cellValue));
					} 
					}
					System.out.println("daoru"+student);
					studentList.add(student);
					
					System.out.println("building:"+student);
				}
				
				System.out.println(studentList);
               StudentService studentService=new StudentService();
               studentService.addStudent(studentList);
                response.sendRedirect("StudentServlet?method=getAll");
                
			
		}
		}

	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

