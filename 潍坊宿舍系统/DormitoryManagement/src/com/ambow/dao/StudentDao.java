package com.ambow.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ambow.entity.AdminEntity;
import com.ambow.entity.DeptEntity;
import com.ambow.entity.RoomEntity;
import com.ambow.entity.StudentEntity;
import com.ambow.entity.StudentMiddleEntity;
import com.ambow.util.Tools;

public class StudentDao extends Tools {
	// 入住，修改学生表里的房间号
/*	public int ruzhu(int roomid, int stuid) {
		int a = 0;
		try {
			Connection con = Tools.getCon();
			String sql = "update five_studentmanager set roomid=? where stuid=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, roomid);
			pst.setInt(2, stuid);
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	@Test
	public void test() {
		RoomEntity room = new RoomDao().getRoomById(1);
		if (ruzhu(2, 6) != 0) {
			System.out.println("success");
		}
	}
*/
/*	public int qingkong(int stuid) {
		int a = 0;
		try {
			Connection con = Tools.getCon();
			String sql = "update five_studentmanager set roomid=null where stuid=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, stuid);
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	@Test
	public void test12() {
		int i = qingkong(6);
		if (i != 0) {
			System.out.println("///////////////////////");
		}
	}*/
	//根据year查询入住的学生
	public  List<StudentEntity> getRuZhuByYear(int y,int adminid) {
		List<StudentEntity> list = new ArrayList<StudentEntity>();
		try {
			Connection con = Tools.getCon();
			String sql = "select * from five_studentmanager s LEFT JOIN five_middle m  on m.stuid=s.stuid "
					+ "LEFT JOIN five_adminmanager a on m.buildid=a.buildid "+
					"where year(s.stustarttime)>=?-5 and year(s.stustarttime)<?-2 "+
					"and adminid=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, y);
			pst.setInt(2, y);
			pst.setInt(3, adminid);
			ResultSet res = pst.executeQuery();
			while (res.next()) {
				a++;
				StudentEntity stu = new StudentEntity(res.getInt(1),
						res.getInt(2), res.getString(3), res.getString(4),
						res.getInt(5), res.getString(6), res.getDate(7),
						res.getInt(8), res.getInt(9));
				list.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(list.size());
		return list;
	}
	@Test
	public void testgetRuZhuByYear(){
		List<StudentEntity> list=getRuZhuByYear(2020,1);
		for (StudentEntity studentEntity : list) {
			System.out.println(studentEntity.getStudept()+" "+studentEntity.getStustartime());
		}
	
	}
	
	/*public  List<StudentMiddleEntity> getRuZhuByYear(int y,int adminid) {
		List<StudentMiddleEntity> list = new ArrayList<StudentMiddleEntity>();
		try {
			Connection con = Tools.getCon();
			String sql = "select * from five_studentmanager s LEFT JOIN five_middle m  on m.stuid=s.stuid "
					+ "LEFT JOIN five_adminmanager a on m.buildid=a.buildid "+
					"where year(s.stustarttime)>=?-5 and year(s.stustarttime)<?-2 "+
					"and adminid=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, y);
			pst.setInt(2, y);
			pst.setInt(3, adminid);
			ResultSet res = pst.executeQuery();
			while (res.next()) {
				StudentMiddleEntity a=new StudentMiddleEntity(res.getInt(1),new StudentDao().getStudentById(res.getInt(2)),new RoomDao().getRoomById(res.getInt(3)),new BuildingDao().getBuildById(res.getInt(4)),new DeptDao().getDeptById(res.getInt(5)));
				list.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(list.size());
		return list;
	}
	*/
	
	
	
	
	
	
	/*//删除根据year的学生
	public  List<StudentEntity> getRuZhuByYear1(int y,int adminid) {
		List<StudentEntity> list = new ArrayList<StudentEntity>();
		try {
			Connection con = Tools.getCon();
			String sql = "select * from five_studentmanager s LEFT JOIN five_middle m  on m.stuid=s.stuid "
					+ "LEFT JOIN five_adminmanager a on m.buildid=a.buildid "+
					"where year(s.stustarttime)>=?-5 and year(s.stustarttime)<?-2 "+
					"and adminid=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, y);
			pst.setInt(2, y);
			pst.setInt(3, adminid);
			ResultSet res = pst.executeQuery();
			while (res.next()) {
				a++;
				StudentEntity stu = new StudentEntity(res.getInt(1),
						res.getInt(2), res.getString(3), res.getString(4),
						res.getInt(5), res.getString(6), res.getDate(7),
						res.getInt(8), res.getInt(9));
				list.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(list.size());
		return list;
	}
	*/
	
	//查询一个学生入没入住
	public int getOneRuZhuMeiRuZhu(int id){
		//StudentEntity stu=null;
		try {
			String sql="SELECT * from five_studentmanager AS s,five_middle as m WHERE s.stuid=m.stuid and s.stuid=?";
			Connection con=Tools.getCon();
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs=pst.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@Test
	public void testgetOneRuZhuMeiRuZhu(){
		int a=getOneRuZhuMeiRuZhu(5);
		if(a==0){
			System.out.println("weiruzhu");
		}else{
			System.out.println("ruzhu");
		}
	}

	// 得到所有学生
	static int a = 0;

	public List<StudentEntity> getAllStudent() {
		List<StudentEntity> list = new ArrayList<StudentEntity>();
		try {
			Connection con = Tools.getCon();
			String sql = "select * from five_studentmanager";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet res = pst.executeQuery();
			while (res.next()) {
				a++;
				StudentEntity stu = new StudentEntity(res.getInt(1),
						res.getInt(2), res.getString(3), res.getString(4),
						res.getInt(5), res.getString(6), res.getDate(7),
						res.getInt(8), res.getInt(9));
				list.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(list.size());
		return list;
	}

	@Test
	public void test1() {
		List<StudentEntity> list = getAllStudent();
		for (StudentEntity item : list) {
			System.out.println(item.getStuid()+" "+item.getStuname()+" "+item.getStudept());
		}
	}
	
	

	
	//得到所有入住学生
	public  List<StudentEntity> getRuZhuStudent() {
		List<StudentEntity> list = new ArrayList<StudentEntity>();
		try {
			Connection con = Tools.getCon();
			String sql = "select * from five_studentmanager AS s,five_middle as m WHERE s.stuid=m.stuid";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet res = pst.executeQuery();
			while (res.next()) {
				a++;
				StudentEntity stu = new StudentEntity(res.getInt(1),
						res.getInt(2), res.getString(3), res.getString(4),
						res.getInt(5), res.getString(6), res.getDate(7),
						res.getInt(8), res.getInt(9));
				list.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(list.size());
		return list;
	}
	
	
	
	@Test
	public void testgetRuZhuStudent(){
		List<StudentEntity> list=getRuZhuStudent();
		for (StudentEntity stu : list) {
			System.out.println(stu.getStuname());
		}
	}
	
	
	//得到所有未入住学生
		public  List<StudentEntity> getWeiRuZhuStudent() {
			List<StudentEntity> list = new ArrayList<StudentEntity>();
			try {
				Connection con = Tools.getCon();
				String sql = "SELECT * from five_studentmanager s LEFT JOIN five_middle m on s.stuid=m.stuid where m.stuid is NULL";
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet res = pst.executeQuery();
				while (res.next()) {
					a++;
					StudentEntity stu = new StudentEntity(res.getInt(1),
							res.getInt(2), res.getString(3), res.getString(4),
							res.getInt(5), res.getString(6), res.getDate(7),
							res.getInt(8), res.getInt(9));
					list.add(stu);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println(list.size());
			return list;
		}
		
		@Test
		public void testgetWeiRuZhuStudent(){
			List<StudentEntity> list=getWeiRuZhuStudent();
			for (StudentEntity stu : list) {
				System.out.println(stu.getStuname());
			}
		}

	// 根据roomid得到宿舍内的学生
	public List<StudentEntity> getAllStudentByRoomid(int roomid) {
		List<StudentEntity> list = new ArrayList<StudentEntity>();
		try {
			Connection con = Tools.getCon();
			String sql = "select * from five_studentmanager ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, roomid);
			ResultSet res = pst.executeQuery();
			while (res.next()) {

				StudentEntity stu = new StudentEntity(res.getInt(1),
						res.getInt(2), res.getString(3), res.getString(4),
						res.getInt(5), res.getString(6), res.getDate(7),
						res.getInt(8), res.getInt(9));
				list.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(list.size());
		return list;
	}

	@Test
	public void test7() {
		List<StudentEntity> list = getAllStudentByRoomid(1);
		System.out.println(list.size());
	}
	/*
//得到所有未分配宿舍的学生
	public List<StudentEntity> getAllNullStudent() {
		List<StudentEntity> list = new ArrayList<StudentEntity>();
		try {
			Connection con = Tools.getCon();
			String sql = "select * from five_studentmanager where roomid is null ";
			PreparedStatement pst = con.prepareStatement(sql);
		
			ResultSet res = pst.executeQuery();
			while (res.next()) {
				RoomEntity room = new RoomDao().getRoomById(res.getInt(10));
				DeptEntity dept=new DeptDao().getDeptById(res.getInt(6));
				StudentEntity stu = new StudentEntity(res.getInt(1),
						res.getInt(2), res.getString(3), res.getString(4),
						res.getInt(5),dept, res.getDate(7),
						res.getInt(8), res.getInt(9), room);
				list.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(list.size());
		return list;
	}
	@Test
	public void wwwww(){
		List<StudentEntity> list=getAllNullStudent();
		for (StudentEntity stu : list) {
			System.out.println(stu.getStuname());
		}
	}*/
/*
	// 根据系别和性别得到所有未分配宿舍的学生
	public List<StudentEntity> getAllNullRoomStudent(int dept, int sex) {
		List<StudentEntity> list = new ArrayList<StudentEntity>();
		try {
			Connection con = Tools.getCon();
			String sql = "select * from five_studentmanager where roomid is null and studept=? and stusex=?";
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setInt(1, dept);
			pst.setInt(2, sex);
			ResultSet res = pst.executeQuery();
			while (res.next()) {
				RoomEntity room = new RoomDao().getRoomById(res.getInt(10));
				String dept1=new DeptDao().getDeptById(res.getInt(6));
				StudentEntity stu = new StudentEntity(res.getInt(1),
						res.getInt(2), res.getString(3), res.getString(4),
						res.getInt(5), dept1, res.getDate(7),
						res.getInt(8), res.getInt(9));
				list.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(list.size());
		return list;
	}
	@Test
	public void ooooo(){
		List<StudentEntity> list=getAllNullRoomStudent(3, 1);
		for (StudentEntity stu : list) {
			System.out.println(stu.getStuname());
		}
	}*/
/*
	public static void main(String[] args) {
		List<StudentEntity> list = new StudentDao().getAllNullRoomStudent(
				3, 1);
		System.out.println(list.size());
		for (StudentEntity item : list) {
			System.out.println(item.getStuname());
		}
	}

	@Test
	public void teset34() {
		List<StudentEntity> list = getAllNullRoomStudent(3, 1);
		System.out.println(list.size());
		for (StudentEntity item : list) {
			System.out.println(item.getStuname());
		}
	}
*/

	// 根据学号查学生所有
	public StudentEntity getStudentByStuno(int stuno) {
		StudentEntity stu = null;
		try {
			Connection con = Tools.getCon();
			String sql = "select * from five_studentmanager where stuno=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, stuno);
			ResultSet res = pst.executeQuery();
			while (res.next()) {
				stu = new StudentEntity(res.getInt(1), res.getInt(2),
						res.getString(3), res.getString(4), res.getInt(5),
						res.getString(6), res.getDate(7), res.getInt(8),
						res.getInt(9));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stu;
	}

	@Test
	public void test6() {
		StudentEntity ee = getStudentByStuno(20160107);

		System.out.println(ee.getStuname());

	}

	

	// 删除学生
	public void delStu(int stuid) {
		try {
			Connection con = Tools.getCon();
			String sql = "delete from five_studentmanager where stuid=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, stuid);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test11() {
		delStu(5);
	}

	// 根据系别和性别查人
	public List<StudentEntity> getAllStudentByDeptAndSex(String dept, int sex) {
		List<StudentEntity> list = new ArrayList<StudentEntity>();
		try {
			Connection con = Tools.getCon();
			String sql = "select * from five_studentmanager where studept=? and stusex=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, dept);
			pst.setInt(2, sex);
			ResultSet res = pst.executeQuery();
			while (res.next()) {
				StudentEntity stu = new StudentEntity(res.getInt(1),
						res.getInt(2), res.getString(3), res.getString(4),
						res.getInt(5), res.getString(6), res.getDate(7),
						res.getInt(8), res.getInt(9));
				list.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Test
	public void test4() {
		List<StudentEntity> list = getAllStudentByDeptAndSex("计算机系", 1);
		for (StudentEntity item : list) {
			System.out.println(item.getStuname());
		}
	}

	
	/* * // 根据学生id查询住哪个宿舍 public StudentEntity getRoomidByStuid(int stuid) { //
	 * List<StudentEntity> list = new ArrayList<StudentEntity>(); try {
	 * Connection con = Tools.getCon(); String sql =
	 * "select * from five_studentmanager where stuid =?"; PreparedStatement pst
	 * = con.prepareStatement(sql); pst.setInt(1, stuid); ResultSet res =
	 * pst.executeQuery(); while (res.next()) {
	 * 
	 * RoomEntity room = new RoomDao().getRoomById(res.getInt(10));
	 * StudentEntity stu = new StudentEntity(res.getInt(1), res.getInt(2),
	 * res.getString(3), res.getString(4), res.getInt(5), res.getString(6),
	 * res.getDate(7), res.getInt(8), res.getInt(9), room); // list.add(stu);
	 * return stu; } } catch (SQLException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } // System.out.println(list.size()); return
	 * null; }
	 * 
	 * @Test public void test10() { StudentEntity item = getRoomidByStuid(5);
	 * System.out.println(item.getRoomid().getRoomid()); }*/
	 

	/*@Test
	public void twetwet() {
		piliangfenpei(3,1,3);
	}

	public void piliangfenpei(int dept, int sex, int buildid) {
		//List<StudentEntity> list = getAllStudent();
		//int allnum = list.size();// 总人数
		List<StudentEntity> list = getAllNullRoomStudent(dept, sex);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 登录
	public StudentEntity login(int stuno, String pwd) {
		StudentEntity stu = null;
		try {
			Connection con = getCon();
			String sql = "select * from five_studentmanager where stuno=? and stupwd=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, stuno);
			pst.setString(2, pwd);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				stu = new StudentEntity(rs.getInt(1), rs.getInt(2),
						rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6), rs.getDate(7), rs.getInt(8),
						rs.getInt(9));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stu;
	}

	@Test
	public void testLogin() {

		int name = 20160104;
		String pwd = "123";
		StudentEntity users = login(name, pwd);
		if (users != null) {
			System.out.println("ok");
		} else {
			System.out.println("error");
		}
	}

	// 返回数据库有没有名字
	public int haveName(String name) {
		try {
			Connection con = getCon();
			String sql = "select count(*) from five_studentmanager where stuname=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, name);
			ResultSet res = pst.executeQuery();
			if (res.next()) {
				return res.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Test
	public void testHaveName() {
		System.out.println(haveName("一一"));
	}

	public StudentEntity getStudentById(int stuid) {
		StudentEntity stu = null;
		try {
			Connection con = Tools.getCon();
			String sql = "select * from five_studentmanager where stuid=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, stuid);
			ResultSet res = pst.executeQuery();
			while (res.next()) {
				stu = new StudentEntity(res.getInt(1), res.getInt(2),
						res.getString(3), res.getString(4), res.getInt(5),
						res.getString(6), res.getDate(7), res.getInt(8),
						res.getInt(9));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stu;
	}
@Test
	public void test2() {
		System.out.println(getStudentById(1).getStuname());
	}

	
/*	// 获取所有未分配宿舍的学生
		public List<StudentEntity> getAllNullRoomStudent1() {
			List<StudentEntity> list = new ArrayList<StudentEntity>();
			try {
				Connection con = Tools.getCon();
				String sql = "select * from five_studentmanager where roomid is null";
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet res = pst.executeQuery();
				while (res.next()) {
					RoomEntity room = new RoomDao().getRoomById(res.getInt(10));
					DeptEntity dept1=new DeptDao().getDeptById(res.getInt(6));
					StudentEntity stu = new StudentEntity(res.getInt(1),
							res.getInt(2), res.getString(3), res.getString(4),
							res.getInt(5), dept1, res.getDate(7),
							res.getInt(8), res.getInt(9), room);
					list.add(stu);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println(list.size());
			return list;
		}
		@Test
		public void ooooowww(){
			List<StudentEntity> list=getAllNullRoomStudent1();
			for (StudentEntity stu : list) {
				System.out.println(stu.getStuname());
			}
		}*/
    //添加学生
    public int addStudent(StudentEntity student) {
	  int rs = 0;
		try {  
	  Connection con = Tools.getCon();
		String sql="insert into five_studentmanager values(null,?,?,?,?,?,?,?,?)";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setInt(1, student.getStuno());
 	    pst.setString(2, student.getStuname());
 	    pst.setString(3, student.getStupwd());
 	    pst.setInt(4, student.getStusex());
 	    pst.setString(5, student.getStudept());
 	    pst.setString(6, String.valueOf(student.getStustartime()));
 	    pst.setInt(7, student.getStuyear());
 	    pst.setInt(8, student.getStupay());
 	return pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			return rs;
}





}
