package edu.sjsu.cmpe202.cli;

import edu.sjsu.cmpe202.db.dao.VehicleDao;
import lombok.Data;

import java.util.List;
import java.util.Scanner;

@Data
public class VehicleRegistration {
	String ownerID;


	String vehicleId;
	String mailId;

	String registration; //vehicle registration number
	String capacity;
	
    public static void printVehicleMenu() {
        System.out.println("\t [1] Register Vehicle");
        System.out.println("\t [2] Update Vehicle Details");
        System.out.println("\t [3] Delete Vehicle Details");
		System.out.println("\t [4] Go back to main menu");
    }
    
    
	
    public void handleVehicleRegistration() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\t\t Mail ID: ");
        mailId = scanner.nextLine();

        VehicleDao.createVehicle(this);
        System.out.println("\t\t Vehicle Registered: " + this);
    }

    public void handleUpdateVehicleInfo() {
    	Scanner scanner = new Scanner(System.in);
        String vehicleIdMsg = "\t\t Enter Vehicle Registration";
        registration = scanner.nextLine();


    }
    
    public void handleDeleteVehicle() {
    	Scanner scanner = new Scanner(System.in);
        String ownerIdMsg = "\t\t Owner ID: ";
        ownerID = Utilities.getIntStr(ownerIdMsg);

    	List<VehicleRegistration> vehicles = VehicleDao.showVehiclesOfOwner(ownerID);

    	if(vehicles != null) {
    		System.out.println("\t\t Below are the vehciles of the Owner :" + mailId);
            ShowVehicles(vehicles);

            System.out.println("\t\t Enter the vehicle ID");
            vehicleId = scanner.nextLine();
    		VehicleDao.deleteVehicle(vehicleId);
    		System.out.println("\t\t Delete Vehilces with ID:" + vehicleId);
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
		for(VehicleRegistration v:vehciles)
		{
			System.out.println("\t\t" + v.toString());
		}
	}
}
