package com.mypractice.sb.CabServiceSystem.entity;

public class DriverContact {
	
	private String name;
	
	private String car_number;
	
	private String phone_number;
	
	public DriverContact() {
		
	}	
	

	public DriverContact(String name, String car_number, String phone_number) {
		this.name = name;
		this.car_number = car_number;
		this.phone_number = phone_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCar_number() {
		return car_number;
	}

	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	@Override
	public String toString() {
		return "DriverContact [name=" + name + ", car_number=" + car_number + ", phone_number=" + phone_number + "]";
	}
	
	
	

}
