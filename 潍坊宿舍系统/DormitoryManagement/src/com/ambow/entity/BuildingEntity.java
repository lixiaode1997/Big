package com.ambow.entity;

public class BuildingEntity {
	private int buildid;
	private String buildname;
	private int buildsex;
	public BuildingEntity(int buildid, String buildname, int buildsex) {
		super();
		this.buildid = buildid;
		this.buildname = buildname;
		this.buildsex = buildsex;
	}
	public BuildingEntity(String buildname, int buildsex) {
		super();
		this.buildname = buildname;
		this.buildsex = buildsex;
	}
	public BuildingEntity() {
		super();
	}
	public int getBuildid() {
		return buildid;
	}
	public void setBuildid(int buildid) {
		this.buildid = buildid;
	}
	public String getBuildname() {
		return buildname;
	}
	public void setBuildname(String buildname) {
		this.buildname = buildname;
	}
	public int getBuildsex() {
		return buildsex;
	}
	public void setBuildsex(int buildsex) {
		this.buildsex = buildsex;
	}
	
	
}
