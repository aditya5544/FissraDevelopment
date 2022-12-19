package com.v2stech.fissara.model;

import java.util.ArrayList;
import java.util.List;

public class RegionUploadVO {
	
	private String regionName;
	private String regionManager;
	private String recordUpdateMessage;
	List<Integer> errorColList = new ArrayList<>();
	
	
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getRegionManager() {
		return regionManager;
	}
	public void setRegionManager(String regionManager) {
		this.regionManager = regionManager;
	}
	public String getRecordUpdateMessage() {
		return recordUpdateMessage;
	}
	public void setRecordUpdateMessage(String recordUpdateMessage) {
		this.recordUpdateMessage = recordUpdateMessage;
	}
	public RegionUploadVO(String regionName) {
		super();
		this.regionName = regionName;
	}

	
	
	
	
	
	
	

}
