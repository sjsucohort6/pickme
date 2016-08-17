package edu.sjsu.cmpe202.facade;

import edu.sjsu.cmpe202.db.dao.ParkingDetailsDao;
import edu.sjsu.cmpe202.db.dao.RideDao;
import edu.sjsu.cmpe202.db.domain.Parking;
import lombok.Data;

import java.util.List;
import java.util.Scanner;

/**
 * Created by swethamuchukota on 8/13/16.
 */
@Data
public class ParkingHandler {

    int parkingDetailsID;
    int parkerID;
    int parkingID;
    String startTime;
    String endTime;
    String status;
    String ownerID;
    String locationId;
    String capacity;

    public void printReserveParkingMenu(){
        System.out.println("\t [1] Add Parking");
        System.out.println("\t [2] List Parking");
        System.out.println("\t [3] Request Parking Slot");
        System.out.println("\t [4] Cancel Parking Request");
        System.out.println("\t [5] Go back to main menu");
    }

    public void handleParkingReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t Enter Mail ID:");
        String mailID = scanner.nextLine();
        parkerID = RideDao.getRiderID(mailID);
        System.out.println("\t\t Enter Parking ID:");
        parkingID = scanner.nextInt();
        String startTimeStr = "\t\t Parking Start Time(yyyy-MM-dd HH:mm:ss):";
        startTime = Utilities.getDateTimeString(startTimeStr);
        String endTimeStr = "\t\t Parking End Time(yyyy-MM-dd HH:mm:ss):";
        endTime = Utilities.getDateTimeString(endTimeStr);
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


    public void handleAddParking() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t Owner Email: ");
        String mailId = scanner.nextLine();
        ownerID = "" + RideDao.getRiderID(mailId);
        System.out.println("\t\t Parking location ID: ");
        locationId = scanner.nextLine();
        System.out.println("\t\t Parking Max Capacity :");
        capacity = scanner.nextLine();
        status = ParkingStatus.AVAILABLE.name();
        ParkingDetailsDao.createParking(this);
        System.out.println("\t\t Parking Registered: " + this);
    }

    public void handleListParking() {
        List<Parking> parkingList = ParkingDetailsDao.listParking();
        System.out.println("List of Parkings:");
        System.out.println(parkingList);
    }
}
