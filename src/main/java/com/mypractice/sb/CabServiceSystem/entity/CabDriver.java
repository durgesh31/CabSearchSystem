package com.mypractice.sb.CabServiceSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cab_driver_info")
public class CabDriver {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="email",nullable=false,unique=true)
	private String email;
	
	@Column(name="mobile_number",nullable=false,length=10,unique=true)
	private String phone_number;
	
	@Column(name="license_number",nullable=false,unique=true)
	private String license_number;
	
	@Column(name="car_number",nullable=false,unique=true)
	private String car_number;

/*	
	@Column(name="latitude",nullable=false)
	private double latitude;
	
	@Column(name="longitude",nullable=false)
	private double longitude;
*/	
	public CabDriver() {
		
	}	

	public CabDriver(String name, String email, String phone_number, String license_number, String car_number) {
		
		this.name = name;
		this.email = email;
		this.phone_number = phone_number;
		this.license_number = license_number;
		this.car_number = car_number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getLicense_number() {
		return license_number;
	}

	public void setLicense_number(String license_number) {
		this.license_number = license_number;
	}

	public String getCar_number() {
		return car_number;
	}

	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}	

/*	public double getLatitude() {
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
	}*/

	@Override
	public String toString() {
		return "CabDriver [id=" + id + ", name=" + name + ", email=" + email + ", phone_number=" + phone_number
				+ ", license_number=" + license_number + ", car_number=" + car_number + "]";
	}
	
	
	

}
