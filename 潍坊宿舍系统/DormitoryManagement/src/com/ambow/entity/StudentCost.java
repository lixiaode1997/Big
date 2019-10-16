package com.ambow.entity;

public class StudentCost {
	private int scid;
	private int stuid;
	private int costid;
	public int getScid() {
		return scid;
	}
	public void setScid(int scid) {
		this.scid = scid;
	}
	public int getStuid() {
		return stuid;
	}
	public void setStuid(int stuid) {
		this.stuid = stuid;
	}
	public int getCostid() {
		return costid;
	}
	public void setCostid(int costid) {
		this.costid = costid;
	}
	public StudentCost(int scid, int stuid, int costid) {
		super();
		this.scid = scid;
		this.stuid = stuid;
		this.costid = costid;
	}
	public StudentCost(int stuid, int costid) {
		super();
		this.stuid = stuid;
		this.costid = costid;
	}
	public StudentCost() {
		super();
	}
	
}
