package com.ambow.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ambow.entity.FenYe;
import com.ambow.entity.RecordEntity;
import com.ambow.entity.StudentEntity;
import com.ambow.util.Tools;

public class RecordDao extends Tools {

	
	public String time(){
		Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(d);
		return str;
	}

	// excel导入
	   public int excelBuiding(RecordEntity recordEntity) throws NullPointerException {
	    try {
	     String sql = "insert into five_record values (?,?,?,?)";
	     PreparedStatement pst = getCon().prepareStatement(sql);
	     pst = getCon().prepareStatement(sql);
	     
	     pst.setInt(1, recordEntity.getRecordid());
	     pst.setDate(2, (Date)recordEntity.getLeavetime());
	     pst.setDate(3, (Date)recordEntity.getBacktime());
	     pst.setInt(4, recordEntity.getStuid().getStuid());
	     return pst.executeUpdate();
	    } catch (SQLException e) {
	     e.printStackTrace();
	    } catch (NullPointerException e) {
	     return 0;
	    }finally{
	   }
	    return 0;
	   }
	


	// 添加 出入登记记录
	public void addRecord(RecordEntity record) {

		//List<StudentEntity> list = studentDao.getAllStudent();		
		int rs = 0;
		try {
			String sql = "insert into five_record values (null,?,?,?)";

			String time = time();
			PreparedStatement pst = getCon().prepareStatement(sql);
			pst.setDate(1, Date.valueOf(time));
			pst.setDate(2, (Date) record.getBacktime());
			pst.setInt(3, record.getStuid().getStuid());
			rs = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAdd(){
		
		String time = time();
		StudentDao stu = new StudentDao();
		StudentEntity stuno = stu.getStudentByStuno(20160101);
		RecordEntity record = new RecordEntity(Date.valueOf(time),Date.valueOf("2019-09-07"),stuno);
		addRecord(record);
	}

	// 删除 出入登记记录 根据id
	public void delRecordById(int id) {
		try {
			String sql = "delete from five_record where recordid=?";
			PreparedStatement pst = getCon().prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDel() {
		delRecordById(1);
	}

	//分页查所有
	public List<RecordEntity> getAllBigByPage(FenYe fenye){
		List<RecordEntity> list = new ArrayList<RecordEntity>();
		String time = time();
		try {
			Connection con = Tools.getCon();
			String sql = "select * from five_record limit ?,?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, fenye.getPageIndex());
			pst.setInt(2, fenye.getPageSize());
			ResultSet res = pst.executeQuery();
			while(res.next()){
				StudentEntity record1 = new StudentDao().getStudentById(res.getInt(4));
				
				RecordEntity record = new RecordEntity(res.getInt(1),
						Date.valueOf(time), res.getDate(3), record1);
				list.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 查询所有 出入记录
	public List<RecordEntity> getAllRecord() {
		List<RecordEntity> list = new ArrayList<RecordEntity>();
		String time = time();
		try {
			Connection con = Tools.getCon();
			String sql = "select * from five_record";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet res = pst.executeQuery();
			while (res.next()) {
				StudentEntity stu = new StudentDao().getStudentById(res
						.getInt(4));
				RecordEntity record = new RecordEntity(res.getInt(1),
						Date.valueOf(time), res.getDate(3), stu);
					list.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Test
	public void testAll() {
		List<RecordEntity> list = getAllRecord();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getRecordid() + " "
					+ list.get(i).getLeavetime() + " "
					+ list.get(i).getBacktime() + " "
					+ list.get(i).getStuid().getStuname());
		}
	}

}
