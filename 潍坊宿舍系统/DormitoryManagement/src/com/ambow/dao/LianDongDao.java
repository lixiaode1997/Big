package com.ambow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ambow.entity.LianDongEntity;
import com.ambow.util.Tools;




public class LianDongDao {
	public List<LianDongEntity> getAreaByParentno(int id) {
	String sql=null;
	if(id!=0){
		sql="select * from city where "+"id!="+id + " and parent_id="+id;//甯傘�鍘裤�鍖�
	}else
	sql="select * from city where parent_id="+id;//鐪併�
Connection conn=Tools.getCon();//鑾峰彇JDBC宸ュ叿绫讳腑鐨勮繛鎺ユ暟鎹簱鐨勬柟娉�
PreparedStatement ps=null;
ResultSet rs=null;
List<LianDongEntity> list=new ArrayList<LianDongEntity>();
try {
	ps=conn.prepareStatement(sql);//瀵箂ql璇彞杩涜棰勭紪璇�
	rs=ps.executeQuery();
	while(rs.next()){
		LianDongEntity area=new LianDongEntity();
		area.setName(rs.getString("name"));
		area.setId(rs.getInt("id"));
		list.add(area);
	}
} catch (SQLException e) {
	e.printStackTrace();
}finally{
	Tools.close(conn, ps, rs);
}
return list;
}
}
