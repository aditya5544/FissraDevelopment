package com.v2stech.fissara.service;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.v2stech.fissara.dao.DaoImplementation;
import com.v2stech.fissara.exception.InvalidCredentialException;
import com.v2stech.fissara.exception.InvalidFieldException;
import com.v2stech.fissara.exception.InvalidFileException;
import com.v2stech.fissara.exception.InvalidPasswordException;
import com.v2stech.fissara.exception.InvalidUsername;
import com.v2stech.fissara.model.AreaRegionDetails;
import com.v2stech.fissara.model.AreaUploadVO;
import com.v2stech.fissara.model.ErrorData;
import com.v2stech.fissara.model.RegionUploadVO;
import com.v2stech.fissara.model.UserCredentialsDTO;

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

	public void validateFile(CommonsMultipartFile cmpFile) throws InvalidFileException {
		String extension = FilenameUtils.getExtension(cmpFile.getOriginalFilename());
		if(!extension.equals("csv"))
		{
			throw new InvalidFileException("file extension should be csv");
		}
		if(cmpFile==null)
		{
			throw new InvalidFileException("file is null");
		}
		
		
		
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
					if (!check) {
						daoImpl.insertInToDatabaseRegion(regionUploadVO.getRegionName());
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
				} else {
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
				System.out.println("Region Name should not be Empty");
				failCounter++;
				}
			}
		}
		if (failCounter > 0) {
			write(regionUploadVoList, filePathError);
			return 1;
		}
		return 0;
	}
	
	public void validateAreaUploadVoList(List<AreaUploadVO> areaUploadVOList)
			 {
		int failCounter = 0;
		boolean check;
		for (AreaUploadVO areaUplioadVO : areaUploadVOList) {
			if(!areaUplioadVO.getAreaName().isEmpty())
			{
				daoImpl.insertInToArea(areaUplioadVO.getAreaName() ,areaUplioadVO.getRegionName());
			}
			else
			{
				System.out.println("Area Name Should Not be Empty");
			}
			
		}
		
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


	public List<RegionUploadVO> read(CommonsMultipartFile cmpFile) throws IOException {
		List<String> headerList = new ArrayList<String>();
		List<RegionUploadVO> regionUploadVoList = new ArrayList<>();
		String regionName;
	
		String regionManager;
		String csvFile = cmpFile.getOriginalFilename();
		BufferedReader br;
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
				data = line.split(",");
				System.out.println("Array length" + data.length);
				for (int i = 0; i < data.length; i++) {
					regionName = data[0];
					RegionUploadVO regionUploadObject = new RegionUploadVO(data[0]);
					regionUploadVoList.add(regionUploadObject);
					System.out.print("my data=" + data[i] + " ");
				}
				System.out.println();
				// regionUploadVoList.add(regionUploadObject);
				// result.add(line);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return regionUploadVoList;
	}


	public List<AreaUploadVO>  readArea(CommonsMultipartFile cmpFile) throws IOException {
		List<String> headerList = new ArrayList<>();
		List<AreaUploadVO> areaUploadVOList = new ArrayList<>();
		String areaName;
		String regionName;
		String areaManager;
		BufferedReader br;
		try {
			// int i=0;
			String[] data;
			String line = "";
			InputStream is = cmpFile.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			String header = br.readLine();
			if (header != null) {
				headerList.add(header);
			}
			while ((line = br.readLine()) != null) {
				data=line.split(",");
				for (int i = 0; i < data.length; i++){
					areaName=data[0];
					regionName=data[1];
					AreaUploadVO areaUploadVO = new AreaUploadVO(data[0],data[1]);
					areaUploadVOList.add(areaUploadVO);
					System.out.print("my data=" + data[i] + " ");
				}
				System.out.println();
			}
			//Check areaList
			for ( AreaUploadVO areauploadVO: areaUploadVOList) {
				System.out.println("obj "+areauploadVO.getAreaName()+ areauploadVO.getRegionName());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return areaUploadVOList;
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
