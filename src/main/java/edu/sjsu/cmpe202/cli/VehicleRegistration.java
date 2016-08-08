package edu.sjsu.cmpe202.cli;

import edu.sjsu.cmpe202.db.DBOperations;
import lombok.Data;

import java.util.List;
import java.util.Scanner;

@Data
public class VehicleRegistration {
	String ownerID;
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
        String ownerIdMsg = "\t\t Owner ID: ";
        ownerID = Utilities.getIntStr(ownerIdMsg);
        System.out.println("\t\t Registration ID: ");
        registration = scanner.nextLine();
        String capacityMsg = "\t\t Capacity: ";
        capacity = Utilities.getIntStr(capacityMsg);
        DBOperations.createVehicle(this);
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

    	List<VehicleRegistration> vehicles = DBOperations.showVehiclesOfOwner(ownerID);
    	if(vehicles != null) {
    		System.out.println("\t\t Below are the vehciles of the Owner :" + ownerID);
            ShowVehicles(vehicles);
            String vehicleIdMsg = "\t\t Enter the vehicle ID";
            String vehicleId = Utilities.getIntStr(vehicleIdMsg);
    		DBOperations.deleteVehicle(vehicleId);
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
		for(VehicleRegistration v:vehciles)
		{
			System.out.println("\t\t" + v.toString());
		}
	}

}
