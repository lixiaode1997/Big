package com.ambow.entity;

public class MiddleEntity {
private int mid;
private StudentEntity stuid;
private RoomEntity roomid;
private BuildingEntity buildid;
private int flag;
public int getMid() {
	return mid;
}
public void setMid(int mid) {
	this.mid = mid;
}
public StudentEntity getStuid() {
	return stuid;
}
public void setStuid(StudentEntity stuid) {
	this.stuid = stuid;
}
public RoomEntity getRoomid() {
	return roomid;
}
public void setRoomid(RoomEntity roomid) {
	this.roomid = roomid;
}
public BuildingEntity getBuildid() {
	return buildid;
}
public void setBuildid(BuildingEntity buildid) {
	this.buildid = buildid;
}
public int getFlag() {
	return flag;
}
public void setFlag(int flag) {
	this.flag = flag;
}
public MiddleEntity(int mid, StudentEntity stuid, RoomEntity roomid,
		BuildingEntity buildid, int flag) {
	super();
	this.mid = mid;
	this.stuid = stuid;
	this.roomid = roomid;
	this.buildid = buildid;
	this.flag = flag;
}
public MiddleEntity() {
	super();
	// TODO Auto-generated constructor stub
}
public MiddleEntity(StudentEntity stuid, RoomEntity roomid,
		BuildingEntity buildid, int flag) {
	super();
	this.stuid = stuid;
	this.roomid = roomid;
	this.buildid = buildid;
	this.flag = flag;
}

}
