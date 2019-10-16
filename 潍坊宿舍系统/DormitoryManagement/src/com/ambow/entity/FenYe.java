package com.ambow.entity;
import java.util.List;

public class FenYe {

	private int currentPage; 
	private int pageSize = 3; 
	private int recordCount; 
	private int pageCount; 
	private int pageIndex;
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}
	
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	
	public int getPageCount() {
		return (recordCount % pageSize == 0)?recordCount / pageSize:recordCount / pageSize + 1;
	}
	
	public int getPageIndex() {
		return (currentPage-1)*pageSize;
	}
}
