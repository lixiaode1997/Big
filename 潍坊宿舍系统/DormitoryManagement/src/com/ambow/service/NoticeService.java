package com.ambow.service;

import java.util.List;

import com.ambow.dao.NoticeDao;
import com.ambow.entity.NoticeEntity;

public class NoticeService {
	NoticeDao noticeDao = new NoticeDao();
	public int insertNotice(NoticeEntity notice){
		return noticeDao.insertNoticeEntity(notice);
	}
	
	public int delNotice(String noticeid){
		return noticeDao.delNoticeEntityById(Integer.parseInt(noticeid));
	}
	
	public int updateNotice(NoticeEntity notice){
		return noticeDao.updateNoticeEntityById(notice);
	}
	
	public List<NoticeEntity> getAllNotice(){
		return noticeDao.getAllNotic();
	}
	
	public NoticeEntity getNoticeById(String noticeid){
		return noticeDao.getAllNoticById(Integer.parseInt(noticeid));
	}
	
	//根据id修改flag
	public int updateFlag(String id,String flag){
		return noticeDao.updateFlagById(Integer.parseInt(id), Integer.parseInt(flag));
	}
	
	//根据flag查询所有信息
	public List<NoticeEntity> getAllNoticeByFlag(String flag){
		return noticeDao.getAllNoticByFlag(Integer.parseInt(flag));
	}
	
	//根据roomid查询信息
	public List<NoticeEntity> getAllBYRoomid(String roomid){
		return noticeDao.getAllNoticByRoomid(Integer.parseInt(roomid));
	}
	
	//根据roomid查询notice
	public List<NoticeEntity> getnoticeBYRoomid(int id){
		return noticeDao.getAllNoticByRoomid(id);
	}
}
