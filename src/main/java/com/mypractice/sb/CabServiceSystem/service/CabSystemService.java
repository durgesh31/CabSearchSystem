package com.mypractice.sb.CabServiceSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypractice.sb.CabServiceSystem.dao.CabServiceSystemDAO;
import com.mypractice.sb.CabServiceSystem.dao.DriverLocationDAO;
import com.mypractice.sb.CabServiceSystem.entity.CabDriver;
import com.mypractice.sb.CabServiceSystem.entity.DriverLocation;
import com.mypractice.sb.CabServiceSystem.entity.UserLocation;

@Service
public class CabSystemService {

	@Autowired	
 	private CabServiceSystemDAO cabServiceSystemDao;
	
	@Autowired
	private DriverLocationDAO driverLocationDao;
	
	public CabDriver registerDriver(CabDriver newDriver) {
				
		CabDriver cabDriverWithId = cabServiceSystemDao.save(newDriver);
		
		DriverLocation driverLocation = new DriverLocation();
		
		//Initializing the data into driver_location_info table
		driverLocation.setDriverId(cabDriverWithId.getId());
		driverLocation.setLongitude(0.0);
		driverLocation.setLatitude(0.0);
		
		System.out.println("Initializing Driver location in DB --"+driverLocation);
		
		driverLocationDao.save(driverLocation);
		
		
		return cabDriverWithId;
	}

	public String saveLocation(int theId, double latitude, double longitude) {
		
		Optional<CabDriver> theDriverContainer = cabServiceSystemDao.findById(theId);
		
		if(theDriverContainer.isPresent()) {
			CabDriver theDriver = theDriverContainer.get();
			System.out.println("Fetched driver details...."+theDriver);
		//	theDriver.setLongitude(longitude);
		//	theDriver.setLatitude(latitude);
			
			DriverLocation driverLocation = new DriverLocation();
			driverLocation.setDriverId(theDriver.getId());
			driverLocation.setLongitude(longitude);
			driverLocation.setLatitude(latitude);
			
			driverLocationDao.save(driverLocation);
			
			return "success";
		}	
		else
		{
			System.out.println("No driver found with id:"+theId);
			return "failure";
			
		}
		
	}

	public List<CabDriver> getAvailableCabs(UserLocation userLocation) {
		
		List<DriverLocation> nearByCabs = new ArrayList<>();
		List<CabDriver> nearByDriversList = new ArrayList<>();
		//Get all driver location details
		
		Iterable<DriverLocation> iterableList =  driverLocationDao.findAll();
		
		List<DriverLocation> locationList = new ArrayList<>();		
		iterableList.forEach(i->locationList.add(i));
		
		System.out.println("Fetched driver location objects -- total"+locationList.size());
		
		for(DriverLocation dl:locationList) {
		  double dist =	calculateDistance(dl,userLocation);		
		  		  		  
			  if(dist<=4000) {
				  nearByCabs.add(dl);
			  }
		  
		}
		
		for(DriverLocation dl2 : nearByCabs) {
			
			CabDriver d = fetchDriverDetails(dl2.getDriverId());
			nearByDriversList.add(d);			
		}
		System.out.println();
		System.out.println("Count of NearBy drivers :"+nearByDriversList.size());
		
		return nearByDriversList;
		
	}
  
	
	public double calculateDistance(DriverLocation dl,UserLocation ul){
		
		System.out.println();
		System.out.println("====In Calculate Distance method=====");
		
			double userLatitude = ul.getLatitude(); //--- 1
			double userLongitude = ul.getLongitude();		
		  
			double driverLatitude= dl.getLatitude(); //---2
			double driverLongitude= dl.getLongitude();			
			
			System.out.println("User Latitude/Longitude :: "+userLatitude+"  "+userLongitude);
			System.out.println("Driver Latitude/Longitude :: "+driverLatitude+"  "+driverLongitude);
			
			double r= 6371E3;
			double Th1 = userLatitude*Math.PI/180;
			double Th2 = driverLatitude*Math.PI/180;
			
			double deltaTh = (driverLatitude-userLatitude)*Math.PI/180;
			double deltaLa = (driverLongitude-userLongitude)*Math.PI/180;
			
			double a= Math.sin(deltaTh/2) * Math.sin(deltaTh/2) + 
					  Math.cos(Th1) * Math.cos(Th2) * Math.sin(deltaLa/2) * Math.sin(deltaLa/2);
			
			double c= 2*Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
			
			double d= r*c;		
		
			System.out.println("Distance calculated ::"+d);
		
		return d;
	}
	
	
	public CabDriver fetchDriverDetails(int driverId) {
		
		CabDriver driver = cabServiceSystemDao.findById(driverId).get();	
		
		return driver;
	}
 
 
}
