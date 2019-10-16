package com.ambow.service;

import java.util.ArrayList;
import java.util.List;

import com.ambow.dao.LianDongDao;
import com.ambow.entity.LianDongEntity;



public class LianDongService {
	 private LianDongDao areacity=new LianDongDao();
		public List<LianDongEntity> getAreaByParentno(int id) {
			List<LianDongEntity> list=new ArrayList<LianDongEntity>();
			return list=areacity.getAreaByParentno(id);
		}
}
