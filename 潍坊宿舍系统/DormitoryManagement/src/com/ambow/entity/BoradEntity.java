package com.ambow.entity;

import java.util.Date;

public class BoradEntity {

	private int boradId;
	private String boradCOntent;
	private Date boradDate;
	private AdminEntity admin;
	public BoradEntity(int boradId, String boradCOntent, Date boradDate, AdminEntity admin) {
		super();
		this.boradId = boradId;
		this.boradCOntent = boradCOntent;
		this.boradDate = boradDate;
		this.admin = admin;
	}
	public BoradEntity(String boradCOntent, Date boradDate, AdminEntity admin) {
		super();
		this.boradCOntent = boradCOntent;
		this.boradDate = boradDate;
		this.admin = admin;
	}
	public BoradEntity() {
		super();
	}
	public int getBoradId() {
		return boradId;
	}
	public void setBoradId(int boradId) {
		this.boradId = boradId;
	}
	public String getBoradCOntent() {
		return boradCOntent;
	}
	public void setBoradCOntent(String boradCOntent) {
		this.boradCOntent = boradCOntent;
	}
	public Date getBoradDate() {
		return boradDate;
	}
	public void setBoradDate(Date boradDate) {
		this.boradDate = boradDate;
	}
	public AdminEntity getAdmin() {
		return admin;
	}
	public void setAdmin(AdminEntity admin) {
		this.admin = admin;
	}
	
	
}
