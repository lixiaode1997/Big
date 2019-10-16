package com.ambow.entity;

public class RoomEntity {
private int roomid;
private int roomno;
private int roombed;
private int roomnum;
private BuildingEntity buildid;
public int getRoomid() {
	return roomid;
}
public void setRoomid(int roomid) {
	this.roomid = roomid;
}
public int getRoomno() {
	return roomno;
}
public void setRoomno(int roomno) {
	this.roomno = roomno;
}
public int getRoombed() {
	return roombed;
}
public void setRoombed(int roombed) {
	this.roombed = roombed;
}
public int getRoomnum() {
	return roomnum;
}
public void setRoomnum(int roomnum) {
	this.roomnum = roomnum;
}
public BuildingEntity getBuildid() {
	return buildid;
}
public void setBuildid(BuildingEntity buildid) {
	this.buildid = buildid;
}
public RoomEntity(int roomid, int roomno, int roombed, int roomnum,
		BuildingEntity buildid) {
	super();
	this.roomid = roomid;
	this.roomno = roomno;
	this.roombed = roombed;
	this.roomnum = roomnum;
	this.buildid = buildid;
}
public RoomEntity() {
	super();
	// TODO Auto-generated constructor stub
}
public RoomEntity(int roomno, int roombed, int roomnum, BuildingEntity buildid) {
	super();
	this.roomno = roomno;
	this.roombed = roombed;
	this.roomnum = roomnum;
	this.buildid = buildid;
}
public RoomEntity(int roomid, int roomno, BuildingEntity buildid) {
	super();
	this.roomid = roomid;
	this.roomno = roomno;
	this.buildid = buildid;
}

}
