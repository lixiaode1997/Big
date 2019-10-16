package com.ambow.service;

import java.util.List;

import com.ambow.dao.AdminDao;
import com.ambow.entity.AdminEntity;

public class AdminService {
	AdminDao dao=new AdminDao();
     //通过Id获得单条
	public AdminEntity getAdminById(int adminid){
		
	return dao.getAdminById(adminid);
	
	}
	
	//获得所有
	public List<AdminEntity> getAllAdmin(){
		
		return dao.getAllAdmin();
		
	}
	
	//增加宿管
	public int addAdmin(AdminEntity admin){
		
		return dao.addAdmin(admin);
	}
	
	
	//删除宿管
	public int deleteAdmin(String adminid){
		
		return dao.deleteAdmin(Integer.parseInt(adminid));
	}
	
	
	//修改宿管
	public int updateAdmin(AdminEntity admin) {
		System.out.println("++++++++++++++++++++++++++++");
		return dao.updateAdmin(admin);
	}
	
	//登录
	public AdminEntity login(String adminname,String adminpwd){
		
		return dao.login(adminname, adminpwd);

	}
	
	
}
