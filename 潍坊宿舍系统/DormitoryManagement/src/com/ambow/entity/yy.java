package com.ambow.entity;

public class yy {
private int s_rid;
private int sid;
private int rid;
private int bid;
private int did;
public int getS_rid() {
	return s_rid;
}
public void setS_rid(int s_rid) {
	this.s_rid = s_rid;
}
public int getSid() {
	return sid;
}
public void setSid(int sid) {
	this.sid = sid;
}
public int getRid() {
	return rid;
}
public void setRid(int rid) {
	this.rid = rid;
}
public int getBid() {
	return bid;
}
public void setBid(int bid) {
	this.bid = bid;
}
public int getDid() {
	return did;
}
public void setDid(int did) {
	this.did = did;
}
public yy(int s_rid, int sid, int rid, int bid, int did) {
	super();
	this.s_rid = s_rid;
	this.sid = sid;
	this.rid = rid;
	this.bid = bid;
	this.did = did;
}
public yy() {
	super();
	// TODO Auto-generated constructor stub
}
public yy(int sid, int rid, int bid, int did) {
	super();
	this.sid = sid;
	this.rid = rid;
	this.bid = bid;
	this.did = did;
}

}
