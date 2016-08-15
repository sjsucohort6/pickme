package edu.sjsu.cmpe202.cli;

import edu.sjsu.cmpe202.db.dao.ParkingDetailsDao;
import edu.sjsu.cmpe202.db.dao.RideDao;
import lombok.Data;

import java.util.Date;
import java.util.Scanner;

/**
 * Created by swethamuchukota on 8/13/16.
 */
@Data
public class ParkingDetails {

    int parkingDetailsID;
    int parkerID;
    int parkingID;
    String startTime;
    String endTime;
    String status;

    public void printReserveParkingMenu(){
        System.out.println("\t [1] Request Parking Slot");
        System.out.println("\t [2] Cancel Parking Request");
        System.out.println("\t [3] Go back to main menu");
    }

    public void handleParkingReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t Enter Mail ID:");
        String mailID = scanner.nextLine();
        parkerID = RideDao.getRiderID(mailID);
        System.out.println("\t\t Enter Location:");
        parkingID = scanner.nextInt();
        String startTime = "\t\t Parking Start Time(yyyy/MM/dd HH:mm:ss):";
        String startDate = Utilities.getDateTimeString(startTime);
        String endTime = "\t\t Parking End Time(yyyy/MM/dd HH:mm:ss):";
        String endDate = Utilities.getDateTimeString(endTime);
        status = "Waiting";
        ParkingDetailsDao.addParkingRequest(this);
        System.out.println("\t\t We have recieved your Parking Request: " + this);
    }

    public void handleParkingCancellation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t Enter Parking Request ID :");
        parkingDetailsID = scanner.nextInt();
        ParkingDetailsDao.cancelParkingRequest(parkingDetailsID);
        System.out.println("\t\t Your Parking request is cancelled");
    }


}
