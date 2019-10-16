package com.ambow.entity;

public class NoticeEntity {
private int noticeid;
private String noticecontent;
private int noticeflag;
private RoomEntity roomid;
public int getNoticeid() {
	return noticeid;
}
public void setNoticeid(int noticeid) {
	this.noticeid = noticeid;
}
public String getNoticecontent() {
	return noticecontent;
}
public void setNoticecontent(String noticecontent) {
	this.noticecontent = noticecontent;
}
public int getNoticeflag() {
	return noticeflag;
}
public void setNoticeflag(int noticeflag) {
	this.noticeflag = noticeflag;
}
public RoomEntity getRoomid() {
	return roomid;
}
public void setRoomid(RoomEntity roomid) {
	this.roomid = roomid;
}
public NoticeEntity() {
	super();
	// TODO Auto-generated constructor stub
}
public NoticeEntity(int noticeid, String noticecontent, int noticeflag,
		RoomEntity roomid) {
	super();
	this.noticeid = noticeid;
	this.noticecontent = noticecontent;
	this.noticeflag = noticeflag;
	this.roomid = roomid;
}
public NoticeEntity(String noticecontent, int noticeflag, RoomEntity roomid) {
	super();
	this.noticecontent = noticecontent;
	this.noticeflag = noticeflag;
	this.roomid = roomid;
}

}
