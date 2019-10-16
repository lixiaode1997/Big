package com.ambow.entity;

public class DeptEntity {
private int deptid;
private String deptname;
private BuildingEntity buildid;
public int getDeptid() {
	return deptid;
}
public void setDeptid(int deptid) {
	this.deptid = deptid;
}
public String getDeptname() {
	return deptname;
}
public void setDeptname(String deptname) {
	this.deptname = deptname;
}
public BuildingEntity getBuildid() {
	return buildid;
}
public void setBuildid(BuildingEntity buildid) {
	this.buildid = buildid;
}
public DeptEntity(int deptid, String deptname, BuildingEntity buildid) {
	super();
	this.deptid = deptid;
	this.deptname = deptname;
	this.buildid = buildid;
}
public DeptEntity() {
	super();
	// TODO Auto-generated constructor stub
}
public DeptEntity(String deptname, BuildingEntity buildid) {
	super();
	this.deptname = deptname;
	this.buildid = buildid;
}

}
