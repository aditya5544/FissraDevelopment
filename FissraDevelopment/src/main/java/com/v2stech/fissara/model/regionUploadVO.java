package com.v2stech.fissara.model;

import java.util.ArrayList;
import java.util.List;

public class regionUploadVO {
	

	public regionUploadVO(String regionName) {
		super();
		this.regionName = regionName;
	}

	public regionUploadVO(String regionName, String recordUpdateMessage, List<Integer> errorColList) {
		super();
		this.regionName = regionName;
		this.recordUpdateMessage = recordUpdateMessage;
		this.errorColList = errorColList;
	}


	private String regionName;
	private String recordUpdateMessage;
	
	List<Integer> errorColList = new ArrayList<Integer>();

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getRecordUpdateMessage() {
		return recordUpdateMessage;
	}

	public void setRecordUpdateMessage(String recordUpdateMessage) {
		this.recordUpdateMessage = recordUpdateMessage;
	}

	public List<Integer> getErrorColList() {
		return errorColList;
	}

	public void setErrorColList(List<Integer> errorColList) {
		this.errorColList = errorColList;
	}

	
	
	
	
	

}
