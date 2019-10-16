package com.ambow.entity;

public class AdminEntity {
private int adminid;
private String adminname;
private String adminpwd;
private BuildingEntity buildid;
public AdminEntity(int adminid, String adminname, String adminpwd,
		BuildingEntity buildid) {
	super();
	this.adminid = adminid;
	this.adminname = adminname;
	this.adminpwd = adminpwd;
	this.buildid = buildid;
}
public AdminEntity(String adminname, String adminpwd, BuildingEntity buildid) {
	super();
	this.adminname = adminname;
	this.adminpwd = adminpwd;
	this.buildid = buildid;
}

public AdminEntity() {
	super();
}

public int getAdminid() {
	return adminid;
}
public void setAdminid(int adminid) {
	this.adminid = adminid;
}
public String getAdminname() {
	return adminname;
}
public void setAdminname(String adminname) {
	this.adminname = adminname;
}
public String getAdminpwd() {
	return adminpwd;
}
public void setAdminpwd(String adminpwd) {
	this.adminpwd = adminpwd;
}
public BuildingEntity getBuildid() {
	return buildid;
}
public void setBuildid(BuildingEntity buildid) {
	this.buildid = buildid;
}


}
