package edu.sjsu.cmpe202.cli;

import edu.sjsu.cmpe202.db.DBOperations;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author rwatsh on 8/6/16.
 */
@Data
public class Ride {
	
	int rideid = 0;
	String userid;
	String sourceid;
	String destid;
	String createDate;
	String startDate;
	String status;

    public void printReserveRideMenu(){
        System.out.println("\t [1] Reserve Ride ");
        System.out.println("\t [2] Cancel Ride");
        System.out.println("\t [3] Track Ride");
        System.out.println("\t [4] Go back to main menu");
    }

    public void handleRideReservation() {
    	Scanner scanner = new Scanner(System.in);
		String riderIdMsg = "\t\t Rider ID: ";
		userid = Utilities.getIntStr(riderIdMsg);
		String pickupLocMsg = "\t\t Pickup Location: ";
        sourceid = Utilities.getIntStr(pickupLocMsg);
		String destLocMsg = "\t\t Destination Location: ";
        destid = Utilities.getIntStr(destLocMsg);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        createDate = sdf.format(new Date());
		String pickupTimeMsg = "\t\t Pick up Time (yyyy-MM-dd): ";
		startDate = Utilities.getDateStr(pickupTimeMsg);
        //need code to add Date time.
        status = "Waiting";
        DBOperations.addRideRequest(this);
        System.out.println("\t\t We have recieved your Ride Request: " + this);
    }

    public void handleRideTracking() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("\t\t Provide Ride ID: ");
    	rideid = scanner.nextInt();
    	if(rideid > 0) {
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
    	rideid = scanner.nextInt();
    	if(rideid > 0) {
    		DBOperations.deleteRequestedRide(rideid);
    		System.out.println("Your requested Ride is cancelled");
    	}
    	else
    		System.out.println("\t\t Please provide correct RideID");
    }

}
