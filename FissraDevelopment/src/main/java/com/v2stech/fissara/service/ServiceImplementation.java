package com.v2stech.fissara.service;



import java.sql.SQLException;

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
import com.v2stech.fissara.model.UserCredentialsDTO;

@Service
public class ServiceImplementation {
	@Autowired
	DaoImplementation daoImpl;

	public String getUserCredentials(UserCredentialsDTO credentials, BindingResult result) throws SQLException, ClassNotFoundException, InvalidFieldException, InvalidCredentialException, InvalidUsername, InvalidPasswordException {
		String viewNameString = "";
		boolean value=false;
//		if (result.hasErrors()) {
//			throw new InvalidFieldException(result);
//		} else {
		
			value = daoImpl.loginCredentials(credentials);
			if (value) {
				viewNameString="homepage";
				}
		//}
		return viewNameString;
	}


}
