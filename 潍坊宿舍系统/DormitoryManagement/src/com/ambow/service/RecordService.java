package com.ambow.service;

import java.util.List;

import com.ambow.dao.RecordDao;
import com.ambow.entity.FenYe;
import com.ambow.entity.RecordEntity;

public class RecordService {
	RecordDao recordDao = new RecordDao();
	
	public List<RecordEntity> getAllRecordByPage(FenYe currentPage){
		return recordDao.getAllBigByPage(currentPage);
	}
	
	public List<RecordEntity> getAllRecord(){
		return recordDao.getAllRecord();
	}

	public void delRecordById(String recordid) {
		recordDao.delRecordById(Integer.parseInt(recordid));
	}

	public void addRecord(RecordEntity record) {
		recordDao.addRecord(record);
	}

	public String getTime() {
		return recordDao.time();
	}
	
	//excel导入
	  public int excelBuiding(RecordEntity recordEntity){
	   return recordDao.excelBuiding(recordEntity);
	  }
	
}
