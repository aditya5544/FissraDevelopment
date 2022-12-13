package com.v2stech.fissara.service;



import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.v2stech.fissara.dao.DaoImplementation;
import com.v2stech.fissara.exception.InvalidCredentialException;
import com.v2stech.fissara.exception.InvalidFieldException;
import com.v2stech.fissara.exception.InvalidPasswordException;
import com.v2stech.fissara.exception.InvalidUsername;
import com.v2stech.fissara.model.AreaRegionDetails;
import com.v2stech.fissara.model.UserCredentialsDTO;

@Service
public class ServiceImplementation {
	@Autowired
	DaoImplementation daoImpl;

	public List<AreaRegionDetails> getUserCredentials(UserCredentialsDTO credentials, BindingResult result) throws SQLException, ClassNotFoundException, InvalidFieldException, InvalidCredentialException, InvalidUsername, InvalidPasswordException {
		boolean value=false;
			value = daoImpl.loginCredentials(credentials);
			if (value) {
				return daoImpl.getRegionArea(); 
				
				}
			return null;
	
	}


}
