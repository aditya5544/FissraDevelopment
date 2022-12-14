package com.v2stech.fissara.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.opencsv.exceptions.CsvValidationException;
import com.v2stech.fissara.dao.DaoImplementation;
import com.v2stech.fissara.exception.InvalidCredentialException;
import com.v2stech.fissara.exception.InvalidFieldException;
import com.v2stech.fissara.exception.InvalidPasswordException;
import com.v2stech.fissara.exception.InvalidUsername;
import com.v2stech.fissara.model.AreaRegionDetails;
import com.v2stech.fissara.model.UserCredentialsDTO;
import com.v2stech.fissara.service.ServiceImplementation;

@Controller
public class HomeController {
	@Autowired
	private ServiceImplementation service;

	String filePath;

	@GetMapping("/")
	public ModelAndView displayHomepage(ModelAndView modelAndView)  {
		modelAndView.setViewName("LoginPage");
		return modelAndView;
	}
	
	 @RequestMapping(value = "/login-data")
	 public ModelAndView addData(@Valid @RequestBody UserCredentialsDTO login, BindingResult result,ModelAndView modelAndView) throws  SQLException, ClassNotFoundException, InvalidFieldException, InvalidCredentialException, InvalidUsername, InvalidPasswordException{
		 System.out.println("username=" + login.getUsername());
		 System.out.println("password=" + login.getPassword());
		 modelAndView.addObject("dataList", service.getUserCredentials(login, result));
		 modelAndView.setViewName("HomePage");
			return modelAndView;
		 
	 }
	 
//	 @RequestMapping(value = "/drop-data")
//	 public ModelAndView addData( @RequestBody AreaRegionDetails area, ModelAndView model)
//	 {
//		System.out.println(area.getDataName());
//		model.setViewName("success");
//		return model;
//		 
//	 }
	 
		@RequestMapping(value = "/uploadYourFile")
		public String  uploadChecker(@RequestParam("fileToStore") CommonsMultipartFile cmpFile,@RequestParam("regionareaname") String select, Model model)
		throws IOException, CsvValidationException
		{
			System.out.println(cmpFile.getOriginalFilename());
			System.out.println(select);
			service.read(cmpFile);
			return "null";
			
		}
	 
	 
	 
	 
//	@RequestMapping(value = "/uploadYourFile")
//	public String  uploadChecker(@RequestParam("fileToStore") CommonsMultipartFile cmpFile, Model model)
//			throws IOException
//
//	{
//		
//		long size;
//		if (!cmpFile.isEmpty() && cmpFile.getSize() != 0) {
//			byte[] data = cmpFile.getBytes();
//			filePath = File.separator + "home" + File.separator + "v2stech" + File.separator + "Downloads"
//					+ File.separator + "FindLatLong"  + File.separator + "src"
//					+ File.separator + "main" + File.separator + "webapp" + File.separator + "static" + File.separator
//					+ "uploads" + File.separator + cmpFile.getOriginalFilename();
//			FileOutputStream fos = new FileOutputStream(filePath);
//			fos.write(data);
//			fos.close();
//			model.addAttribute("fileName", cmpFile.getOriginalFilename());
//			model.addAttribute("fileSize", cmpFile.getSize());
//			size = cmpFile.getSize();
//			if (size > 4242880) {
//				throw new MaxUploadSizeExceededException(size);
//			}
//			model.addAttribute("status", "File Added Successully");
//			service.insertInDatabase(cmpFile,filePath);
//			return "homepage";
//		
//		} else {
//			throw new NullPointerException();
//
//		}
//
//}

}
