package com.ambow.entity;

import java.util.Date;

public class StudentEntity {
private int stuid;
private int stuno;
private String stuname;
private String stupwd;
private int stusex;
private String studept;
private Date stustartime;
private int stuyear;
private int stupay;
public StudentEntity(int stuid, int stuno, String stuname, String stupwd, int stusex, String studept, Date stustartime,
		int stuyear, int stupay) {
	super();
	this.stuid = stuid;
	this.stuno = stuno;
	this.stuname = stuname;
	this.stupwd = stupwd;
	this.stusex = stusex;
	this.studept = studept;
	this.stustartime = stustartime;
	this.stuyear = stuyear;
	this.stupay = stupay;
}



public StudentEntity(int stuno, String stuname, String stupwd, int stusex, String studept, Date stustartime,
		int stuyear, int stupay) {
	super();
	this.stuno = stuno;
	this.stuname = stuname;
	this.stupwd = stupwd;
	this.stusex = stusex;
	this.studept = studept;
	this.stustartime = stustartime;
	this.stuyear = stuyear;
	this.stupay = stupay;
}



public StudentEntity() {
	super();
}



public int getStuid() {
	return stuid;
}
public void setStuid(int stuid) {
	this.stuid = stuid;
}
public int getStuno() {
	return stuno;
}
public void setStuno(int stuno) {
	this.stuno = stuno;
}
public String getStuname() {
	return stuname;
}
public void setStuname(String stuname) {
	this.stuname = stuname;
}
public String getStupwd() {
	return stupwd;
}
public void setStupwd(String stupwd) {
	this.stupwd = stupwd;
}
public int getStusex() {
	return stusex;
}
public void setStusex(int stusex) {
	this.stusex = stusex;
}
public String getStudept() {
	return studept;
}
public void setStudept(String studept) {
	this.studept = studept;
}
public Date getStustartime() {
	return stustartime;
}
public void setStustartime(Date stustartime) {
	this.stustartime = stustartime;
}
public int getStuyear() {
	return stuyear;
}
public void setStuyear(int stuyear) {
	this.stuyear = stuyear;
}
public int getStupay() {
	return stupay;
}
public void setStupay(int stupay) {
	this.stupay = stupay;
}




}
