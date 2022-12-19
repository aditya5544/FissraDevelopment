package com.v2stech.fissara.model;

import java.util.ArrayList;
import java.util.List;

public class AreaUploadVO {
	
	private String areaId;
	private String areaName;
	private String regionName;
	List<Integer> areaErrorList=new  ArrayList<>();
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	public List<Integer> getAreaErrorList() {
		return areaErrorList;
	}
	public void setAreaErrorList(List<Integer> areaErrorList) {
		this.areaErrorList = areaErrorList;
	}
	public AreaUploadVO(String areaName, String regionName) {
		super();
		this.areaName = areaName;
		this.regionName = regionName;
	}

	
	
	
	
	

}
