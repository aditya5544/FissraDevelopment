package com.v2stech.fissara.service;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.v2stech.fissara.dao.DaoImplementation;
import com.v2stech.fissara.exception.InvalidCredentialException;
import com.v2stech.fissara.exception.InvalidFieldException;
import com.v2stech.fissara.exception.InvalidPasswordException;
import com.v2stech.fissara.exception.InvalidUsername;
import com.v2stech.fissara.model.AreaRegionDetails;
import com.v2stech.fissara.model.ErrorData;
import com.v2stech.fissara.model.UserCredentialsDTO;
import com.v2stech.fissara.model.regionUploadVO;

@Service
public class ServiceImplementation {
	@Autowired
	DaoImplementation daoImpl;
	List<ErrorData> errorDataList = new ArrayList<ErrorData>();
	List<String> errorList = new ArrayList<>();
	String filePathError = "/home/v2stech/Documents/Fissara_Development/fissradevelopment/FissraDevelopment/src/main/resources/errorfile.csv";

	List<String> dataList = new ArrayList<>();

	public List<AreaRegionDetails> getUserCredentials(UserCredentialsDTO credentials, BindingResult result)
			throws SQLException, ClassNotFoundException, InvalidFieldException, InvalidCredentialException,
			InvalidUsername, InvalidPasswordException {
		boolean value = false;
		value = daoImpl.loginCredentials(credentials);
		if (value) {
			return daoImpl.getRegionArea();

		}
		return null;

	}

	public int validateRegionUploadVoList(List<regionUploadVO> regionUploadVoList) throws ClassNotFoundException, SQLException {
		int failCounter=0;
		boolean check;
		
		for (regionUploadVO regionUploadVO : regionUploadVoList) {
			if(!regionUploadVO.getRegionName().isEmpty())
			{
				String regex = "[0-9]+";
				if(!regionUploadVO.getRegionName().matches(regex))
				{
					check=daoImpl.checkFromDatabaseRegion(regionUploadVO.getRegionName());
					if(!check)
					{
						daoImpl.insertInToDatabaseRegion(regionUploadVO.getRegionName());
					}
					else
					{
						System.out.println("Duplicate Region");
						failCounter++;
					}
						
				}
				else
				{
					System.out.println("Region Name should not be in Number");
					failCounter++;
				}
			}
			else
			{
				System.out.println("Region Name should not be Empty");
				failCounter++;
			}
		}
		
		if(failCounter>0)
		{
			return 1;
		}
		
		return 0;
		
		
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

//	public List<regionUploadVO> read(CommonsMultipartFile cmpFile) throws IOException {
//		// List<Object> csvContent=new ArrayList<Object>();
//		List<String> headerList = new ArrayList();
//		List<regionUploadVO> regionUploadVoList = new ArrayList<>();
//
//		String regionName = null;
//		String regionManager;
//		String csvFile = cmpFile.getOriginalFilename();
//		BufferedReader br;
//		List<String> result = new ArrayList<>();
//		try {
//			// int i=0;
//			String[] data;
//			String line = "";
//			InputStream is = cmpFile.getInputStream();
//			System.out.println(is);
//			br = new BufferedReader(new InputStreamReader(is));
//			String header = br.readLine();
//			System.out.println("Header " + header);
//			if (header != null) {
//				headerList.add(header);
//			}
//
//			// Print Header List
//			for (String a : headerList) {
//				System.out.println("hh " + a);
//			}
//
//			while ((line = br.readLine()) != null) {
//				// dataList.add(line);
//				regionUploadVO regionUploadObject = new regionUploadVO(regionName);
//				regionUploadVoList.add(regionUploadObject);
//				data = line.split(",");
//				System.out.println("Array length" + data.length);
//				for (int i = 0; i < data.length; i++) {
//					regionName = data[0];
//					System.out.print(data[i] + " ");
//				}
//				datavalidation(regionName, dataList);
//				System.out.println();
//				result.add(line);
//			}
//
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		return regionUploadVoList;
//	}

	public List<regionUploadVO> read(CommonsMultipartFile cmpFile) throws IOException {
		List<String> headerList = new ArrayList();
		List<regionUploadVO> regionUploadVoList = new ArrayList<>();

		String regionName = null;
		String regionManager;
		String csvFile = cmpFile.getOriginalFilename();
		BufferedReader br;
		List<String> result = new ArrayList<>();
		try {
			// int i=0;
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
				// dataList.add(line);
				regionUploadVO regionUploadObject = new regionUploadVO(regionName);
				regionUploadVoList.add(regionUploadObject);
				data = line.split(",");
				System.out.println("Array length" + data.length);
				for (int i = 0; i < data.length; i++) {
					regionName = data[0];
					System.out.print(data[i] + " ");
				}
				System.out.println();
				result.add(line);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return regionUploadVoList;
	}

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

	public void write(List<ErrorData> errorDataList, String regionName, String filePathError2) {
		boolean status = false;
		try (FileWriter writer = new FileWriter(filePathError)) {
			for (ErrorData errorRecord : errorDataList) {
				writer.append(errorRecord.getName() + "," + errorRecord.getMessage() + "\n");
				status = true;
			}
			if (!status) {
				System.out.println("not successfully created");
			} else {
				System.out.println("error file successfully created");
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
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
