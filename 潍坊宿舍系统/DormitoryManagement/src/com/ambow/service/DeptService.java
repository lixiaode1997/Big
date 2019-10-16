package com.ambow.service;

import java.util.List;

import com.ambow.dao.DeptDao;
import com.ambow.entity.DeptEntity;

public class DeptService {

	DeptDao dao=new DeptDao();
	
	//查询所有
	public List<DeptEntity> getAllDept(){
		return dao.getAllDept();
	}
	
	//根据id查询
	public DeptEntity getAllDeptById(String deptid){
		return dao.getDeptById(Integer.parseInt(deptid));
	}
	
	//名字去重
	public List<Object> getNameDept(){
		return dao.getNameDept();
	}
	//增加
	public int addDept(DeptEntity dept){
		return dao.addDept(dept);
	}
	
	//删除
	public void delDept(String deptid){
		dao.delDeptById(Integer.parseInt(deptid));
	}
	
	//修改
	public int updateDept(DeptEntity dept){
		return dao.updateDept(dept);
	}
}
