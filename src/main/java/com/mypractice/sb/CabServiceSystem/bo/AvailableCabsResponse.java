package com.mypractice.sb.CabServiceSystem.bo;

import java.util.List;

import com.mypractice.sb.CabServiceSystem.entity.DriverContact;

public class AvailableCabsResponse {
	
	private List<DriverContact> available_cabs;

	public List<DriverContact> getAvailable_cabs() {
		return available_cabs;
	}

	public void setAvailable_cabs(List<DriverContact> available_cabs) {
		this.available_cabs = available_cabs;
	}

	
}
