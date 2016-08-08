package edu.sjsu.cmpe202.cli;

import edu.sjsu.cmpe202.db.DBOperations;

import java.util.Date;
import java.util.Scanner;

/**
 * @author rwatsh on 8/6/16.
 */
public class Ride {
	
	int rideid = 0;
	int userid;
	int sourceid;
	int destid;
	Date createDate;
	Date startDate;
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
        userid = scanner.nextInt();
        System.out.println("\t\t Pickup Location: ");
        sourceid = scanner.nextInt();
        System.out.println("\t\t Destination Location: ");
        destid = scanner.nextInt();
        createDate = new Date();
        System.out.println("\t\t Pick up Time(yyyy/MM/dd HH:mm:ss: ");
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

	public int getRideid() {
		return rideid;
	}

	public void setRideid(int rideid) {
		this.rideid = rideid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getSourceid() {
		return sourceid;
	}

	public void setSourceid(int sourceid) {
		this.sourceid = sourceid;
	}

	public int getDestid() {
		return destid;
	}

	public void setDestid(int destid) {
		this.destid = destid;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
