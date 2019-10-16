package com.ambow.service;

import java.util.List;

import org.junit.Test;

import com.ambow.dao.StudentMiddleDao;
import com.ambow.entity.AdminEntity;
import com.ambow.entity.RoomEntity;
import com.ambow.entity.StudentEntity;
import com.ambow.entity.StudentMiddleEntity;

public class StudentMiddleService {

	StudentMiddleDao dao=new StudentMiddleDao();
	//根据stuid查询Room的信息
	public StudentMiddleEntity getRoomBySid(String stuid){
		return dao.getRoomBySid(Integer.parseInt(stuid));
	}
	//根据sno查询sid
	public StudentEntity getSidBySno(String id){
		return dao.getSidBySno(Integer.parseInt(id));
	}
	
	//获取所有
	public List<StudentMiddleEntity> getAll(){
		return dao.getAll();
	}
	
	//添加middle
	public boolean addStudentMidle(int sid,int rid,int bid,int did) {
		boolean flag=false;
		if(dao.add(sid, rid, bid, did)!=0){
			 flag=true;
		}
		
		return flag;
	}
	
	public boolean updateStudentMiddleEntity(StudentMiddleEntity sme){
		boolean flag=false;
		if(dao.updateStudentMiddleEntity(sme)!=0){
			 flag=true;
		}
		
		return flag;
	}
	
	
	public void deleteStudentMiddleEntity(int id){
		dao.deleteStudentMiddleEntity(id);
			
		
	}
	
	
	//通过宿舍id查到这个宿舍住的学生
	 public List<StudentEntity> getAllStudentEntityByRoomId(String roomid){
		 return dao.getAllStudentEntityByRoomId(Integer.parseInt(roomid));
	 }
	 
	 
	 //通过学生查该学生住那个宿舍
	 public RoomEntity getStudentEntityByStuId(String stuid){
		 return dao.getStudentEntityByStuId(Integer.parseInt(stuid));
	 }
	 
	 @Test
	 public void test(){
		 System.out.println(getStudentEntityByStuId("1").getRoomno());
	 }
	 
	//通过楼查这个楼有哪些宿舍
	 public List<RoomEntity> getAllStudentEntityByBuildId(String buildid){
		 return dao.getAllStudentEntityByBuildId(Integer.parseInt(buildid));
	 }
	 
	 
	 // 通过系别查有多少学生
	 public List<StudentEntity> getAllStudentEntityByDeptId(String deptid){
		 return dao.getAllStudentEntityByDeptId(Integer.parseInt(deptid));
	 }
	 
	 
	 // 通过系别查该系在那栋楼有哪个宿舍
	 public List<StudentMiddleEntity> getAllRoomDeptByDeptId(String deptid){
		  return dao.getAllRoomDeptByDeptId(Integer.parseInt(deptid));
	 }
	 
	 
	  //判断该学生是否存在表中
	  public boolean getStudentMiddleEntityByStuid(String stuid){
		  boolean flag = false;
		  if(dao.getStudentMiddleEntityByStuid(Integer.parseInt(stuid))!=null){
			  flag=true;
		  }
		  return flag;
	  }
	  //批量入住
	  public void piLiangRuZhu(){
		  dao.fenpei();
	  }
	  //删除全部
	  public void deleteAll(){
		  dao.deleteAllStudentMiddle();
	  }//宿管删除全部
	  public void deleteOne(int year,int adminid){
		  dao.ert(year, adminid);
	  }
	  public List<StudentMiddleEntity> getAllByAdmin(AdminEntity admin){
		  return dao.getAllByAdmin(admin);
	  }
}
