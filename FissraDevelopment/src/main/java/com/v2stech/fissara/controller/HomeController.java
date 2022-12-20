package com.v2stech.fissara.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.opencsv.exceptions.CsvValidationException;
import com.v2stech.fissara.exception.InvalidCredentialException;
import com.v2stech.fissara.exception.InvalidFileException;
import com.v2stech.fissara.model.AreaUploadVO;
import com.v2stech.fissara.model.RegionUploadVO;
import com.v2stech.fissara.model.UserCredentialsDTO;
import com.v2stech.fissara.service.ServiceImplementation;

@RestController
public class HomeController {
	@Autowired
	private ServiceImplementation service;

	String filePath;

	@GetMapping("/")
	public ModelAndView displayHomepage(ModelAndView modelAndView) {
		modelAndView.setViewName("LoginPage");
		return modelAndView;
	}

	@RequestMapping(value = "/login-data")
	public ModelAndView addData(@Valid @RequestBody UserCredentialsDTO login, BindingResult result,
			ModelAndView modelAndView) throws SQLException, ClassNotFoundException, InvalidCredentialException {
		modelAndView.addObject("dataList", service.validateUserCredentials(login, result));
		modelAndView.setViewName("HomePage");
		return modelAndView;
	}

	@PostMapping(value = "/form-details")
	public String uploadChecker(@RequestParam("fileToStore") CommonsMultipartFile cmpFile,
			@RequestParam("regionareaname") String select, Model model)
			throws IOException, CsvValidationException, ClassNotFoundException, SQLException, InvalidFileException {
		System.out.println(cmpFile.getOriginalFilename());
		System.out.println("selected value" + select);
		String filePathError = "/home/v2stech/Documents/Fissara_Development/fissradevelopment/FissraDevelopment/src/main/resources/errorfile.csv";
		Object success;
		List<String> headerList = new ArrayList();
		List<RegionUploadVO> regionUploadVoList = new ArrayList<RegionUploadVO>();
		System.out.println(cmpFile.getOriginalFilename());
		System.out.println(select);
		service.validateFile(cmpFile);
		regionUploadVoList = service.readRegionData(cmpFile);
		System.out.println("list=" + regionUploadVoList);
		for (RegionUploadVO region : regionUploadVoList) {
			System.out.println("return list region name=" + region.getRegionName());
		}
		success = service.validateRegionUploadVoList(regionUploadVoList);
		if (success.equals(regionUploadVoList)) {
			service.write(regionUploadVoList, filePathError);
			System.out.println("written successfully");
			return "Error in file";
		}

		return "File Successfully Uploaded";
	}

//	@RequestMapping(value = "/form-details")
//	public void uploadAreaChecker(@RequestParam("fileToStore") CommonsMultipartFile cmpFile, Model model)
//			throws IOException, ClassNotFoundException, SQLException {
//	List<String> headerList = new ArrayList();
//		List<AreaUploadVO> areaUploadVOList = new ArrayList<>();
//		System.out.println(cmpFile.getOriginalFilename());
//		areaUploadVOList = service.readArea(cmpFile);
//		service.validateAreaUploadVoList(areaUploadVOList);
//
//	}

}
