package com.ambow.entity;

public class SuperEntity {
private int superid;
private String supername;
private String superpwd;
public int getSuperid() {
	return superid;
}
public void setSuperid(int superid) {
	this.superid = superid;
}
public String getSupername() {
	return supername;
}
public void setSupername(String supername) {
	this.supername = supername;
}
public String getSuperpwd() {
	return superpwd;
}
public void setSuperpwd(String superpwd) {
	this.superpwd = superpwd;
}
public SuperEntity(int superid, String supername, String superpwd) {
	super();
	this.superid = superid;
	this.supername = supername;
	this.superpwd = superpwd;
}
public SuperEntity() {
	super();
	// TODO Auto-generated constructor stub
}
public SuperEntity(String supername, String superpwd) {
	super();
	this.supername = supername;
	this.superpwd = superpwd;
}


}
