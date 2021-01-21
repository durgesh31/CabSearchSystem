package com.mypractice.sb.CabServiceSystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mypractice.sb.CabServiceSystem.bo.AvailableCabsResponse;
import com.mypractice.sb.CabServiceSystem.bo.GenericSuccessResponse;
import com.mypractice.sb.CabServiceSystem.bo.NoAvailableCabsResponse;
import com.mypractice.sb.CabServiceSystem.entity.CabDriver;
import com.mypractice.sb.CabServiceSystem.entity.DriverContact;
import com.mypractice.sb.CabServiceSystem.entity.DriverLocation;
import com.mypractice.sb.CabServiceSystem.entity.UserLocation;
import com.mypractice.sb.CabServiceSystem.service.CabSystemService;

@RestController
@RequestMapping("cbs/apiv1")
public class AppController {
	
	@Autowired
	private CabSystemService cabSystemService;
	
	@GetMapping("test")
	public String testAPI(){
		return "Testing App endpoints";
	}
	
	@GetMapping("driver/test")
	public String testDriverAPI(){
		return "Testing Driver endpoint";
	}
	
	
	@PostMapping("driver/register")
	public ResponseEntity<CabDriver> registerDriver(@RequestBody CabDriver newDriver) {
		
		System.out.println("In registerDriver method..");
    	System.out.println("===== Registering Driver into database ======");
    	System.out.println(newDriver);
		
		CabDriver newDriverWithId=cabSystemService.registerDriver(newDriver);
		 
		 return new ResponseEntity<>(newDriverWithId,HttpStatus.CREATED);
	}
	
	@PostMapping("driver/{theId}/sendLocation")
	public ResponseEntity<GenericSuccessResponse> saveLocation(@PathVariable int theId,@RequestBody DriverLocation currentLocation) {
		
		double latitude = currentLocation.getLatitude();
		double longitude = currentLocation.getLongitude();
		
		String status = cabSystemService.saveLocation(theId,latitude,longitude);
		
		System.out.println("Saving Location --- status:"+status);
		
		GenericSuccessResponse response=new GenericSuccessResponse("success");		
		
		return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping("passenger/available_cabs")
	private ResponseEntity<?> getAvailableCabs(@RequestBody UserLocation userLocation){
		
		List<CabDriver> nearByDriversList = new ArrayList<>();
		List<DriverContact> driverContacts = new ArrayList<>();
		DriverContact contact;
		
		
		System.out.println("User provided location..."+userLocation.getLatitude()+"  "+userLocation.getLongitude());
		
		nearByDriversList = cabSystemService.getAvailableCabs(userLocation);
		
		for (CabDriver d: nearByDriversList) {
			contact = getDriverContactDetails(d);
		    driverContacts.add(contact);			
		}
		
	   if(driverContacts.size()!=0)	
	   {
		  /* Object[] driverContactsArray = driverContacts.toArray();
		   return new ResponseEntity<DriverContact[]>(driverContactsArray[],HttpStatus.OK);*/
		   AvailableCabsResponse response = new AvailableCabsResponse();
		   response.setAvailable_cabs(driverContacts);
		   return new ResponseEntity<AvailableCabsResponse>(response,HttpStatus.OK);
		   
	   }
	   
	   else
	   {
		   NoAvailableCabsResponse response= new NoAvailableCabsResponse();
		   response.setMessage("No cabs available!");
		   
		   return new ResponseEntity<NoAvailableCabsResponse>(response,HttpStatus.OK);
	   }
		
		
	}
	
	private DriverContact getDriverContactDetails(CabDriver d) {
		DriverContact contact = new DriverContact();
		
		contact.setName(d.getName());
		contact.setPhone_number(d.getPhone_number());
		contact.setCar_number(d.getCar_number());
		
		return contact;
		
	}
	

}
