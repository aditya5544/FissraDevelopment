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
import com.v2stech.fissara.model.AreaRegionDetails;
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

//	public List<UserCredentialsDTO> loginCredentials(UserCredentialsDTO loginCredentials) throws SQLException, ClassNotFoundException, InvalidCredentialException {
//		PreparedStatement preparedStatement;
//		ResultSet resultSet;
//		String username = null;
//		String password = null;
//		boolean status = false;
//		connect = getConnection();
//		preparedStatement = connect.prepareStatement("select * from users where username=? or password=?;");
//		preparedStatement.setString(1, loginCredentials.getUsername());
//		preparedStatement.setString(2, loginCredentials.getPassword());
//		resultSet = preparedStatement.executeQuery();
//		while (resultSet.next()) {
//		UserCredentialsDTO loginCredentials1=new UserCredentialsDTO();
//			username = resultSet.getString("username");
//			loginCredentials1.setUsername(username);
//			password = resultSet.getString("password");
//			loginCredentials1.setPassword(password);
//			
//		}
		
		//status = validateLoginCredentials(loginCredentials, username, password, status);
//		return status;
//	}

	

	public List<AreaRegionDetails> getRegionArea() throws SQLException, ClassNotFoundException {
		List<AreaRegionDetails> regionAreaList1 = new ArrayList<>();
		connect = getConnection();
		preparedStatement = connect.prepareStatement("select *from template_details");
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			AreaRegionDetails regionArea = new AreaRegionDetails();
			regionArea.setDataName(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
			regionAreaList1.add(regionArea);
		}
		return regionAreaList1;
	}

	public void insertInToDatabaseRegion(String regionName) {
		try {
			connect = getConnection();
			String sql = "insert into region(region_name) values(?)";
			preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setString(1, regionName);
			preparedStatement.executeUpdate();
			System.out.println("my region name" + regionName);
			System.out.println("data inserted successfully");
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}


	public void findRegion() throws ClassNotFoundException, SQLException {
		String region_name = null;
		connect = getConnection();
		String sql = "select region_name from region where region_id=?";
		preparedStatement = connect.prepareStatement(sql);
		preparedStatement.setString(1, region_name);
		

	}

	public void insertInToArea(String areaName, String regionName,String id) {
		try {
			connect = getConnection();
			String sql = "insert into area(area_name,region_name,region_id) values(?,?)";
			preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setString(1, areaName);
			preparedStatement.setString(2, regionName);
			preparedStatement.setInt(3, Integer.parseInt(id));
			preparedStatement.executeUpdate();
			System.out.println("Area Data inserted successfully");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	public boolean checkFromDatabaseRegion(String regionName) throws ClassNotFoundException, SQLException {
		connect = getConnection();
		boolean value = true;
		String sql = "select * from region where region_name=? ";
		preparedStatement = connect.prepareStatement(sql);
		preparedStatement.setString(1, regionName);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (!resultSet.isBeforeFirst()) {
			value = false;
		}
		return value;
	}

	public String checkFromDatabaseRegionForId(String regionName) throws ClassNotFoundException, SQLException {
		connect = getConnection();
		String id = null;
		boolean value = true;
		String sql = "select  region_id from region where region_name=? ";
		preparedStatement.setString(1, regionName);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			id = resultSet.getString("region_id");
		}
		return id;
		
	}

	public List<UserCredentialsDTO> loginCredentials(List<UserCredentialsDTO> loginCredentialsList, UserCredentialsDTO loginCredentials) throws ClassNotFoundException, SQLException {
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
		UserCredentialsDTO loginCredentials1=new UserCredentialsDTO();
			username = resultSet.getString("username");
			loginCredentials1.setUsername(username);
			password = resultSet.getString("password");
			loginCredentials1.setPassword(password);
			loginCredentialsList.add(loginCredentials1);
			
		}
		
		return loginCredentialsList;
	}

}
