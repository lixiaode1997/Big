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
import com.ambow.entity.RoomEntity;
import com.ambow.entity.StudentEntity;
import com.ambow.entity.StudentMiddleEntity;
import com.ambow.util.Tools;


public class RoomDao extends Tools{

	//根据id查询
	public RoomEntity getRoomById(int id){
		RoomEntity room=null;
		try {
			Connection con=getCon();
			String sql="select * from five_roommanager where roomid=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs=pst.executeQuery();
			if(rs.next()){
				BuildingEntity build=new BuildingDao().getBuildById(rs.getInt(5));
				room=new RoomEntity(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),build);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return room;
	}
	
	//根据no查询
		public RoomEntity getRoomByno(int no){
			RoomEntity room=null;
			try {
				Connection con=getCon();
				String sql="select * from five_roommanager where roomno=?";
				PreparedStatement pst=con.prepareStatement(sql);
				pst.setInt(1, no);
				ResultSet rs=pst.executeQuery();
				if(rs.next()){
					BuildingEntity build=new BuildingDao().getBuildById(rs.getInt(5));
					room=new RoomEntity(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),build);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return room;
		}
		
		//根据buildid查询
				public RoomEntity getRoomBybuildid(int buildid,int roomno){
					RoomEntity room=null;
					try {
						Connection con=getCon();
						String sql="select * from five_roommanager where buildid=? and roomno=?";
						PreparedStatement pst=con.prepareStatement(sql);
						pst.setInt(1, buildid);
						pst.setInt(2, roomno);
						ResultSet rs=pst.executeQuery();
						if(rs.next()){
							BuildingEntity build=new BuildingDao().getBuildById(rs.getInt(5));
							room=new RoomEntity(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),build);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return room;
				}
				
				@Test
				public void testex(){
					RoomEntity room = getRoomBybuildid(1, 101);
					System.out.println(room.getRoomno()+"\t"+room.getBuildid().getBuildid());
				}
	
	@Test
	public void testGetRoomById(){
		System.out.println(getRoomById(2).getRoomno());
	}
	//查询所有By  buildid
	public List<RoomEntity> getAllRoomByBuildId(int buildid){
		List<RoomEntity> list=new ArrayList<RoomEntity>();
		try {
			Connection con=getCon();
			String sql="select * from five_roommanager where buildid=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, buildid);
			ResultSet res=pst.executeQuery();
			while (res.next()) {
				BuildingEntity build=new BuildingDao().getBuildById(res.getInt(5));
				list.add(new RoomEntity(res.getInt(1),res.getInt(2),res.getInt(3),res.getInt(4),build));
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Test
	public void wuuw(){
		List<RoomEntity> list=getAllRoomByBuildId(6);
		for (RoomEntity item : list) {
			System.out.println(item.getRoomid());
		}
	}
	//查询所有
	public List<RoomEntity> getAllRoom(){
		List<RoomEntity> list=new ArrayList<RoomEntity>();
		try {
			Connection con=getCon();
			String sql="select * from five_roommanager";
			PreparedStatement pst=con.prepareStatement(sql);
			ResultSet res=pst.executeQuery();
			while (res.next()) {
				BuildingEntity build=new BuildingDao().getBuildById(res.getInt(5));
				list.add(new RoomEntity(res.getInt(1),res.getInt(2),res.getInt(3),res.getInt(4),build));
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Test
	public void testGetAllRoom(){
		List<RoomEntity> list=getAllRoom();
		for (RoomEntity room : list) {
			System.out.println(room.getBuildid().getBuildname());
		}
	}
	
	//得到男生楼
	//得到男生楼
	 public List<DeptEntity> getBoyBuild(String deptname){
	  List<DeptEntity> list=new ArrayList<DeptEntity>();
	  try {
	   Connection con=getCon();
	   String sql="SELECT * from five_dept d LEFT JOIN five_building b on d.buildid=b.buildid where d.deptname=? and b.buildsex=0";
	   PreparedStatement pst=con.prepareStatement(sql);
	   pst.setString(1, deptname);
	   ResultSet res=pst.executeQuery();
	   while (res.next()) {
	    //System.out.println(res.getInt(6));
	    BuildingEntity build=new BuildingDao().getBuildById(res.getInt(3));
	   list.add(new DeptEntity(res.getInt(1), res.getString(2),build ));
	   }
	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
	  
	  return list;
	 }
	 @Test
	 public void testGetBoyBuild(){
	  List<DeptEntity> list=getBoyBuild("计算机系");
	  for (DeptEntity room : list) {
	   System.out.println(room.getBuildid().getBuildsex());
	  }
	 }
	//得到女生楼
	  public List<DeptEntity> getGirlBuild(String deptname){
	   List<DeptEntity> list=new ArrayList<DeptEntity>();
	   try {
	    Connection con=getCon();
	    String sql="SELECT * from five_dept d LEFT JOIN five_building b on d.buildid=b.buildid where d.deptname=? and b.buildsex=1";
	    PreparedStatement pst=con.prepareStatement(sql);
	    pst.setString(1, deptname);
	    ResultSet res=pst.executeQuery();
	    while (res.next()) {
	     //System.out.println(res.getInt(6));
	     BuildingEntity build=new BuildingDao().getBuildById(res.getInt(3));
	    list.add(new DeptEntity(res.getInt(1), res.getString(2),build ));
	    }
	   } catch (SQLException e) {
	    e.printStackTrace();
	   }
	   
	   return list;
	  }
	  @Test
	  public void testGetGirlBuild(){
	   List<DeptEntity> list=getGirlBuild("计算机系");
	   for (DeptEntity room : list) {
	    System.out.println(room.getBuildid().getBuildsex());
	   }
	  }
	  
	 
	 
	//查询所有空房间
		public List<RoomEntity> getAllNullRoom(int buildid){
			List<RoomEntity> list=new ArrayList<RoomEntity>();
			try {
				Connection con=getCon();
				String sql="select * from five_roommanager where buildid=? AND (roomnum<8 or roomnum is null)  ";
				PreparedStatement pst=con.prepareStatement(sql);
				pst.setInt(1, buildid);
				ResultSet res=pst.executeQuery();
				while (res.next()) {
					BuildingEntity build=new BuildingDao().getBuildById(res.getInt(5));
					list.add(new RoomEntity(res.getInt(1),res.getInt(2),res.getInt(3),res.getInt(4),build));
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return list;
		}
		@Test
		public void testGetAllNullRoom(){
			List<RoomEntity> list=getAllNullRoom(1);
			for (RoomEntity room : list) {
				System.out.println(room.getRoomnum());
			}
		}
		
		//查询所有空房间
				public List<RoomEntity> getAllNullRoom1(int buildid){
					List<RoomEntity> list=new ArrayList<RoomEntity>();
					try {
						Connection con=getCon();
						String sql="select * from five_roommanager where buildid=? AND  roomnum=0 ";
						PreparedStatement pst=con.prepareStatement(sql);
						pst.setInt(1, buildid);
						ResultSet res=pst.executeQuery();
						while (res.next()) {
							BuildingEntity build=new BuildingDao().getBuildById(res.getInt(5));
							list.add(new RoomEntity(res.getInt(1),res.getInt(2),res.getInt(3),res.getInt(4),build));
							}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					return list;
				}
				@Test
				public void testGetAllNullRoom1(){
					List<RoomEntity> list=getAllNullRoom1(1);
					for (RoomEntity room : list) {
						System.out.println(room.getRoomnum());
					}
				}
	//增加
	public int addRoom(RoomEntity room){
		try {
			String sql="insert into five_roommanager values(null,?,8,8,?)";
			PreparedStatement pst=getCon().prepareStatement(sql);
			pst.setInt(1, room.getRoomno());
			pst.setInt(2, room.getBuildid().getBuildid());
			return pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Test
	public void testAddRoom(){
		BuildingEntity build=new BuildingDao().getBuildById(2);
		RoomEntity room=new RoomEntity(15, 909, 4, 4, build);
		addRoom(room);
	}
	
	//删除  根据id
	public void delRoomById(int id){
		try {
			String sql="delete from five_roommanager where roomid=?";
			PreparedStatement pst=getCon().prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDelRoomById(){
		delRoomById(14);
	}
	
	//修改  根据id
	public int updateRoom(RoomEntity room){
		try {
			String sql="update five_roommanager set roomno=?,buildid=? where roomid=?";
			PreparedStatement pst=getCon().prepareStatement(sql);
			pst.setInt(1, room.getRoomno());
			pst.setInt(2, room.getBuildid().getBuildid());
			pst.setInt(3, room.getRoomid());
			return pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return 0;
	}
	
	@Test
	public void testUpdate(){
		BuildingEntity build=new BuildingDao().getBuildById(2);
		RoomEntity room=new RoomEntity(13,302,build);
		updateRoom(room);
	}
	
	//让数据库中roomnum更新的方法
	public void updateRoomnum(int roomid){
		try {
			Connection con=Tools.getCon();
			List<StudentMiddleEntity> list=new StudentMiddleDao().getAllByRoomId(roomid);
			String sql="update five_roommanager set roomnum=? where roomid=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, list.size());
			pst.setInt(2, roomid);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test8(){
		updateRoomnum(2);
	}
	
	
	
//获得所有未满的房间
	public List<RoomEntity> getAllWeiManRoom() {
	
			List<RoomEntity> list=new ArrayList<RoomEntity>();
			try {
				Connection con=getCon();
				String sql="select * from five_roommanager where roomnum>0 and roomnum<8";
				PreparedStatement pst=con.prepareStatement(sql);
				ResultSet res=pst.executeQuery();
				while (res.next()) {
					BuildingEntity build=new BuildingDao().getBuildById(res.getInt(5));
					list.add(new RoomEntity(res.getInt(1),res.getInt(2),res.getInt(3),res.getInt(4),build));
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
	
	
	}
	
	
	//获得所有未满的房间
	public List<RoomEntity> getAllWeiManRoomByBuildId(int buildid) {
	
			List<RoomEntity> list=new ArrayList<RoomEntity>();
			try {
				Connection con=getCon();
				String sql="select * from five_roommanager where roomnum>0 and roomnum<8 and buildid=?";
				PreparedStatement pst=con.prepareStatement(sql);
				pst.setInt(1, buildid);
				ResultSet res=pst.executeQuery();
				while (res.next()) {
					BuildingEntity build=new BuildingDao().getBuildById(res.getInt(5));
					list.add(new RoomEntity(res.getInt(1),res.getInt(2),res.getInt(3),res.getInt(4),build));
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
	
	
	}
	
	@Test
	public void test22(){
		List<RoomEntity> list=getAllWeiManRoomByBuildId(1);
		for(RoomEntity item:list){
			System.out.println(item);
		}
	}
	
	
	//获得所有yi满的房间
		public List<RoomEntity> getAllYiManRoomByBuildId(int buildid) {
		
				List<RoomEntity> list=new ArrayList<RoomEntity>();
				try {
					Connection con=getCon();
					String sql="select * from five_roommanager where roomnum=8 and buildid=?";
					PreparedStatement pst=con.prepareStatement(sql);
					pst.setInt(1, buildid);
					ResultSet res=pst.executeQuery();
					while (res.next()) {
						BuildingEntity build=new BuildingDao().getBuildById(res.getInt(5));
						list.add(new RoomEntity(res.getInt(1),res.getInt(2),res.getInt(3),res.getInt(4),build));
						}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return list;
		
		
		}
		
		@Test
		public void test23(){
			List<RoomEntity> list=getAllYiManRoomByBuildId(1);
			for(RoomEntity item:list){
				System.out.println(item.getRoomnum());
			}
		}
	
	//通过楼号查询
		public List<RoomEntity> getRoomBybuildName(String buildname){
			List<RoomEntity> list=new ArrayList<RoomEntity>();
			try {
				Connection con=getCon();
				String sql="select * from five_roommanager where buildid=?";
				PreparedStatement pst=con.prepareStatement(sql);
				pst.setInt(1,new BuildingDao().getBuildByName(buildname).getBuildid());
				ResultSet res=pst.executeQuery();
				
				while (res.next()) {
					BuildingEntity build=new BuildingDao().getBuildById(res.getInt(5));
					list.add(new RoomEntity(res.getInt(1),res.getInt(2),res.getInt(3),res.getInt(4),build));
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
		
		
		@Test
		public void test11(){
			List<RoomEntity> list=getRoomBybuildName("1号楼");
			for(RoomEntity item:list){
				System.out.println(item.getRoomno()+"  "+item.getBuildid().getBuildid());
			}
		}

		//根据房间人数显示所有
		public List<RoomEntity> getRoomByRoomNum(int roomnum){
			List<RoomEntity> list=new ArrayList<RoomEntity>();
			try {
				Connection con=getCon();
				String sql="select * from five_roommanager where roomnum=?";
				PreparedStatement pst=con.prepareStatement(sql);
				pst.setInt(1,roomnum);
				ResultSet res=pst.executeQuery();
				
				while (res.next()) {
					RoomEntity room=new RoomEntity();
					room.setRoomid(res.getInt(1));
					room.setRoomno(res.getInt(2));
					room.setRoombed(res.getInt(3));
					room.setRoomnum(res.getInt(4));
					BuildingEntity build=new BuildingDao().getBuildById(res.getInt(5));
					room.setBuildid(build);
					list.add(room);
					
					/*BuildingEntity build=new BuildingDao().getBuildById(res.getInt(5));
					list.add(new RoomEntity(res.getInt(1),res.getInt(2),res.getInt(3),res.getInt(4),build));*/
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
		
		@Test
		public void test(){
			System.out.println(000);
			List<RoomEntity> list=getRoomByRoomNum(8);
			for (RoomEntity room : list) {
				System.out.println(room.getRoomno());
			}
		}

	
	
}
