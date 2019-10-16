package com.ambow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;



import org.junit.Test;

import com.ambow.entity.AdminEntity;
import com.ambow.entity.BuildingEntity;
import com.ambow.util.Tools;






public class AdminDao {

	
	// 登录
		public AdminEntity login(String adminname, String adminpwd) {

			AdminEntity admin = null;
			try {
				Connection con = Tools.getCon();
				String sql = "select * from five_adminmanager where adminname=? and adminpwd=?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, adminname);
				pst.setString(2, adminpwd);
			
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					admin = new AdminEntity();
					admin.setAdminid(rs.getInt(1));
					admin.setAdminname(rs.getString(2));
					admin.setAdminpwd(rs.getString(3));
					BuildingEntity build = new BuildingEntity();
					build.setBuildid(rs.getInt(4));
					admin.setBuildid(build);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return admin;
		}


		
		//通过Id得到宿管
		public AdminEntity getAdminById(int adminid){
			AdminEntity admin=null;
			Connection con=Tools.getCon();
			
			String sql="select * from five_adminmanager where adminid=?";

			try {
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, adminid);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					BuildingEntity build = new BuildingDao().getBuildById(rs.getInt(4));
					
					admin = new AdminEntity(rs.getInt(1), rs.getString(2), rs.getString(3), build);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return admin;
		}

		@Test
		public void testgetgetAdminById() {
			System.out.println(getAdminById(1).getBuildid().getBuildname());
		
		}

		// 得到所有宿管
		public List<AdminEntity> getAllAdmin() {
			List<AdminEntity> list = new ArrayList<AdminEntity>();
			try {

				Connection con = Tools.getCon();
				String sql = "select* from five_adminmanager";
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					AdminEntity admin = new AdminEntity();
					admin.setAdminid(rs.getInt(1));
					admin.setAdminname(rs.getString(2));
					admin.setAdminpwd(rs.getString(3));
					BuildingEntity build = new BuildingDao().getBuildById(rs.getInt(4));
					admin.setBuildid(build);
					list.add(admin);
				}

				return list;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;

		}

		@Test
		public void testgetAllAdmin() {
			List<AdminEntity> list = getAllAdmin();
		/*	if (list != null) {
				System.out.println("显示成功");
			} else {
				System.out.println("显示失败");
			}
*/
			for (AdminEntity adminEntity : list) {
				System.out.println(adminEntity.getBuildid().getBuildname());
			}
		}


		
		
	  //添加宿管
		public int addAdmin(AdminEntity admin) {
			  int rs = 0;
				try {  
			  Connection con = Tools.getCon();
				String sql="insert into five_adminmanager values(null,?,?,?)";
				PreparedStatement pst=con.prepareStatement(sql);
	       	pst.setString(1,admin.getAdminname());
	       	pst.setString(2,admin.getAdminpwd());
	       	pst.setInt(3, admin.getBuildid().getBuildid());
	       	return pst.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					return rs;
		}

		@Test()
		public void testaddAdmin() {
			AdminEntity admin = new AdminEntity();
			admin.setAdminid(7);
			admin.setAdminname("aaa");
			admin.setAdminpwd("123");
			admin.setBuildid(new BuildingDao().getBuildById(2));

			addAdmin(admin);


			addAdmin(admin);
		

		}

		// 修改宿管
		public int updateAdmin(AdminEntity admin) {
			Connection con = Tools.getCon();
		
			try {
				String sql = "update five_adminmanager set adminname=?,adminpwd=?,buildid=? where adminid=?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, admin.getAdminname());
				pst.setString(2, admin.getAdminpwd());
				pst.setInt(3, admin.getBuildid().getBuildid());
				pst.setInt(4, admin.getAdminid());
				System.out.println("diaoyong...........dao......");
				return pst.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return 0;
			
		}

		@Test
		public void testupdate() {

		/*	AdminEntity admin = new AdminEntity();
			admin.setAdminid(2);
			admin.setAdminname("ww");
			admin.setAdminpwd("123");
			BuildingEntity build = new BuildingDao().getBuildById(3);
			admin.setBuildid(build);
			updateAdmin(admin);
			
	*/
			BuildingEntity buildid=new BuildingDao().getBuildById(2);
			AdminEntity admin=new AdminEntity(2, "iop", "123", buildid);
			updateAdmin(admin);
		}
		

		// 删除宿管
		public int deleteAdmin(int adminid) {
			int a = 0;
			try {
				Connection con = Tools.getCon();
				String sql = "delete from five_adminmanager where adminid = ?";

				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, adminid);
				a = ps.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return a;

		}

		@Test
		public void testdeleteAdmin() {

			int res = deleteAdmin(4);
			if (res != 0) {
				System.out.println("成功");
			} else {
				System.out.println("失败");
			}
		}
	
	
}
