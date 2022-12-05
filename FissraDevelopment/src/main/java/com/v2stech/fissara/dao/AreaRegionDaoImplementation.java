package com.v2stech.fissara.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.mysql.jdbc.Statement;
import com.v2stech.fissara.model.AreaRegionDetails;
import com.v2stech.fissara.model.TenantPojo;

@Repository
public class AreaRegionDaoImplementation {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	int batchSize=20; 
	
	public Connection getConnection() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if (connect == null)
			{
				return connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/template_data?autoReconnect=true&useSSL=false",
						"root", "password");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connect;
	}
	public void connectionTest() throws SQLException, ClassNotFoundException
	{
		connect=getConnection();
		System.out.println("connection created successfully");
		
		}
	
	
	
//	public List<AreaRegionDetails> getRegionArea() throws SQLException, ClassNotFoundException {
//		List<AreaRegionDetails>regionAreaList = new ArrayList<>();
//	    connect=getConnection();
//		preparedStatement = connect.prepareStatement("select * from template_details");
//		resultSet = preparedStatement.executeQuery();
//		while (resultSet.next()) {
//			AreaRegionDetails regionArea = new AreaRegionDetails();
//			regionArea.setDataName(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
//			System.out.println("RA Data is showing");
//			regionAreaList.add(regionArea);
//		}
//		return regionAreaList;
//	}
//	
	
//	public List<TenantPojo> getDatabaseNameList() throws SQLException, ClassNotFoundException {
//		List<TenantPojo>databaseList = new ArrayList<>();
//	    connect=getConnection();
//		preparedStatement = connect.prepareStatement("select *from database_details");
//		resultSet = preparedStatement.executeQuery();
//		while (resultSet.next()) {
//			TenantPojo databaseListName= new TenantPojo();
//			databaseListName.setDatabase_name(resultSet.getString(resultSet.getMetaData().getColumnName(2)));
//			System.out.println("Database Data is showing");
//			databaseList.add(databaseListName);
//		}
//		return databaseList;
	//}
	


}
