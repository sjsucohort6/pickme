package edu.sjsu.cmpe202.cli;

import java.util.List;
import java.util.Scanner;
import edu.sjsu.cmpe202.dbaLayer.DBOperations;

public class Vehicle {
	
	int vehicleID;
	int ownerID;
	String firstname; //vehicle registration number
	int capacity;
	
    public static void printVehicleMenu() {
        System.out.println("\t [1] Register Vehicle");
        System.out.println("\t [2] Update Vehicle Details");
        System.out.println("\t [3] Delete Vehicle Details");
    }
    
    
	
    private void handleVehicleRegistration() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t Owner ID: ");
        ownerID = scanner.nextInt();
        System.out.println("\t\t firstname: ");
        firstname = scanner.nextLine();
        System.out.println("\t\t Capacity: ");
        capacity = scanner.nextInt();
        DBOperations.createVehicle(this);
        System.out.println("\t\t Vehicle Registered: " + this);
    }
    
    private void handleUpdateVehicleInfo() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("\t\t Enter VehicleID");
    	vehicleID = scanner.nextInt();
    	if(!isVehicleValid())
    	{
    		System.out.println("\t\t Please provide correct ID");
    		vehicleID = -1;
    	}
    }
    
    private void handleDeleteVehicle() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("\t\t Enter the Owner ID: ");
    	ownerID = scanner.nextInt();
    	if(!isOwnerValid()) {
    		System.out.println("\t\t Please provide correct ID");
    		ownerID = -1;
    		return;
    	}
    	List<Vehicle> vehicles = DBOperations.showVehiclesOfOwner(ownerID);
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

	public int getVehicleID() {
		return vehicleID;
	}
	public void setVehicleID(int vehicleID) {
		this.vehicleID = vehicleID;
	}
	public int getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	private boolean isOwnerValid() {
		return true; //assumes always user enters correct ID; Can be handled better with login session
	}
	
	private boolean isVehicleValid() {
		return true; //assumes always user enters correct ID; Can be handled better with login session
	}
	
	private void ShowVehicles(List<Vehicle> vehciles)
	{
		for(Vehicle v:vehciles)
		{
			System.out.println("\t\t" + v.toString());
		}
	}



	@Override
	public String toString() {
		return "Vehicle [vehicleID=" + vehicleID + ", ownerID=" + ownerID + ", firstname=" + firstname + ", capacity="
				+ capacity + "]";
	}
}
