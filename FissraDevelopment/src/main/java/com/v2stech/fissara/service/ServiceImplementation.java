package com.v2stech.fissara.service;



import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.opencsv.exceptions.CsvValidationException;
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
	public void read(CommonsMultipartFile cmpFile) throws IOException, CsvValidationException
	{
		String regionName;
		String regionManager;
		 String csvFile=cmpFile.getOriginalFilename();
		 BufferedReader br;
		 List<String> result = new ArrayList<>();
		 try {

		      String line;
		      InputStream is = cmpFile.getInputStream();
		      System.out.println(is);
		      br = new BufferedReader(new InputStreamReader(is));
		      while ((line = br.readLine()) != null) {
		    	  System.out.println(line);
		    	  result.add(line);
		    	  System.out.println(result.get(0));
		    	  System.out.println(result.get(1));
		    	  System.out.println(result.get(2));
		    	  
//		    	  String[] data = line.split(",");
//		        	regionName = data[0];
//		        	regionManager=data[1];
//		        	System.out.print("area name"+data[0]);
//		        	System.out.print("area manager"+data[1]);
//		        	System.out.print("my name"+data[2]);
//		        	System.out.println();
		    	  //   result.add(line);
		      }
		      System.out.println(" ");

		   } catch (IOException e) {
		     System.err.println(e.getMessage());       
		   }
		// File file = new File(csvFile);
//		 CSVReader reader=new CSVReader(new FileReader(file));
//		 StringBuffer buffer=new StringBuffer();
//		 String data[];
//		 while((data=reader.readNext())!=null)
//		 {
//			 for(int i=0;i<data.length;i++)
//			 {
//				 System.out.print(data[i]+" ");
//			 }
//			 System.out.println(" ");
//		 }
//		 
		 
		 //some treatments to get datas (headers and values)
//		  final BufferedReader br = new BufferedReader(new FileReader(file));
//
//		  try {
//		        String line;
//
//		        while ((line = br.readLine()) != null) {
//		        	String[] data = line.split(",");
//		        	regionName = data[0];
//		        	regionManager=data[1];
//		        	System.out.println("area name"+data[0]);
//		        	System.out.println("area manager"+data[1]);
//				
//		        	
//		           
//		        }
//		    } finally {
//		        br.close();
//		    }
//
//		    try {
//		        file.delete(); // I delete the file
//		    } catch (Exception e) {
//		        e.printStackTrace();
//		    }
//	}

	}
}
