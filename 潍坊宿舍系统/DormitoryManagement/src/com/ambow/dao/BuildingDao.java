package com.ambow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ambow.entity.BuildingEntity;
import com.ambow.util.Tools;

public class BuildingDao extends Tools{

	//根据id查询所有
	public BuildingEntity getBuildById(int buildid){
		BuildingEntity build=null;
		Connection con=Tools.getCon();
		String sql="select * from five_building where buildid=?";
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, buildid);
			ResultSet rs=pst.executeQuery();
			if(rs.next()){
				build=new BuildingEntity(buildid, rs.getString(2),rs.getInt(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return build;
	}
	
	@Test
	public void testGetBuildById(){
		System.out.println(getBuildById(2).getBuildname());
	}
	
	//查询所有
	public List<BuildingEntity> getAllBuild(){
		List<BuildingEntity> list=new ArrayList<BuildingEntity>();
		try {
			Connection con=getCon();
			String sql="select * from five_building";
			PreparedStatement pst=con.prepareStatement(sql);
			ResultSet res=pst.executeQuery();
			while (res.next()) {
				list.add(new BuildingEntity(res.getInt(1), res.getString(2), res.getInt(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@Test
	public void testGetAllBuild(){
		List<BuildingEntity> list=getAllBuild();
		for (BuildingEntity build : list) {
			System.out.println(build.getBuildname()+" "+build.getBuildsex());
		}
	}
	
	//删除 根据id
			public void delBuildById(int id){
				try {
					String sql="delete from five_building where buildid=?";
					PreparedStatement pst=getCon().prepareStatement(sql);
					pst.setInt(1, id);
					pst.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Test
			public void testDel(){
				delBuildById(7);
			}
			
	//修改  根据id
			public int updateBuildById(BuildingEntity build){
				try {
					String sql="update five_building set buildname=?,buildsex=? where buildid=?";
					PreparedStatement pst=getCon().prepareStatement(sql);
					pst.setString(1, build.getBuildname());
					pst.setInt(2,build.getBuildsex());
					pst.setInt(3,build.getBuildid());
					
					return pst.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return 0;
			}
			
			@Test
			public void testUpdate(){
				BuildingEntity build=new BuildingEntity(8,"7号楼", 0);
				updateBuildById(build);
			}
			
			
		//增加
			public int addBuild(BuildingEntity build) {
				  int rs = 0;
				  try {
				   String sql = "insert into five_building values (null,?,?)";

				   PreparedStatement pst =getCon().prepareStatement(sql);
				   pst.setString(1, build.getBuildname());
				   pst.setInt(2, build.getBuildsex());
				   rs = pst.executeUpdate();
				  } catch (SQLException e) {
				   // TODO Auto-generated catch block
				   e.printStackTrace();
				  }
				  return rs;
				 } 
			@Test
			public void testAddBuild(){
				BuildingEntity build=new BuildingEntity("999", 1);
				addBuild(build);
			}
			
			
			//通过名字得到单条信息
			public BuildingEntity getBuildByName(String buildname){
				BuildingEntity build=null;
				Connection con=Tools.getCon();
				String sql="select * from five_building where buildname=?";
				try {
					PreparedStatement pst=con.prepareStatement(sql);
					pst.setString(1, buildname);
					ResultSet rs=pst.executeQuery();
					if(rs.next()){
						build=new BuildingEntity(rs.getInt(1), rs.getString(2),rs.getInt(3));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return build;
			}
				
			@Test
			public void test(){
				System.out.println(getBuildByName("1号楼").getBuildid());
			}
}
