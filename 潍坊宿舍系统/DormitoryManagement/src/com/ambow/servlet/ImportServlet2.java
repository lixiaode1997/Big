package com.ambow.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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

import com.ambow.dao.BuildingDao;
import com.ambow.entity.AdminEntity;
import com.ambow.entity.BuildingEntity;
import com.ambow.service.AdminService;

public class ImportServlet2<A> extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			AdminService adminService = new AdminService();
	 		String filename = "F:/admin.xls";
//			System.out.println("路径-----"+filename);	 
		    List<AdminEntity> list = readExcel(filename);
			for(AdminEntity items: list){
				
			    /*
			    for(RecordEntity items: list){
			    	RecordEntity b=new RecordEntity();
			     b.setRecordid(items.getRecordid());
			     b.setLeavetime(items.getLeavetime());
			     b.setBacktime(items.getBacktime());
			     b.setStuid(items.getStuid());
			     service.excelBuiding(b);*/
				AdminEntity admin=new AdminEntity();
				admin.setAdminid(items.getAdminid());
				admin.setAdminname(items.getAdminname());
				admin.setAdminpwd(items.getAdminpwd());
				admin.setBuildid(items.getBuildid());
				adminService.addAdmin(admin);
				 
			}
}
//导入学生信息-->导包poi-3.8
	private List<AdminEntity> readExcel(String filename) {
		List<AdminEntity> list = new ArrayList<AdminEntity>();
		HSSFWorkbook workbook = null;
		try {
			// 读取Excel文件
			//String sep = System.getProperty("file.separator"); 
			InputStream inputStream = new FileInputStream(filename);
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
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);

				if (hssfRow == null) {
					continue;
				}
				// 将单元格中的内容存入集合
				AdminEntity admin = new AdminEntity();
				
				HSSFCell cell = hssfRow.getCell(0);
				if (cell == null) {
					continue;
				}
				admin.setAdminid(Integer.parseInt(cell.getStringCellValue()));
				
				
				
				cell = hssfRow.getCell(1);
				if (cell == null) {
					continue;
				}
				
				admin.setAdminname(cell.getStringCellValue());
				
				cell = hssfRow.getCell(2);
				if (cell == null) {
					continue;
				}
				
				admin.setAdminpwd(cell.getStringCellValue());
				
				cell = hssfRow.getCell(3);
				if (cell == null) {
					continue;
				}
				
				int buildid=Integer.parseInt(cell.getStringCellValue());
				BuildingEntity build=new BuildingEntity();
				build.setBuildid(buildid);
				admin.setBuildid(build);
				System.out.println("buildid="+cell);
				 list.add(admin);
				
				
				 /* int stuid = Integer.parseInt(cell.toString());
			      //返回stu
			      StudentEntity stu = new StudentEntity();
			      stu.setStuid(stuid);
			      record.setStuid(stu);
			      System.out.println("stuid="+cell);
			      list.add(record);*/
			
	
		}
		
}
		return list;}}
