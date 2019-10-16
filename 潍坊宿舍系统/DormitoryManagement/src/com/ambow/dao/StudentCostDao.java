package com.ambow.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ambow.entity.CostEntity;
import com.ambow.entity.StudentCost;
import com.ambow.util.Tools;

public class StudentCostDao extends Tools {
	
	
	
	
	//添加
	public int addStudentCost(int stuid,int costid) {

		try {
			String sql = "insert into five_stucost values(null,?,?)";
			PreparedStatement ps = getCon().prepareStatement(sql);
			ps.setInt(1,stuid);
			ps.setInt(2,costid);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean isExistInStuCost(int stuid,int costid){
		boolean flag=false;
		try {
			String sql="select * from five_stucost where stuid=? and costid=?";
			PreparedStatement ps = getCon().prepareStatement(sql);
			ps.setInt(1,stuid);
			ps.setInt(2,costid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				flag=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	@Test
	public void testIisExistInStuCost(){
		System.out.println(isExistInStuCost(2,2));
	}
	
	public List<StudentCost> getAllStuCost(){
		List<StudentCost> list = new ArrayList<StudentCost>();
		try {
			String sql="select * from five_stucost";
			PreparedStatement ps = getCon().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				int scid=rs.getInt(1);
				int stuid=rs.getInt(2);
				int costid=rs.getInt(3);
				StudentCost sc = new StudentCost(scid,stuid,costid);
				list.add(sc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Test
	public void testGetAllStuCost(){
		List<StudentCost> list=getAllStuCost();
		for(StudentCost item:list){
			System.out.println(item.getStuid()+"号学生交了第"+item.getCostid()+"的学费");
		}
	}
	
}
