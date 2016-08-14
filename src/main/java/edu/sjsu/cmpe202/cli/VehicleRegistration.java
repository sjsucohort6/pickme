package edu.sjsu.cmpe202.cli;

import edu.sjsu.cmpe202.db.dao.VehicleDao;
import lombok.Data;
import edu.sjsu.cmpe202.db.dao.RideDao;

import java.util.List;
import java.util.Scanner;

@Data
public class VehicleRegistration {
	int ownerID;
	String vehicleId;
	String mailId;
    String status;
	String registration_id; //vehicle registration number
	String capacity;
	
    public static void printVehicleMenu() {
        System.out.println("\t [1] Register Vehicle");
        System.out.println("\t [2] Delete Vehicle Details");
		System.out.println("\t [3] Go back to main menu");
    }
    
    
	
    public void handleVehicleRegistration() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t Mail ID: ");
        mailId = scanner.nextLine();
		ownerID = RideDao.getRiderID(mailId);
		System.out.println("\t\t Vehicle Registration No.: ");
        registration_id = scanner.nextLine();
		System.out.println("\t\t Vehicle Max Capacity :");
		capacity = scanner.nextLine();
		status = "Available";
        VehicleDao.createVehicle(this);
        System.out.println("\t\t Vehicle Registered: " + this);
    }

    /*public void handleUpdateVehicleInfo() {
    	Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t Enter Vehicle Registration :");
		vehicleId = scanner.nextLine();



    }*/
    
    public void handleDeleteVehicle() {
    	Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t Owner Mail ID: ");
		mailId = scanner.nextLine();
		ownerID = RideDao.getRiderID(mailId);
    	List<VehicleRegistration> vehicles = VehicleDao.showVehiclesOfOwner(ownerID);

    	if(vehicles != null) {
    		System.out.println("\t\t Below are the vehicles of the Owner :" + mailId);
            ShowVehicles(vehicles);

            System.out.println("\t\t Enter the vehicle ID");
            vehicleId = scanner.nextLine();
    		VehicleDao.deleteVehicle(vehicleId);
    		System.out.println("\t\t Deleted Vehilce with ID:" + vehicleId);
    	}
    	else
    		System.out.println("\t\t No vehicles found in our Database");
    }

	private boolean isOwnerValid() {
		return true; //assumes always user enters correct ID; Can be handled better with login session
	}
	
	private boolean isVehicleValid() {
		return true; //assumes always user enters correct ID; Can be handled better with login session
	}
	
	private void ShowVehicles(List<VehicleRegistration> vehciles)
	{
        System.out.println("\t\t V_ID" + " " +"RegNo" + " " + " Capacity "+ " " + " Status ");
	    for(VehicleRegistration v:vehciles)
		{
			System.out.println("\t\t"+ v.getVehicleId()+ " " +v.getRegistration_id() + " " + v.getCapacity()+ " " + v.getStatus());
		}
	}

}
