package com.ambow.entity;

public class StudentMiddleEntity {
	private int smid;
	private StudentEntity stuid;
	private RoomEntity roomid;
	private BuildingEntity buildid;
	private DeptEntity deptid;
	public int getSmid() {
		return smid;
	}
	public void setSmid(int smid) {
		this.smid = smid;
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
	public DeptEntity getDeptid() {
		return deptid;
	}
	public void setDeptid(DeptEntity deptid) {
		this.deptid = deptid;
	}
	public StudentMiddleEntity(StudentEntity stuid, RoomEntity roomid,
			BuildingEntity buildid, DeptEntity deptid) {
		super();
		this.stuid = stuid;
		this.roomid = roomid;
		this.buildid = buildid;
		this.deptid = deptid;
	}
	public StudentMiddleEntity() {
		super();
	}
	public StudentMiddleEntity(int smid, StudentEntity stuid,
			RoomEntity roomid, BuildingEntity buildid, DeptEntity deptid) {
		super();
		this.smid = smid;
		this.stuid = stuid;
		this.roomid = roomid;
		this.buildid = buildid;
		this.deptid = deptid;
	}
	public StudentMiddleEntity(RoomEntity roomid, BuildingEntity buildid) {
		super();
		this.roomid = roomid;
		this.buildid = buildid;
	}

	
}
