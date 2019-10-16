package com.ambow.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ambow.entity.AdminEntity;
import com.ambow.entity.BoradEntity;
import com.ambow.entity.BuildingEntity;
import com.ambow.util.Tools;

public class BoradDao {

	//id查询
	public BoradEntity getBoradById(int boradid){
		BoradEntity borad=null;
		Connection con=Tools.getCon();
		String sql="select * from five_borad where boradid=?";
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, boradid);
			ResultSet rs=pst.executeQuery();
			if(rs.next()){
				AdminEntity admin=new AdminDao().getAdminById(rs.getInt(4));
				borad=new BoradEntity(boradid, rs.getString(2), rs.getDate(3), admin);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borad;
	}
	
	@Test
	public void testGetBoradById(){
		System.out.println(getBoradById(1).getBoradCOntent());
	}
	
	//查询所有
	public List<BoradEntity> getAllBorad(){
		List<BoradEntity> list=new ArrayList<BoradEntity>();
		try {
			Connection con=Tools.getCon();
			String sql="select * from five_borad";
			PreparedStatement pst=con.prepareStatement(sql);
			ResultSet res=pst.executeQuery();
			while (res.next()) {
				AdminEntity admin=new AdminDao().getAdminById(res.getInt(4));
				list.add(new BoradEntity(res.getInt(1),res.getString(2),res.getDate(3),admin));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@Test
	public void testGetAllBuild(){
		List<BoradEntity> list=getAllBorad();
		for (BoradEntity borad : list) {
			System.out.println(borad.getBoradCOntent());
		}
	}
	
	//增加
	public int addBorad(BoradEntity borad) {
		  int rs = 0;
		  try {
		   String sql = "insert into five_borad values (null,?,?,?)";

		   PreparedStatement pst =Tools.getCon().prepareStatement(sql);
		   pst.setString(1, borad.getBoradCOntent());
		   pst.setDate(2, (Date) borad.getBoradDate());
		   pst.setInt(3, borad.getAdmin().getAdminid());
		   rs = pst.executeUpdate();
		  } catch (SQLException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		  return rs;
		 } 
	@Test
	public void testAddBuild(){
		AdminEntity admin=new AdminDao().getAdminById(1);
		BoradEntity borad=new BoradEntity("qqqq",Date.valueOf("2019-09-12"),admin);
		addBorad(borad);
	}
	
	//删除
	public void delBorddById(int id){
		try {
			String sql="delete from five_borad where boradid=?";
			PreparedStatement pst=Tools.getCon().prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDel(){
		delBorddById(2);
	}
	
	//修改
	public int updateBoradById(BoradEntity borad){
		try {
			String sql="update five_borad set boradcontent=?,boraddate=?,boradperson=? where boradid=?";
			PreparedStatement pst=Tools.getCon().prepareStatement(sql);
			pst.setString(1, borad.getBoradCOntent());
			pst.setDate(2,(Date) borad.getBoradDate());
			pst.setInt(3,borad.getAdmin().getAdminid());
			pst.setInt(4,borad.getBoradId());
			
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	@Test
	public void testUpdate(){
		AdminEntity admin=new AdminDao().getAdminById(2);
		BoradEntity borad=new BoradEntity(1,"jingtianbufj",Date.valueOf("2019-7-7"),admin);
		updateBoradById(borad);
	}
	
	
}
