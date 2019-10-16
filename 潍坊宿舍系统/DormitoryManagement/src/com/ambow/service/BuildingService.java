package com.ambow.service;

import java.util.List;

import com.ambow.dao.BuildingDao;
import com.ambow.entity.BuildingEntity;

public class BuildingService {

	BuildingDao dao=new BuildingDao();
	
	//查询所有
	public List<BuildingEntity> getAllBuild(){
		return dao.getAllBuild();
	}
	
	//根据id查询
	public BuildingEntity getAllBuildById(String buildid){
		return dao.getBuildById(Integer.parseInt(buildid));
	}
	
	//增加
	public int addBuild(BuildingEntity build){
		return dao.addBuild(build);
	} 
	
	//删除
	public void delBuild(String buildid){
	dao.delBuildById(Integer.parseInt(buildid));	
	}
	
	//修改
	public int updateBuild(BuildingEntity build){
	return	dao.updateBuildById(build);
	}
}
