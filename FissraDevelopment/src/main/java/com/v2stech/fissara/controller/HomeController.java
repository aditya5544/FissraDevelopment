package com.v2stech.fissara.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.v2stech.fissara.serviceImplementation.ServiceImpl;

@Controller
public class HomeController {
	@Autowired
	private ServiceImpl service;

	String filePath;

	@GetMapping("/")
	public ModelAndView displayHomepage(ModelAndView modelAndView) throws ClassNotFoundException, SQLException {
		modelAndView.setViewName("loginpage");
	
		return modelAndView;
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

//	}

}
