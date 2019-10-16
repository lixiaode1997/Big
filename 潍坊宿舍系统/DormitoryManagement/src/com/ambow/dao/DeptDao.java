package com.ambow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ambow.entity.BuildingEntity;
import com.ambow.entity.DeptEntity;
import com.ambow.util.Tools;

public class DeptDao extends Tools{

	//根据id查询
		public DeptEntity getDeptById(int id){
			DeptEntity dept=null;
			try {
				Connection con=getCon();
				String sql="select * from five_dept where deptid=?";
				PreparedStatement pst=con.prepareStatement(sql);
				pst.setInt(1, id);
				ResultSet rs=pst.executeQuery();
				if(rs.next()){
					BuildingEntity build=new BuildingDao().getBuildById(rs.getInt(3));
					dept=new DeptEntity(rs.getInt(1),rs.getString(2),build);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return dept;
		}
		
		@Test
		public void testGetDeptById(){
			System.out.println(getDeptById(2).getBuildid().getBuildname());
		}
		
		//名字去重
		public List<Object> getNameDept(){
			List<Object> list=new ArrayList<Object>();
		String name=null;
			try {
				Connection con=getCon();
				String sql="select  deptname from five_dept";
				PreparedStatement pst=con.prepareStatement(sql);
			
				ResultSet res=pst.executeQuery();
				while (res.next()) {
					name=res.getString(1);
					list.add(name);
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
		@Test
		public void name(){
			List<Object> list=getNameDept();
			for (Object item : list) {
				System.out.println(item);
			}
		}
		//查询所有
		public List<DeptEntity> getAllDept(){
			List<DeptEntity> list=new ArrayList<DeptEntity>();
			try {
				Connection con=getCon();
				String sql="select  * from five_dept";
				PreparedStatement pst=con.prepareStatement(sql);
				ResultSet res=pst.executeQuery();
				while (res.next()) {
					BuildingEntity build=new BuildingDao().getBuildById(res.getInt(3));
					list.add(new DeptEntity(res.getInt(1),res.getString(2),build));
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
		@Test
		public void testGetAllDept(){
			List<DeptEntity> list=getAllDept();
			for (DeptEntity dept : list) {
				System.out.println(dept.getDeptname()+" "+dept.getBuildid().getBuildname());
			}
		}
		
		
		//增加
		public int addDept(DeptEntity dept){
			try {
				String sql="insert into five_dept values(null,?,?)";
				PreparedStatement pst=getCon().prepareStatement(sql);
				pst.setString(1, dept.getDeptname());
				pst.setInt(2, dept.getBuildid().getBuildid());
				return pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;
		}
		
		@Test
		public void testAddDept(){
			BuildingEntity build=new BuildingDao().getBuildById(2);
			DeptEntity dept=new DeptEntity(8,"biubiu",build);
			addDept(dept);
		}
		
		//删除  根据id
		public void delDeptById(int id){
			try {
				String sql="delete from five_dept where deptid=?";
				PreparedStatement pst=getCon().prepareStatement(sql);
				pst.setInt(1, id);
				pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Test
		public void testDelDeptById(){
			delDeptById(8);
		}
		
		//修改  根据id
		public int updateDept(DeptEntity dept){
			try {
				String sql="update five_dept set deptname=?,buildid=? where deptid=?";
				PreparedStatement pst=getCon().prepareStatement(sql);
				pst.setString(1, dept.getDeptname());
				pst.setInt(2, dept.getBuildid().getBuildid());
				pst.setInt(3, dept.getDeptid());
				return pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return 0;
		}
		
		@Test
		public void testUpdate(){
			BuildingEntity build=new BuildingDao().getBuildById(2);
			DeptEntity dept=new DeptEntity(7,"wwww",build);
			updateDept(dept);
		}

		public DeptEntity getDeptByName(String studept) {
			// TODO Auto-generated method stub
			DeptEntity dept=null;
			try {
				Connection con=getCon();
				String sql="select * from five_dept where deptname=?";
				PreparedStatement pst=con.prepareStatement(sql);
				pst.setString(1, studept);
				ResultSet rs=pst.executeQuery();
				if(rs.next()){
					BuildingEntity build=new BuildingDao().getBuildById(rs.getInt(3));
					dept=new DeptEntity(rs.getInt(1),rs.getString(2),build);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return dept;
		}
		
		
}
