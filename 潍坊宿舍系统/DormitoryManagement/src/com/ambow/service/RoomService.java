package com.ambow.service;

import java.util.List;

import com.ambow.dao.RoomDao;
import com.ambow.entity.RoomEntity;

public class RoomService {

	RoomDao dao=new RoomDao();
	
	//查询所有
	public List<RoomEntity> getAllRoom(){
		return dao.getAllRoom();
	}
	
	//根据id查询
	public RoomEntity getAllRoomById(String roomid){
		return dao.getRoomById(Integer.parseInt(roomid));
	}
	
	//增加
	public int addRoom(RoomEntity room){
		return dao.addRoom(room);
	}
	
	//删除
	public void delRoom(String roomid){
		dao.delRoomById(Integer.parseInt(roomid));
	}
	
	//修改
	public int updateRoom(RoomEntity room){
		return dao.updateRoom(room);
	}
	
	//根据楼名查询所有信息
	public List<RoomEntity> getRoomBybuildName(String buildname){
		return dao.getRoomBybuildName(buildname);
	}
		
	//根据楼名查询所有信息
	public List<RoomEntity> getRoomByRoomNum(String roomnum){
		return dao.getRoomByRoomNum(Integer.parseInt(roomnum));
	}	

}
