package com.ambow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.ambow.entity.RoomEntity;
import com.ambow.entity.StudentEntity;
import com.ambow.entity.SuperEntity;
import com.ambow.util.Tools;

public class SuperDao extends Tools{

	public SuperEntity login(String supername,String superpwd){
		SuperEntity sup=null;
		try {
			Connection con=getCon();
			String sql="select * from five_supermanager where supername=? and superpwd=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, supername);
			pst.setString(2, superpwd);
			ResultSet rs=pst.executeQuery();
			if(rs.next()){
				sup=new SuperEntity(supername, superpwd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sup;
	}
	
	@Test
	public void testLogin(){
		
		String name ="super";
		String pwd = "123";
		SuperEntity sup=login(name, pwd);
		if(sup!=null){
			System.out.println("ok");
		}else{
			System.out.println("error");
		}
	}
}
