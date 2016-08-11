package edu.sjsu.cmpe202.cli;

import edu.sjsu.cmpe202.db.DBOperations;
import lombok.Data;

import java.util.List;
import java.util.Scanner;

@Data
public class VehicleRegistration {
	String ownerID;


	String vehicleID;
	String MailID;

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
        MailID = scanner.nextLine();

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
    		System.out.println("\t\t Below are the vehciles of the Owner :" + MailID);
            ShowVehicles(vehicles);

            System.out.println("\t\t Enter the vehicle ID");
            vehicleID = scanner.nextLine();
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



	public String getRegistration() {
		return registration;
	}



	public void setRegistration(String registration) {
		this.registration = registration;
	}



	public String getVehicleID() {
		return vehicleID;
	}



	public void setVehicleID(String vehicleID) {
		this.vehicleID = vehicleID;
	}



	public String getOwnerID() {
		return MailID;
	}



	public void setOwnerID(String ownerID) {
		this.MailID = ownerID;
	}



	public String getCapacity() {
		return capacity;
	}



	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

}
