package com.ambow.service;

import com.ambow.dao.SuperDao;
import com.ambow.entity.SuperEntity;

public class SuperService {

	SuperDao dao=new SuperDao();
	
	public SuperEntity login(String supername,String superpwd){
		return dao.login(supername, superpwd);
	}
}
