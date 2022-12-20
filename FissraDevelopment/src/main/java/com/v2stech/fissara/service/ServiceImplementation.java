package com.v2stech.fissara.service;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.v2stech.fissara.dao.DaoImplementation;
import com.v2stech.fissara.exception.InvalidCredentialException;

import com.v2stech.fissara.exception.InvalidFileException;
import com.v2stech.fissara.model.AreaRegionDetails;
import com.v2stech.fissara.model.AreaUploadVO;
import com.v2stech.fissara.model.RegionUploadVO;
import com.v2stech.fissara.model.UserCredentialsDTO;

@Service
public class ServiceImplementation {
	@Autowired
	DaoImplementation daoImpl;

	String filePathError = "/home/v2stech/Documents/Fissara_Development/fissradevelopment/FissraDevelopment/src/main/resources/errorfile.csv";
	List<String> dataList = new ArrayList<>();

	public List<AreaRegionDetails> validateUserCredentials(UserCredentialsDTO loginCredentials, BindingResult result)
			throws SQLException, ClassNotFoundException,  InvalidCredentialException
			 {
		List<UserCredentialsDTO> loginCredentialsList=new ArrayList<>();
			 String username = null;
			 String password = null;
			 boolean value = false;
			loginCredentialsList = daoImpl.loginCredentials(loginCredentialsList,loginCredentials);
			for(UserCredentialsDTO databaseCredentials:loginCredentialsList)
		    {
			username=databaseCredentials.getUsername();
			password=databaseCredentials.getPassword();
			System.out.println("database username"+username);
			System.out.println("database password"+password);
			}
		value = validateLoginCredentials(loginCredentials, username, password);
		if (value) {
			return daoImpl.getRegionArea();
				}
		return Collections.emptyList();
			 
			 }
	
	private boolean validateLoginCredentials(UserCredentialsDTO loginCredentials, String username, String password
			) throws InvalidCredentialException {
		boolean value1=false;
		if (loginCredentials.getUsername().isEmpty() && loginCredentials.getPassword().isEmpty()) {
				throw new InvalidCredentialException("both are empty");
			}
			if (loginCredentials.getUsername().isEmpty() || loginCredentials.getUsername().isBlank()
					|| loginCredentials.getUsername() == null) {
				throw new InvalidCredentialException("empty username");
			}
			if (loginCredentials.getPassword().isEmpty() || loginCredentials.getPassword().isBlank()
					|| loginCredentials.getPassword() == null) {
				throw new InvalidCredentialException("empty password");
			}
			if (username == null) {
				throw new InvalidCredentialException("invalid username");
			}
			if (username.equals("null") || !username.equals(loginCredentials.getUsername())) {
				throw new InvalidCredentialException("invalid username");
			}

			if (password.equals("null") || loginCredentials.getPassword().isBlank()) {
				throw new InvalidCredentialException("invalid password");
			} else if (!password.equals(loginCredentials.getPassword())) {
				throw new InvalidCredentialException("invalid password");
			} else if (!username.equals(loginCredentials.getUsername())
					&& !password.equals(loginCredentials.getPassword())) {
				throw new InvalidCredentialException("invalid credentials");
			} else {
				value1 = true;
			}
		return value1;
	}
	


	public void validateFile(CommonsMultipartFile cmpFile) throws InvalidFileException {
		String extension = FilenameUtils.getExtension(cmpFile.getOriginalFilename());
		if(!extension.equals("csv"))
		{
			throw new InvalidFileException("file extension should be csv");
		}
		if(cmpFile.isEmpty())
		{
			throw new InvalidFileException("file is null");
		}
		
		
		
	}
 
	public List<RegionUploadVO> readRegionData(CommonsMultipartFile cmpFile) throws IOException {
		List<String> headerList = new ArrayList<>();
		List<RegionUploadVO> regionUploadVoList = new ArrayList<>();
		BufferedReader br;
		try {
			String[] data;
			String line = "";
			InputStream is = cmpFile.getInputStream();
			System.out.println(is);
			br = new BufferedReader(new InputStreamReader(is));
			String header = br.readLine();
			System.out.println("Header " + header);
			if (header != null) {
				headerList.add(header);
			}
			// Print Header List
			for (String a : headerList) {
				System.out.println("hh " + a);
			}
			while ((line = br.readLine()) != null) {
				data = line.split(",");
				System.out.println("Array length" + data.length);
				for (int i = 0; i < data.length; i++) {
					RegionUploadVO regionUploadObject = new RegionUploadVO(data[0]);
					regionUploadVoList.add(regionUploadObject);
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return regionUploadVoList;
	}


	

	public Object validateRegionUploadVoList(List<RegionUploadVO> regionUploadVoList)
			throws ClassNotFoundException, SQLException {
		int failCounter = 0;
		boolean check;
		for (RegionUploadVO regionUploadVO : regionUploadVoList) {
			if (!(regionUploadVO.getRegionName().isEmpty())) {
				String regex = "[0-9]+";
				if (!regionUploadVO.getRegionName().matches(regex)) {
					check = daoImpl.checkFromDatabaseRegion(regionUploadVO.getRegionName());
					failCounter = validationForInsertionInDatabaseForRegion(failCounter, check, regionUploadVO);
				} else {
					failCounter = validationForRegionDatatype(failCounter, regionUploadVO);
				}
			} else {
				if(regionUploadVO.getRecordUpdateMessage()==null)
				{
					regionUploadVO.setRecordUpdateMessage("Region Name should not be Empty");
					failCounter++;
				}
				else
				{
				regionUploadVO.setRecordUpdateMessage(
						regionUploadVO.getRecordUpdateMessage() + "Region Name is should be empty");
				failCounter++;
				}
			}
		}
		if (failCounter > 0) {
			return regionUploadVoList;
		}
		return 0;
	}

	private int validationForRegionDatatype(int failCounter, RegionUploadVO regionUploadVO) {
		if(regionUploadVO.getRecordUpdateMessage()==null)
		{
			regionUploadVO.setRecordUpdateMessage("Region Name should not be in Number");
			failCounter++;
		}
		else
		{
		regionUploadVO.setRecordUpdateMessage(
				regionUploadVO.getRecordUpdateMessage() + "Region name should not be in number");
		System.out.println("Region Name should not be in Number");
		failCounter++;
		}
		return failCounter;
	}

	private int validationForInsertionInDatabaseForRegion(int failCounter, boolean check,
			RegionUploadVO regionUploadVO) {
		if (!check) {
			daoImpl.insertInToDatabaseRegion(regionUploadVO.getRegionName());
			regionUploadVO.setRecordUpdateMessage("succesfully added in database");
		} else {
			if(regionUploadVO.getRecordUpdateMessage()==null)
			{
				regionUploadVO.setRecordUpdateMessage("duplicate region name");
				failCounter++;
			}
			else
			{
			regionUploadVO
					.setRecordUpdateMessage(regionUploadVO.getRecordUpdateMessage() + "Duplicate region");
			System.out.println("Duplicate Region");
			failCounter++;
			}
		}
		return failCounter;
	}
	public void write(List<RegionUploadVO> errorDataList, String filePathError) {
		boolean status = false;
		try (FileWriter writer = new FileWriter(filePathError)) {
			for (RegionUploadVO errorRecord : errorDataList) {

				writer.append(errorRecord.getRegionName() + "," + errorRecord.getRecordUpdateMessage() + "\n");
				status = true;
			}
			if (!status) {
				System.out.println("Not successfully created");
			} else {
				System.out.println("Error file successfully created");
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
//	public void validateAreaUploadVoList(List<AreaUploadVO> areaUploadVOList) throws ClassNotFoundException, SQLException
//			 {
//		int failCounter = 0;
//		String id;
//		for (AreaUploadVO areaUplioadVO : areaUploadVOList) {
//			if(!areaUplioadVO.getAreaName().isEmpty())
//			{
//				if(!areaUplioadVO.getRegionName().isEmpty())
//				{
//				id=daoImpl.checkFromDatabaseRegionForId(areaUplioadVO.getRegionName());
//				if(!id.equals(null))
//				{
//					
//				daoImpl.insertInToArea(areaUplioadVO.getAreaName() ,areaUplioadVO.getRegionName(), id);
//				}
//				else
//				{
//					if(areaUplioadVO.getRecordUpdatemessage()==null)
//					{
//						areaUplioadVO.setRecordUpdatemessage("region name not present in region table");
//					}
//					else
//					{
//						areaUplioadVO.setRecordUpdatemessage(
//								areaUplioadVO.getRecordUpdatemessage() + "region name not present in region table");
//					}
//					System.out.println("region name not present in region table");
//				}
//				}
//				else
//				{
//					if(areaUplioadVO.getRecordUpdatemessage()==null)
//					{
//						areaUplioadVO.setRecordUpdatemessage("region name should not be empty");
//					}
//					else
//					{
//						areaUplioadVO.setRecordUpdatemessage(
//								areaUplioadVO.getRecordUpdatemessage() + "region name should not be empty");
//					}
//					System.out.println("region name should not be empty");
//				}
//			}
//			else
//			{
//				if(areaUplioadVO.getRecordUpdatemessage()==null)
//				{
//					areaUplioadVO.setRecordUpdatemessage("Area Name Should Not be Empty");
//				}
//				else
//				{
//					areaUplioadVO.setRecordUpdatemessage(
//							areaUplioadVO.getRecordUpdatemessage() + "Area Name Should Not be Empty");
//				}
//				System.out.println("Area Name Should Not be Empty");
//			}
//			
//		}
//		
//	}
//	
//	
	

	

//	public List<AreaUploadVO>  readArea(CommonsMultipartFile cmpFile) throws IOException {
//		List<String> headerList = new ArrayList<>();
//		List<AreaUploadVO> areaUploadVOList = new ArrayList<>();
//		String areaName;
//		String regionName;
//		String areaManager;
//		BufferedReader br;
//		try {
//			regionName		// int i=0;
//			String[] data;
//			String row = "";
//			InputStream is = cmpFile.getInputStream();
//			br = new BufferedReader(new InputStreamReader(is));
//			String header = br.readLine();
//			if (header != null) {
//				headerList.add(header);
//			}
//			while ((row = br.readLine()) != null) {
//				data=row.split(",");
//				System.out.println("data length"+data.length);
//				AreaUploadVO areaData=getOneRowData(data);
//				areaUploadVOList.add(areaData);
//			}
//			//Check areaList
//			for ( AreaUploadVO areauploadVO: areaUploadVOList) {
//				System.out.println("my area list row"+areauploadVO.getAreaName()+ areauploadVO.getRegionName());
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		return areaUploadVOList;
//	}
//
//	private AreaUploadVO getOneRowData(String[] data) {
//	String areaName=null;
//	String regionName=null;
//	
//	AreaUploadVO areaData=new AreaUploadVO(data[0],data[1]);
//	//AreaUploadVO areaData=new AreaUploadVO(areaName,regionName);
//	
//		return areaData;
//	}
	

//	private void datavalidation(String regionName, List<String> dataList) {
//		System.out.println("region_____name" + regionName);
//		if (!regionName.isEmpty()) {
//			if (regionName.getClass().getSimpleName().equals("String")) {
//				daoImpl.insertInToDatabaseRegion(regionName);
//
//			} else {
//				errorList.add("should be in string");
//				System.out.println("should be in string");
//
//			}
//
//		} else {
//			errorList.add("region name is empty");
//			System.out.println("region name is empty");
//
//		}
//
//		StringBuffer sb = new StringBuffer();
//
//		for (String s : errorList) {
//			sb.append(s);
//			sb.append(" ");
//		}
//		String str = sb.toString();
//		System.out.println(str);
//		errorList.clear();
//
//		if (!errorList.isEmpty()) {
//			ErrorData errorData = new ErrorData(regionName, str);
//			errorDataList.add(errorData);
//			write(errorDataList, regionName, filePathError);
//		}
//
//		// P Data List
//		for (String a : dataList) {
//			System.out.println("jj" + a);
//		}
//
//	}

	// @PostMapping(value="/csv")
//	public ResponseEntity<Void> processUpload(CommonsMultipartFile cmpFile) {
//
//	BufferedReader fileReader = new BufferedReader(new 
//	InputStreamReader(file.getInputStream(), "UTF-8"));
//	CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
//
//	Iterable<CSVRecord> csvRecords = csvParser.getRecords();
//
//	for (CSVRecord csvRecord : csvRecords) {
//	    System.out.println(csvRecord);
//	}
//


}
