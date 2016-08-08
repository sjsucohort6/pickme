package edu.sjsu.cmpe202.cli;

import edu.sjsu.cmpe202.db.DBOperations;
import lombok.Data;

import java.util.List;
import java.util.Scanner;

@Data
public class VehicleRegistration {
	
	int vehicleID;
	int ownerID;
	String registration; //vehicle registration number
	int capacity;
	
    public static void printVehicleMenu() {
        System.out.println("\t [1] Register VehicleRegistration");
        System.out.println("\t [2] Update VehicleRegistration Details");
        System.out.println("\t [3] Delete VehicleRegistration Details");
		System.out.println("\t [4] Go back to main menu");
    }
    
    
	
    public void handleVehicleRegistration() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t Owner ID: ");
        ownerID = scanner.nextInt();
        System.out.println("\t\t Registration ID: ");
        registration = scanner.nextLine();
        System.out.println("\t\t Capacity: ");
        capacity = scanner.nextInt();
        DBOperations.createVehicle(this);
        System.out.println("\t\t VehicleRegistration Registered: " + this);
    }
    
    public void handleUpdateVehicleInfo() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("\t\t Enter VehicleID");
    	vehicleID = scanner.nextInt();
    	if(!isVehicleValid())
    	{
    		System.out.println("\t\t Please provide correct ID");
    		vehicleID = -1;
    	}
    }
    
    public void handleDeleteVehicle() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("\t\t Enter the Owner ID: ");
    	ownerID = scanner.nextInt();
    	if(!isOwnerValid()) {
    		System.out.println("\t\t Please provide correct ID");
    		ownerID = -1;
    		return;
    	}
    	List<VehicleRegistration> vehicles = DBOperations.showVehiclesOfOwner(ownerID);
    	if(vehicles != null) {
    		System.out.println("\t\t Below are the vehciles of the Owner :" + ownerID);
            ShowVehicles(vehicles);
            System.out.println("\t\t Enter the vehicle ID");
            vehicleID = scanner.nextInt();
    		DBOperations.deleteVehicle(vehicleID);
    		System.out.println("\t\t Delete Vehilces with ID:" + vehicleID);
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
