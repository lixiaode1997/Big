package com.ambow.service;


import java.util.List;

import com.ambow.dao.StudentDao;
import com.ambow.entity.StudentEntity;

public class StudentService {

	StudentDao dao=new StudentDao();
	
	public StudentEntity login(String name,String pwd){
		return dao.login(Integer.parseInt(name), pwd);
	}
	//根据year查询入住的学生
	public List<StudentEntity> getStudentbyYear(int year,int adminid){
		return dao.getRuZhuByYear(year,adminid);
	}
	
	
	//id查询所有信息
	public StudentEntity getAllById(String id){
		return dao.getStudentById(Integer.parseInt(id));
	}
	
	//学生入住没入住返回int
	public int getOneRuZhuMeiRuZhu(int id){
		return dao.getOneRuZhuMeiRuZhu(id);
	}

	
/*	//分配
	/*public void fenpei(int dept,int sex,int buildid){
		dao.piliangfenpei(dept, sex,buildid);
	}*/
	
	//得到所有未分配的人
	/*public List<StudentEntity>  nullStudent(int dept,int sex){
		return dao.getAllNullRoomStudent(dept, sex);
	}*/
	
	//查询所有
	public List<StudentEntity> getAll(){
		return dao.getAllStudent();
	}
	//查询有没有名字
	public int haveName(String name){
		return dao.haveName(name);
	}
	
	//查询所有入住的学生
	public List<StudentEntity> getRuZhustudent(){
		return dao.getWeiRuZhuStudent();
	}

	//查询没有入住的学生
	public List<StudentEntity> getWeiRuZhuStudent(){
		return dao.getWeiRuZhuStudent();
	}
	//根据学号查询学生
	public StudentEntity getStuBuSno(String stuno){
		return dao.getStudentByStuno(Integer.parseInt(stuno));
	}

	
	public StudentEntity getStudentEntityById(String stu){
		return dao.getStudentById(Integer.parseInt(stu));
	}
	
	/*public int insertAll(List<BuildingBean> list) {
		int b=0;
		for(int i=0;i<list.size();i++){
			b= bbd.buildingAdd(list.get(i));
		}
		return b;
	}*/

	
	public int addStudent(List<StudentEntity> list) {
		int b=0;
		for(int i=0;i<list.size();i++){
			b=dao.addStudent(list.get(i));
		}
		return b;
		
		
	}
}
