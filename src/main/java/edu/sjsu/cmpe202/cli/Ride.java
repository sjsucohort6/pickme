package edu.sjsu.cmpe202.cli;

import edu.sjsu.cmpe202.db.dao.RideDao;
import edu.sjsu.cmpe202.db.domain.RideDetails;
import lombok.Data;

import java.util.Scanner;

/**
 * @author rwatsh on 8/6/16.
 */
@Data
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
        RideDao.addRideRequest(this);
        System.out.println("\t\t We have recieved your Ride Request: " + this);
    }

    public void handleRideTracking() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("\t\t Provide Ride ID: ");
    	rideid = scanner.nextLine();
    	if(rideid != null) {
    		RideDetails ride = RideDao.getRideStatus(rideid);
    		System.out.println("Status of the your Ride :" + ride.getRideId() +
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
    		RideDao.deleteRequestedRide(rideid);
    		System.out.println("Your requested Ride is cancelled");
    	}
    	else
    		System.out.println("\t\t Please provide correct RideID");
    }


}
