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
import com.v2stech.fissara.model.UserCredentialsDTO;
import com.v2stech.fissara.model.regionUploadVO;

@Service
public class ServiceImplementation {
	@Autowired
	DaoImplementation daoImpl;

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
 
	public Object validateRegionUploadVoList(List<regionUploadVO> regionUploadVoList)
			throws ClassNotFoundException, SQLException {
		int failCounter = 0;
		boolean check;

		for (regionUploadVO regionUploadVO : regionUploadVoList) {
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
		if (failCounter>0) {
		//	write(regionUploadVoList, filePathError);
			return regionUploadVoList;
			//return 1;
			}

		return 1;
	}

	public void write(List<regionUploadVO> errorDataList, String filePathError) {
		boolean status = false;
		try (FileWriter writer = new FileWriter(filePathError)) {
			for (regionUploadVO errorRecord : errorDataList) {
				
				writer.append(errorRecord.getRegionName() + "," + errorRecord.getRecordUpdateMessage()+ "\n");
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


	public List<regionUploadVO> read(CommonsMultipartFile cmpFile) throws IOException {
		List<String> headerList = new ArrayList();
		List<regionUploadVO> regionUploadVoList = new ArrayList<>();
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
				// dataList.add(line);
			

				data = line.split(",");
				System.out.println("Array length" + data.length);
				for (int i = 0; i < data.length; i++) {
				
					// regionName = data[0];
					regionUploadVO regionUploadObject = new regionUploadVO(data[0]);
					regionUploadVoList.add(regionUploadObject);
					System.out.print("my data=" + data[i] + " ");
				}
				System.out.println();
				//regionUploadVoList.add(regionUploadObject);
				// result.add(line);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return regionUploadVoList;
	}

}
