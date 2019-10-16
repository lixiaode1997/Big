package com.ambow.service;

import java.util.List;

import com.ambow.dao.CostDao;
import com.ambow.dao.StudentCostDao;

import com.ambow.entity.CostEntity;



public class CostService {
	CostDao costDao = new CostDao();
	public boolean insertCost(CostEntity cost){
		boolean flag=false;
		if(costDao.insertCostEntity(cost)!=0){
			flag=true;
		}
		return flag;
	}
	
	public boolean updateCost(CostEntity cost){
		boolean flag=false;
		if(costDao.updateCostEntityById(cost)!=0){
			flag=true;
		}
		return flag;
	}
	
	
	public boolean delCost(String id){
		boolean flag=false;
		if(costDao.delCostEntityById(Integer.parseInt(id))!=0){
			flag=true;
		}
		return flag;
	}
	
	public List<CostEntity> getAllCostEntity(){
		List<CostEntity> list =costDao.getAllCost();
		return list;
	}
	
	public CostEntity getCostEntityById(String id){
		CostEntity cost = costDao.getAllCostById(Integer.parseInt(id));
		return cost;
	}
	
	public List<CostEntity> getStudentCostNum(int stuid){
		return costDao.getStudentCostNum(stuid);
	}
	
	public List<Integer> getAlreadyCost(String stuid,List<CostEntity> list){
		return costDao.getAlreadyCost(Integer.parseInt(stuid), list);
	}
	
	
	public boolean addStudentCost(String stuid,String costid){
		boolean flag=false;
		if(new StudentCostDao().addStudentCost(Integer.parseInt(stuid),Integer.parseInt(costid))!=0){
			flag=true;
		}
		return flag;
	}
	
	

	public int isCostAllOver(int stuid){
		return costDao.isCostAllOver(stuid);
	}
}
