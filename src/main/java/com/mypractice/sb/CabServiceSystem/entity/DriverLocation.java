package com.mypractice.sb.CabServiceSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="driver_location_info")
public class DriverLocation {
	
	@Id
	@Column(name="driver_id",nullable=false)
	private int driverId;	
	
	@Column(name="latitude",nullable=false)
	private double latitude;
	
	@Column(name="longitude",nullable=false)
	private double longitude;
	
	public DriverLocation() {
		
	}	

	public DriverLocation(int driverId, double latitude, double longitude) {
		this.driverId = driverId;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	
	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
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
		return "DriverLocation [driverId=" + driverId + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	
}
