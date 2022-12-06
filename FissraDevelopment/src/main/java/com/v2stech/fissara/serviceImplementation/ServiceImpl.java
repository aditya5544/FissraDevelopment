package com.v2stech.fissara.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.v2stech.fissara.exception.InvalidCredentialException;
import com.v2stech.fissara.model.UserCredentialsDTO;

@Service
public class ServiceImpl {
	@Autowired
	JdbcTemplate jdbcTemplate;
	public String getUserCredentials(UserCredentialsDTO credentials) throws InvalidCredentialException {
		String viewNameString = "";
		
		 if (credentials.getUsername()) { 
			 List<UserCredentialsDTO> admin =
		  jdbcTemplate.query( "select * from adminCredential where adminUserName='" +
		  credentials.getUserName() + "'and password='" + credentials.getPassword() +
		 "'", new
		 BeanPropertyRowMapper<UserCredentialsDTO>(UserCredentialsDTO.class)); if
		 (!admin.isEmpty()) { viewNameString = "admin-dashboard-page"; } else { throw
		new InvalidCredentialException("Input Filed are empty"); }
		 
		} 
		  return viewNameString;
	}

	// AreaRegionDaoImplementation daoObj = new AreaRegionDaoImplementation();

//	public List<AreaRegionDetails> getData() throws ClassNotFoundException, SQLException {
//		return daoObj.getRegionArea();
//
//	}
//	
//	public List<TenantPojo> getTenanat() throws ClassNotFoundException, SQLException {
//		return daoObj.getDatabaseNameList();
//
//	}
//	public void testingConnection() throws ClassNotFoundException, SQLException
//	{
//		daoObj.connectionTest();
//		
//	}
//	

}
