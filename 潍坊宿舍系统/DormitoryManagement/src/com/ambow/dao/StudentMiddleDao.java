package com.ambow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ambow.entity.AdminEntity;
import com.ambow.entity.BuildingEntity;
import com.ambow.entity.DeptEntity;
import com.ambow.entity.MiddleEntity;
import com.ambow.entity.RoomEntity;
import com.ambow.entity.StudentEntity;
import com.ambow.entity.StudentMiddleEntity;
import com.ambow.util.Tools;

public class StudentMiddleDao {
	
	
	
	Connection con=Tools.getCon();
	public int add(int sid,int rid,int bid,int did){
		int res=0;
		try {
			String sql="insert into five_middle values(null,?,?,?,?)";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, sid);
			pst.setInt(2, rid);
			pst.setInt(3, bid);
			pst.setInt(4, did);
			res=pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
		
	}
	  @Test
	  public void test1(){
		  int a=add(2, 3, 1, 4);
		  System.out.println(a);
	  }
	  
	  //判断该学生是否存在表中
	  public StudentMiddleEntity getStudentMiddleEntityByStuid(int stuid){
		  StudentMiddleEntity a=null;
			try {
				String sql="select * from five_middle where stuid=?";
				PreparedStatement pst=con.prepareStatement(sql);
				pst.setInt(1,stuid);
				ResultSet res=pst.executeQuery();
				if(res.next()){
					a=new StudentMiddleEntity(res.getInt(1),new StudentDao().getStudentById(res.getInt(2)),new RoomDao().getRoomById(res.getInt(3)),new BuildingDao().getBuildById(res.getInt(4)),new DeptDao().getDeptById(res.getInt(5)));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return a;
			
		}
		  @Test
		  public void test10(){
			
					System.out.println(getStudentMiddleEntityByStuid(2).getRoomid().getRoomid());
			
		  }
	  
	  
	  public List<StudentMiddleEntity> getAll(){
		  List<StudentMiddleEntity> list=new ArrayList<StudentMiddleEntity>();
			try {
				String sql="select * from five_middle";
				PreparedStatement pst=con.prepareStatement(sql);
				
				ResultSet res=pst.executeQuery();
				while(res.next()){
					StudentMiddleEntity a=new StudentMiddleEntity(res.getInt(1),new StudentDao().getStudentById(res.getInt(2)),new RoomDao().getRoomById(res.getInt(3)),new BuildingDao().getBuildById(res.getInt(4)),new DeptDao().getDeptById(res.getInt(5)));
					list.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
			
		}
		  @Test
		  public void test3(){
			  List<StudentMiddleEntity> a=getAll();
				for (StudentMiddleEntity item : a) {
					System.out.println(item.getBuildid());
				}
		  }
		  
		  
		  public List<StudentMiddleEntity> getAllByAdmin(AdminEntity admin){
			  List<StudentMiddleEntity> list=new ArrayList<StudentMiddleEntity>();
			  
				try {
					String sql="select * from five_middle where buildid=?";
					PreparedStatement pst=con.prepareStatement(sql);
					pst.setInt(1,admin.getBuildid().getBuildid());
					ResultSet res=pst.executeQuery();
					while(res.next()){
						StudentMiddleEntity a=new StudentMiddleEntity(res.getInt(1),new StudentDao().getStudentById(res.getInt(2)),new RoomDao().getRoomById(res.getInt(3)),new BuildingDao().getBuildById(res.getInt(4)),new DeptDao().getDeptById(res.getInt(5)));
						list.add(a);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return list;
				
			}
			  @Test
			  public void test2(){
				  List<StudentMiddleEntity> a=getAllByAdmin(new AdminDao().getAdminById(1));
					for (StudentMiddleEntity item : a) {
						System.out.println(item.getStuid().getStuname());
					}
			  }
	  
		  public int updateStudentMiddleEntity(StudentMiddleEntity sme){
				try {
					String sql="update five_middle set stuid=?,roomid=?,buildid=?,deptid=? where middleid=?";
					PreparedStatement pst=con.prepareStatement(sql);
					pst.setInt(1, sme.getStuid().getStuid());
					pst.setInt(2, sme.getRoomid().getRoomid());
					pst.setInt(3, sme.getBuildid().getBuildid());
					pst.setInt(4, sme.getDeptid().getDeptid());
					pst.setInt(5, sme.getSmid());
					return pst.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			return 0;
			}
			
			@Test
			public void testUpdate(){
				StudentMiddleEntity sme = new StudentMiddleEntity(1,new StudentDao().getStudentById(2),new RoomDao().getRoomById(3),new BuildingDao().getBuildById(1),new DeptDao().getDeptById(2));
				System.out.println(updateStudentMiddleEntity(sme));
				
			}
		  //根据id删除
			public int deleteStudentMiddleEntity(int smeid){
				String sql ="delete from five_middle where stuid=?";
				try {
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1,smeid);
					return ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return 0;
			}
			
			
			@Test
			public void testdelete(){
				System.out.println(deleteStudentMiddleEntity(3));
			}
			
		//一键删除所有
			public int deleteAllStudentMiddle(){
				String sql ="delete from five_middle ";
				try {
					PreparedStatement ps = con.prepareStatement(sql);
					return ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return 0;
			}
			
			
	/*	  public void piliangfenpei(int dept, int sex, int buildid) {
				//List<StudentEntity> list = getAllStudent();
				//int allnum = list.size();// 总人数
				List<StudentEntity> list =new StudentDao().getAllStudent();
				List<StudentMiddleEntity> list1 =getAll();
				int allnum = list.size();// 总人数
				int xuyaosusheshuliang ;// 需要的房间数
				if((allnum%8)!=0){
					xuyaosusheshuliang=(allnum/8)+1;
				}else{
					xuyaosusheshuliang=allnum/8;
				}
				// List<RoomEntity> roomnum=new RoomDao().getAllNullRoom();//所有空宿舍
				// int susheshuliang=roomnum.size();//剩余的空宿舍数量 已分配宿舍数×8=已分配的人数
				List<RoomEntity> roomnum = new RoomDao().getAllNullRoom(buildid);// 得到所有空宿舍
				//System.out.println("roomnum长度："+roomnum.size());
				for (int i = 0; i < xuyaosusheshuliang; i++) {
					System.out.println("需要几个宿舍："+xuyaosusheshuliang);
					System.out.println("i="+i);
					List<StudentEntity> list1 = getAllNullRoomStudent(dept, sex);// 待分配宿舍的人
					System.out.println("----------------------" + list1.size());
					//List<RoomEntity> roomnum = new RoomDao().getAllNullRoom(buildid);// 得到所有空宿舍
					System.out.println("房间号："+roomnum.get(i).getRoomid());
					System.out.println("************");
					for (int j = 0; j <list1.size()&&j<8; j++) {
						System.out.println("j="+j);
						System.out.println("ji="+i);
						
						System.out.println("宿舍：" + roomnum.get(i).getRoomid());
						
						System.out.println("人：" + list1.get(j).getStuid());
						System.out.println("**************************************************************");
						ruzhu(roomnum.get(i).getRoomid(), list1.get(j).getStuid());
						new RoomDao().updateRoomnum(roomnum.get(i).getRoomid());

					}
					if(list1.size()==0){
						break;
					}
				}
			}
	*/
			//删除一栋楼内的学生
			public void ert(int year,int adminid){
				List<StudentEntity> list=new StudentDao().getRuZhuByYear(year, adminid);
				System.out.println("要退宿的有"+list.size()+"人");
				for (int i = 0; i < list.size(); i++) {
				//StudentMiddleEntity list2=getStudentMiddleEntityByStuid(list.get(i).getStuid());
				System.out.println(list.get(i).getStuid());
					deleteStudentMiddleEntity(list.get(i).getStuid());
				}
			}
			
			@Test
			public void uiwe(){
				ert(2020,4);
			}
			
			
			
			
			
			
			
			
	  
			
			//通过宿舍id查到这个宿舍住的学生
			 public List<StudentEntity> getAllStudentEntityByRoomId(int roomid){
				  List<StudentEntity> list=new ArrayList<StudentEntity>();
					try {
						String sql = "select stuid from five_middle where roomid=?";
						PreparedStatement pst=con.prepareStatement(sql);
						  pst.setInt(1,roomid);
						ResultSet res=pst.executeQuery();
						while(res.next()){
							StudentEntity a=new StudentDao().getStudentById(res.getInt(1));
							list.add(a);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return list;
					
				}
		
		
		  @Test
		  public void test5(){
			  for(StudentEntity item:getAllStudentEntityByRoomId(3)){
				  System.out.println(item.getStuid());
			  }
		  }
			
		  
		  
		  //通过学生查该学生住那个宿舍
			 public RoomEntity getStudentEntityByStuId(int stuid){
				 RoomEntity room  = null;
					try {
						String sql = "select roomid from five_middle where stuid=?";
						PreparedStatement pst=con.prepareStatement(sql);
						  pst.setInt(1,stuid);
						ResultSet res=pst.executeQuery();
						if(res.next()){
							room=new RoomDao().getRoomById(res.getInt(1));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return room;
					
				}
		  @Test
		  public void test6(){
				  System.out.println(getStudentEntityByStuId(10).getRoomno());
			  
			  }
		  
		  
		  
			
		  //通过楼查这个楼有哪些宿舍
			 public List<RoomEntity> getAllStudentEntityByBuildId(int buildid){
				  List<RoomEntity> list=new ArrayList<RoomEntity>();
					try {
						String sql = "select roomid from five_middle where buildid=?";
						PreparedStatement pst=con.prepareStatement(sql);
						  pst.setInt(1,buildid);
						ResultSet res=pst.executeQuery();
						while(res.next()){
							RoomEntity a=new RoomDao().getRoomById(res.getInt(1));
							list.add(a);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return list;
					
				}
			 
			 
		  @Test
		  public void test7(){
			  for(RoomEntity item:getAllStudentEntityByBuildId(1)){
				  System.out.println(item.getRoomid());
			  }	 
			 
		  }
			
		  // 通过系别查有多少学生
			 public List<StudentEntity> getAllStudentEntityByDeptId(int deptid){
				  List<StudentEntity> list=new ArrayList<StudentEntity>();
					try {
						String sql = "select stuid from five_middle where deptid=?";
						PreparedStatement pst=con.prepareStatement(sql);
						  pst.setInt(1,deptid);
						ResultSet res=pst.executeQuery();
						while(res.next()){
							StudentEntity a=new StudentDao().getStudentById(res.getInt(1));
							list.add(a);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return list;
					
				}
		  
		  @Test
		  public void test8(){
			  for(StudentEntity item:getAllStudentEntityByDeptId(2)){
				  System.out.println(item.getStuname());
			  }		  }
		  
		  
		  // 通过系别查该系在那栋楼有哪个宿舍
			 public List<StudentMiddleEntity> getAllRoomDeptByDeptId(int deptid){
				  List<StudentMiddleEntity> list=new ArrayList<StudentMiddleEntity>();
					try {
						String sql = "select roomid,buildid from five_middle where deptid=?";
						PreparedStatement pst=con.prepareStatement(sql);
						  pst.setInt(1,deptid);
						ResultSet res=pst.executeQuery();
						while(res.next()){
							RoomEntity a=new RoomDao().getRoomById(res.getInt(1));
							StudentMiddleEntity sme = new StudentMiddleEntity(new RoomDao().getRoomById(res.getInt(1)),new BuildingDao().getBuildById(res.getInt(2)));
							list.add(sme);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return list;
					
				}
		  
		  @Test
		  public void test9(){
			  for(StudentMiddleEntity item:getAllRoomDeptByDeptId(2)){
				  System.out.println(item.getBuildid().getBuildname()+"  "+item.getRoomid().getRoomno());
			  }		
		  }
		  //根据学号查询id
		  public StudentEntity getSidBySno(int sno){
			  StudentEntity stu=null;
			  try {
				String sql="select * from five_studentmanager where stuno=?";
				  Connection con=Tools.getCon();
				  PreparedStatement pst=con.prepareStatement(sql);
				  pst.setInt(1, sno);
				  ResultSet rs=pst.executeQuery();
				  if(rs.next()){
				  stu=new StudentEntity(rs.getInt(1), sno, rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getDate(7), rs.getInt(8), rs.getInt(9));
				  }
				  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			  return stu;
		  }
		  
		  @Test
		  public void testgetSidBySno(){
			  StudentEntity sru=getSidBySno(20160101);
			  System.out.println(sru.getStuid());
		  }
	//根据sid查询Room的信息
			public StudentMiddleEntity getRoomBySid(int id){
				StudentMiddleEntity middle=null;
				try {
					String sql="select * from five_middle where stuid=?";
					PreparedStatement pst=con.prepareStatement(sql);
					pst.setInt(1, id);
					ResultSet rs=pst.executeQuery();
					if(rs.next()){
						BuildingEntity build=new BuildingDao().getBuildById(rs.getInt(5));
						StudentEntity stu=new StudentDao().getStudentById(id);
						RoomEntity room=new RoomDao().getRoomById(rs.getInt(3));
						DeptEntity dept=new DeptDao().getDeptById(rs.getInt(4));
						middle=new StudentMiddleEntity(rs.getInt(1),stu,room,build,dept);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return middle;
			}
			
			@Test
			public void testGetRoomBySid(){
			StudentMiddleEntity stu=getRoomBySid(4);
			System.out.println(stu.getRoomid().getRoomno());
			}
			
			public List<StudentMiddleEntity> getAllAll(){
				List<StudentMiddleEntity> list=new ArrayList<StudentMiddleEntity>();
				
				try {
					String sql="select * from five_middle";
					PreparedStatement pst=con.prepareStatement(sql);
					ResultSet rs=pst.executeQuery();
					while (rs.next()) {
						StudentEntity stu=new StudentDao().getStudentById(rs.getInt(2));
						RoomEntity room=new RoomDao().getRoomById(rs.getInt(3));
						BuildingEntity build=new BuildingDao().getBuildById(rs.getInt(4));
						DeptEntity dept=new DeptDao().getDeptById(rs.getInt(5));
						StudentMiddleEntity stu1=new StudentMiddleEntity(rs.getInt(1), stu, room, build, dept);
						list.add(stu1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return list;
			}
			@Test
			public void testGetAllAll(){
				List<StudentMiddleEntity> list=getAllAll();
				for (StudentMiddleEntity stu : list) {
					System.out.println(stu.getSmid());
				}
			}
			
			
			
			public int addMidd(int stuid, int roomid, int dept) {
				try {
					String sql = "insert into five_middle values(null,?,?,?,?)";
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setInt(1, stuid);
					pst.setInt(2, roomid);
					pst.setInt(3, new RoomDao().getRoomById(roomid).getBuildid()
							.getBuildid());
					pst.setInt(4, dept);
					return pst.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return 0;
			}
			@Test
			public void fenpei() {// 所有的未分配的学生
				List<StudentEntity> allStudent = new StudentDao().getWeiRuZhuStudent();
				List<DeptEntity> allDept = new DeptDao().getAllDept();
				//System.out.println("---------------");
				for (int i = 0; i < allStudent.size(); i++) {
					System.out.println("第一层");
					System.out.println("................" + i);

					for (int j = 0; j < allDept.size(); j++) {
						System.out.println("第二层");
						System.out.println("................" + i);
						System.out.println("................" + j);

						if (allStudent.get(i).getStudept().equals(allDept
								.get(j).getDeptname())&&allDept
								.get(j).getDeptid()%2!=0) {//如果学生系别和系别表里的id匹配，往下走
							//if( allDept.get(j).getBuildid().getBuildsex()==0){
							System.out.println("********************");
							System.out.println(allStudent.get(i).getStudept());
							System.out.println(allDept.get(j).getDeptname());
							System.out.println("********************");

							if (allStudent.get(i).getStusex() == 0) {//男生
								System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
								List<DeptEntity> build= new RoomDao().getBoyBuild(allDept
										.get(j).getDeptname());
								List<RoomEntity> allRoom = new RoomDao()
										.getAllNullRoom(build.get(0).getBuildid().getBuildid());//查空房间
								addMidd(allStudent.get(i).getStuid(), allRoom.get(0)
										.getRoomid(), allDept.get(j).getDeptid());
								//修改宿舍表里的人数
								new RoomDao().updateRoomnum(allRoom.get(0)
										.getRoomid());
								System.out.println("分配男");
							}}else if( allStudent.get(i).getStudept().equals(allDept
									.get(j).getDeptname())&&allDept
									.get(j).getDeptid()%2==0){{
							if (allStudent.get(i).getStusex() == 1) {//女生
								System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<");
								List<DeptEntity> build= new RoomDao().getGirlBuild(allDept
										.get(j).getDeptname());
								List<RoomEntity> allRoom = new RoomDao()
										.getAllNullRoom(build.get(0).getBuildid().getBuildid());
								addMidd(allStudent.get(i).getStuid(), allRoom.get(0)
										.getRoomid(), allDept.get(j).getDeptid());
								new RoomDao().updateRoomnum(allRoom.get(0)
										.getRoomid());
								System.out.println("分配女");
							}}
						}

					}
				}}
			public List<StudentMiddleEntity> getAllByRoomId(int roomid) {
				List<StudentMiddleEntity> list=new ArrayList<StudentMiddleEntity>();
				StudentMiddleEntity midd = new StudentMiddleEntity();
				try {
					String sql = "select * from five_middle where roomid=?";
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setInt(1, roomid);
					ResultSet res = pst.executeQuery();
					while (res.next()) {
						midd.setSmid(res.getInt(1));
						midd.setStuid(new StudentDao().getStudentById(res.getInt(2)));
						midd.setRoomid(new RoomDao().getRoomById(res.getInt(3)));
						midd.setBuildid(new BuildingDao().getBuildById(res.getInt(4)));
						list.add(midd);

					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return list;

			}

			
			
			
			
			
			
			
}
