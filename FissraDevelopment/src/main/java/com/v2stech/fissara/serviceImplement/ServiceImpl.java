package com.v2stech.fissara.serviceImplement;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.v2stech.fissara.dao.AreaRegionDaoImplementation;
import com.v2stech.fissara.model.AreaRegionDetails;
import com.v2stech.fissara.model.TenantPojo;

@Service
public class ServiceImpl {
	AreaRegionDaoImplementation daoObj = new AreaRegionDaoImplementation();

	public List<AreaRegionDetails> getData() throws ClassNotFoundException, SQLException {
		return daoObj.getRegionArea();

	}
	
	public List<TenantPojo > getTenanat() throws ClassNotFoundException, SQLException {
		return daoObj.getDatabaseNameList();

	}

}
