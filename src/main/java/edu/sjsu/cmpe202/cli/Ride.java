package edu.sjsu.cmpe202.cli;

import edu.sjsu.cmpe202.dbaLayer.DBOperations;

import java.util.Date;
import java.util.Scanner;

/**
 * @author rwatsh on 8/6/16.
 */
public class Ride {
	
	String rideid ;
	String userid;
	String sourceid;
	String destid;
	String createDate;
	String startDate;
	String pickupTime;
	String status;

    public void printReserveRideMenu(){
        System.out.println("\t [1] Reserve Ride ");
        System.out.println("\t [2] Cancel Ride");
        System.out.println("\t [3] Track Ride");
        System.out.println("\t [4] Go back to main menu");
    }

    public void handleRideReservation() {
    	Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t Rider ID: ");
        userid = scanner.nextLine();
        System.out.println("\t\t Pickup Location: ");
        sourceid = scanner.nextLine();
        System.out.println("\t\t Destination Location: ");
        destid = scanner.nextLine();
        //createDate = new Date();
        System.out.println("\t\t Pick up Time(yyyy/MM/dd HH:mm:ss: ");
        pickupTime = scanner.nextLine();
        //need code to add Date time.
        status = "Waiting";
        DBOperations.addRideRequest(this);
        System.out.println("\t\t We have recieved your Ride Request: " + this);
    }

    public void handleRideTracking() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("\t\t Provide Ride ID: ");
    	rideid = scanner.nextLine();
    	if(rideid != null) {
    		Ride ride = DBOperations.getRideStatus(rideid);
    		System.out.println("Status of the your Ride :" + ride.getRideid() +
    				" is" + ride.getStatus());
    	}
    	else
    		System.out.println("\t\t Please provide correct RideID");

    }

    public void handleRideCancelation() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("\t\t Provide Ride ID: ");
    	rideid = scanner.nextLine();
    	if(rideid != null) {
    		DBOperations.deleteRequestedRide(rideid);
    		System.out.println("Your requested Ride is cancelled");
    	}
    	else
    		System.out.println("\t\t Please provide correct RideID");
    }

	public String getRideid() {
		return rideid;
	}

	public void setRideid(String rideid) {
		this.rideid = rideid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSourceid() {
		return sourceid;
	}

	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}

	public String getDestid() {
		return destid;
	}

	public void setDestid(String destid) {
		this.destid = destid;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
