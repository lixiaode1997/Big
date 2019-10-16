package com.ambow.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


public class Tools {

	public static Connection getCon(){
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/five_dormitorysystem?useUnicode=true&characterEncoding=utf-8";
			String user="root";
			String password="";
			try {
				con=DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	return con;
	} 
	
	public static void close(Connection conn,Statement state,ResultSet rs) {
		try {
			if (rs!=null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (state!=null) {
				state.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn!=null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGetCon(){
		Connection con=Tools.getCon();
		if(con==null){
			System.out.println("连接失败");
		}else{System.out.println("success");}
	}
	
}
