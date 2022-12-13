package com.v2stech.fissara.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.v2stech.fissara.exception.InvalidCredentialException;
import com.v2stech.fissara.exception.InvalidPasswordException;
import com.v2stech.fissara.exception.InvalidUsername;
import com.v2stech.fissara.model.AreaRegionDetails;
import com.v2stech.fissara.model.TenantPojo;
import com.v2stech.fissara.model.UserCredentialsDTO;

@Repository
public class DaoImplementation {

	private String userId;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public Connection getConnection() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if (connect == null) {
				return connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/template_data",
						"root", "password");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connect;

	}

	public boolean loginCredentials(UserCredentialsDTO loginCredentials)
			throws SQLException, ClassNotFoundException, InvalidUsername, InvalidPasswordException, InvalidCredentialException {
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		String username = null;
		String password = null;
		boolean status = false;
		connect = getConnection();
		preparedStatement = connect.prepareStatement("select * from users where username=? or password=?;");
		preparedStatement.setString(1, loginCredentials.getUsername());
		preparedStatement.setString(2, loginCredentials.getPassword());
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			username = resultSet.getString("username");
			password = resultSet.getString("password");
		}
		if(loginCredentials.getUsername().isEmpty() && loginCredentials.getPassword().isEmpty())
		{
			throw new InvalidCredentialException("both are empty");
		}
		if(loginCredentials.getUsername().isEmpty() ||loginCredentials.getUsername().isBlank()|| loginCredentials.getUsername()==null)
		{
			throw new InvalidCredentialException("empty username");
		}
		if(loginCredentials.getPassword().isEmpty() ||loginCredentials.getPassword().isBlank()|| loginCredentials.getPassword()==null)
		{
			throw new InvalidCredentialException("empty password");
		}
	
	
		if(username==null)
		{
			throw new InvalidUsername("invalid username");
		}
		if(username.equals("null") || !username.equals(loginCredentials.getUsername ()))
		{
			throw new InvalidUsername("invalid username");
		}
		
		if(password.equals("null") || loginCredentials.getPassword().isBlank())
		{
			throw new InvalidPasswordException("invalid password");
		}
		else if (!password.equals(loginCredentials.getPassword())) {
			throw new InvalidPasswordException("invalid password");
		}
		else if(!username.equals(loginCredentials.getUsername ()) && !password.equals(loginCredentials.getPassword()))
		{
			throw new InvalidCredentialException("invalid credentials");
		}
		else
		{
			status=true;
		}
		return status;
	}
	
	public List<AreaRegionDetails> getRegionArea() throws SQLException, ClassNotFoundException {
		List<AreaRegionDetails>regionAreaList = new ArrayList<>();
	     connect=getConnection();
		preparedStatement = connect.prepareStatement("select *from template_details");
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			AreaRegionDetails regionArea = new AreaRegionDetails();
			regionArea.setDataName(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
			regionAreaList.add(regionArea);
		}
		return regionAreaList;
	}

}
