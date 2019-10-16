package com.ambow.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ambow.entity.CostEntity;
import com.ambow.entity.StudentCost;
import com.ambow.util.Tools;

public class CostDao extends Tools {
	//查询全部缴费记录
		public List<CostEntity> getAllCost(){
			List<CostEntity> list =new ArrayList<CostEntity>();
			try {
				String sql="select * from five_cost";
				PreparedStatement ps = getCon().prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					int costid=rs.getInt(1);
					int costroom=rs.getInt(2);
					int costwater=rs.getInt(3);
					CostEntity cost=new CostEntity(costid,costroom,costwater);
					list.add(cost);			
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		} 
		

		@Test
		public void testGetAllCost(){
			List<CostEntity> list = getAllCost();
			for(CostEntity item:list){
				System.out.println(item.getCostid()+"  "+item.getCostroom()+"  "+item.getCostwater());
			}
		}
		
		//根据id查询缴费记录
		public CostEntity getAllCostById(int id){
			CostEntity cost=null;
			try {
				String sql="select * from five_cost where costid=?";
				PreparedStatement ps = getCon().prepareStatement(sql);
				ps.setInt(1,id);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					int costid=rs.getInt(1);
					int costroom=rs.getInt(2);
					int costwater=rs.getInt(3);
					
					cost=new CostEntity(costid,costroom,costwater);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return cost;
		} 
		
		@Test
		public void testGetAllCostById(){
			System.out.println(getAllCostById(1).getCostid()+"   "+getAllCostById(1).getCostroom()+"  "+getAllCostById(1).getCostwater());
		}
		
		
		//根据id删除缴费记录
		public int delCostEntityById(int costid){
			try {
				String sql = "delete from five_cost where costid=?";
				PreparedStatement ps = getCon().prepareStatement(sql);
				ps.setInt(1,costid);
				return ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;
			
		}
		
		
		@Test
		public void testDelCostEntityById(){
			if(delCostEntityById(2)!=0){
				System.out.println("yes");
			}else{
				System.out.println("no");
			}
		}
		
		//添加
		public int insertCostEntity(CostEntity cost){
			
			try {
				String sql="insert into five_cost values(null,?,?)";
				PreparedStatement ps = getCon().prepareStatement(sql);
				ps.setInt(1,cost.getCostroom());
				ps.setInt(2,cost.getCostwater());
				return ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;
		}
		
		@Test
		public void testInsertNoticeEntity(){
			CostEntity cost = new CostEntity(100,600);
			
			if(insertCostEntity(cost)!=0){
				System.out.println("yes");
			}else{
				System.out.println("no");
			}
		}
		
		//根据id 修改
		public int updateCostEntityById(CostEntity cost){
				try {
					String sql="update five_cost set costroom=?,costwater=? where costid=?";
					PreparedStatement pst=getCon().prepareStatement(sql);
					pst.setInt(1,cost.getCostroom());
					pst.setInt(2,cost.getCostwater());
					pst.setInt(3,cost.getCostid());
					return pst.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			return 0;
			}
			
			@Test
			public void testUpdate(){
				CostEntity cost = new CostEntity(3,500,500);
				
				if(updateCostEntityById(cost)!=0){
					System.out.println("yes");
				}else{
					System.out.println("no");
				}
			}
			
		//通过学生得到需要交几年的学费
		public List<CostEntity> getStudentCostNum(int stuid){
			List<CostEntity> list =getAllCost();
			List<CostEntity> afterlist = new ArrayList<CostEntity>();
			String stu=new StudentDao().getStudentById(stuid).getStustartime().toString();
			int startyear=Integer.parseInt(stu.substring(0, 4));//入学年份
			int year=Integer.parseInt(new RecordDao().time().substring(0,4));//现在的年份
			int mouth=Integer.parseInt(new RecordDao().time().substring(6,7));
			if(mouth<8){
				for(int i = 0;i<(year-startyear);i++){
					afterlist.add(list.get(i));
				}				
			}else{
				for(int i = 0;i<(year-startyear)+1;i++){
					afterlist.add(list.get(i));
				}	
			}
			
			return afterlist;
		}
		
		@Test
		public void testGetCostNum(){
			List<CostEntity> list=getStudentCostNum(1);
			for(CostEntity item:list){
				System.out.println(item.getCostid()+"  "+item.getCostroom()+"  "+item.getCostwater());
			}
		}
		
		
		
		
		//得到已缴费的CostEntity
		public List<Integer> getAlreadyCost(int stuid,List<CostEntity> list ){
			
			List<Integer> list1=new ArrayList<Integer>();
			for(int i =0;i<list.size();i++){
				if(new StudentCostDao().isExistInStuCost(stuid,list.get(i).getCostid())){
					list1.add(1);
				}else{
					list1.add(0);
				}
				
			}
			return list1;
		}
		
		
		@Test
		public void testGetAlreadyCost(){
			List<CostEntity> list=getStudentCostNum(1);
			List<Integer> list1=getAlreadyCost(1,list);
			for(Integer item:list1){
				System.out.println(item);
			}
		}
		
		
		//判断学生是否全部缴费完成
		
		public int isCostAllOver(int stuid){
			int flag=0;
			List<CostEntity> list1=getStudentCostNum(stuid);
			int neednum =getStudentCostNum(stuid).size();
			int num=0;
			List<Integer> list=getAlreadyCost(stuid,list1);
			for(Integer  item:list){
				if(item==1){
					num++;
				}
			}
			if(num==neednum){
				flag=1;
			}
			return flag;
		}
		
		@Test
		public void testIsCostAllOver(){
			System.out.println(isCostAllOver(1));
		}
}
