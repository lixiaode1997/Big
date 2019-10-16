package com.ambow.service;

import java.util.List;

import com.ambow.dao.BoradDao;
import com.ambow.entity.BoradEntity;
import com.ambow.entity.BuildingEntity;

public class BoradService {

	BoradDao dao=new BoradDao();
	
	//查询所有
	public List<BoradEntity> getAll(){
		return dao.getAllBorad();
	}
	
	//根据id查询
	public BoradEntity getById(String id){
		return dao.getBoradById(Integer.parseInt(id));
	}
	
	//增加
	public int add(BoradEntity borad){
		return dao.addBorad(borad);
	}
	
	//删除
	public void delBorad(String boradid){
		dao.delBorddById(Integer.parseInt(boradid));	
		}
	
	//修改
	public int updateBorad(BoradEntity borad){
		return	dao.updateBoradById(borad);
		}
}
