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
import com.ambow.entity.NoticeEntity;
import com.ambow.entity.RoomEntity;
import com.ambow.entity.StudentEntity;
import com.ambow.entity.StudentMiddleEntity;
import com.ambow.util.Tools;

public class NoticeDao extends Tools{

	//查询全部维修记录
	public List<NoticeEntity> getAllNotic(){
		List<NoticeEntity> list =new ArrayList<NoticeEntity>();
		try {
			String sql="select * from five_notice";
			PreparedStatement ps = getCon().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				int noticeid=rs.getInt(1);
				String noticecontent=rs.getString(2);
				int noticeflag=rs.getInt(3);
				RoomEntity room =new RoomDao().getRoomById(rs.getInt(4));
				NoticeEntity notice = new NoticeEntity(noticeid,noticecontent,noticeflag,room);
				list.add(notice);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	} 
	
	//查不出来东西 空指针异常
	@Test
	public void testGetAllNotic(){
		List<NoticeEntity> list = getAllNotic();
		for(NoticeEntity item:list){
			System.out.println(item.getNoticeid()+"  "+item.getNoticecontent()+"  "+item.getRoomid().getRoomno());
		}
	}
	
	//根据id查询维修记录
	public NoticeEntity getAllNoticById(int id){
		NoticeEntity notice = null;
		try {
			String sql="select * from five_notice where noticeid=?";
			PreparedStatement ps = getCon().prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				int noticeid=rs.getInt(1);
				String noticecontent=rs.getString(2);
				int noticeflag=rs.getInt(3);
				RoomEntity room =new RoomDao().getRoomById(rs.getInt(4));
				notice = new NoticeEntity(noticeid,noticecontent,noticeflag,room);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notice;
	} 
	
	@Test
	public void testGetAllNoticById(){
		System.out.println(getAllNoticById(3).getNoticeid()+"   "+getAllNoticById(3).getNoticecontent()+"  "+getAllNoticById(3).getRoomid().getRoomno());
	}
	
	//根据flag查询维修记录
		public List<NoticeEntity> getAllNoticByFlag(int flag){
			List<NoticeEntity> list = new ArrayList<NoticeEntity>();
			try {
				String sql="select * from five_notice where noticeflag=?";
				PreparedStatement ps = getCon().prepareStatement(sql);
				ps.setInt(1,flag);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					RoomEntity room =new RoomDao().getRoomById(rs.getInt(4));		
					list.add(new NoticeEntity(rs.getInt(1), rs.getString(2), flag, room));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		} 
		@Test
		public void testGetAllByFlag(){
			List<NoticeEntity> list=getAllNoticByFlag(0);
			for (NoticeEntity notice : list) {
				System.out.println(notice.getNoticecontent());
			}
		}
	
	//根据id删除维修记录
	public int delNoticeEntityById(int noticeid){
		try {
			String sql = "delete from five_notice where noticeid=?";
			PreparedStatement ps = getCon().prepareStatement(sql);
			ps.setInt(1,noticeid);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	
	@Test
	public void testDelNoticeEntityById(){
		if(delNoticeEntityById(2)!=0){
			System.out.println("yes");
		}else{
			System.out.println("no");
		}
	}
	
	//添加
	public int insertNoticeEntity(NoticeEntity notice){
		
		try {
			String sql="insert into five_notice values(null,?,?,?)";
			PreparedStatement ps = getCon().prepareStatement(sql);
			ps.setString(1,notice.getNoticecontent());
			ps.setInt(2,notice.getNoticeflag());
			ps.setInt(3,notice.getRoomid().getRoomid());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Test
	public void testInsertNoticeEntity(){
		RoomEntity re=new RoomEntity();
		re.setRoomid(1);
		NoticeEntity notice=new NoticeEntity("789",0,re);
		if(insertNoticeEntity(notice)!=0){
			System.out.println("yes");
		}else{
			System.out.println("no");
		}
	}
	
	//根据id 修改    第四列接的值错误 已修改
	public int updateNoticeEntityById(NoticeEntity notice){
			try {
				String sql="update five_notice set noticecontent=?,noticeflag=?,roomid=? where noticeid=?";
				PreparedStatement pst=getCon().prepareStatement(sql);
				pst.setString(1,notice.getNoticecontent());
				pst.setInt(2, notice.getNoticeflag());
				pst.setInt(3,notice.getRoomid().getRoomid());
				pst.setInt(4,notice.getNoticeid());
				return pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return 0;
		}
		
		@Test
		public void testUpdate(){
			NoticeEntity notice = new NoticeEntity(3,"1111",1,new RoomDao().getRoomById(4));
			
			if(updateNoticeEntityById(notice)!=0){
				System.out.println("yes");
			}else{
				System.out.println("no");
			}
		}
		
	//根据id修改flag
		public int updateFlagById(int id,int flag){
			try {
				String sql="update five_notice set noticeflag=? where noticeid=?";
				PreparedStatement pst=getCon().prepareStatement(sql);
				pst.setInt(1, flag);
				pst.setInt(2,id);
				return pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return 0;
		}
	@Test
	public void testUpdateById(){
		updateFlagById(2,1);
	}
	
	//根据房间号查询维修记录
			public List<NoticeEntity> getAllNoticByRoomid(int roomid){
				List<NoticeEntity> list = new ArrayList<NoticeEntity>();
				try {
					String sql="select * from five_notice where roomid=?";
					PreparedStatement ps = getCon().prepareStatement(sql);
					ps.setInt(1,roomid);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						RoomEntity room =new RoomDao().getRoomById(roomid);		
						list.add(new NoticeEntity(rs.getInt(1), rs.getString(2),rs.getInt(3) , room));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return list;
			} 
		@Test
		public void testGetAllByRoomId(){
			List<NoticeEntity> list=getAllNoticByRoomid(1);
			for (NoticeEntity notice : list) {
				System.out.println(notice.getNoticecontent()+" "+notice.getNoticeflag());
			}
		}
		
		//根据roomid查询所有
		public List<NoticeEntity> getAllByRoomid(int id){
			List<NoticeEntity> list=new ArrayList<NoticeEntity>();
			try {
				Connection con=Tools.getCon();
				String sql="select * from five_notice where roomid=?";
				PreparedStatement pst=con.prepareStatement(sql);
				pst.setInt(1, id);
				ResultSet rs=pst.executeQuery();
				if(rs.next()){
					RoomEntity room=new RoomDao().getRoomById(id);
					list.add(new NoticeEntity(rs.getInt(1),rs.getString(2),rs.getInt(3),room));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
		@Test
		public void testgetAllByRoomid(){
			List<NoticeEntity> list=getAllByRoomid(2);
			for (NoticeEntity no : list) {
				System.out.println(no.getNoticecontent());
			}
		}
}
