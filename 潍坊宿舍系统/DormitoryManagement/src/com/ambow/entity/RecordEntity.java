package com.ambow.entity;

import java.util.Date;

public class RecordEntity {
private int recordid;
private Date leavetime;
private Date backtime;
private StudentEntity stuid;
public RecordEntity(int recordid, Date leavetime, Date backtime,
		StudentEntity stuid) {
	super();
	this.recordid = recordid;
	this.leavetime = leavetime;
	this.backtime = backtime;
	this.stuid = stuid;
}
public RecordEntity(Date leavetime, Date backtime, StudentEntity stuid) {
	super();
	this.leavetime = leavetime;
	this.backtime = backtime;
	this.stuid = stuid;
}
public RecordEntity() {
	super();
}
public int getRecordid() {
	return recordid;
}
public void setRecordid(int recordid) {
	this.recordid = recordid;
}
public Date getLeavetime() {
	return leavetime;
}
public void setLeavetime(Date leavetime) {
	this.leavetime = leavetime;
}
public Date getBacktime() {
	return backtime;
}
public void setBacktime(Date backtime) {
	this.backtime = backtime;
}
public StudentEntity getStuid() {
	return stuid;
}
public void setStuid(StudentEntity stuid) {
	this.stuid = stuid;
}


}
