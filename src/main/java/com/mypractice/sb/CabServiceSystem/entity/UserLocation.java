package com.mypractice.sb.CabServiceSystem.entity;

public class UserLocation {
	
	private double latitude;
	
	private double longitude;
	
	public UserLocation() {
		
	}

	public UserLocation(double latitude, double longitude) {
		
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "UserLocation [latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
	

}
