package com.ambow.entity;

public class CostEntity {
private int costid;
private int costroom;
private int costwater;
public int getCostid() {
	return costid;
}
public void setCostid(int costid) {
	this.costid = costid;
}
public int getCostroom() {
	return costroom;
}
public void setCostroom(int costroom) {
	this.costroom = costroom;
}
public int getCostwater() {
	return costwater;
}
public void setCostwater(int costwater) {
	this.costwater = costwater;
}
public CostEntity(int costid, int costroom, int costwater) {
	super();
	this.costid = costid;
	this.costroom = costroom;
	this.costwater = costwater;

}

public CostEntity(int costroom, int costwater) {
	super();
	this.costroom = costroom;
	this.costwater = costwater;
}
public CostEntity() {
	super();
}


}
