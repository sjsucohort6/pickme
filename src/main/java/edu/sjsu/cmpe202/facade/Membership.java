package edu.sjsu.cmpe202.facade;

import edu.sjsu.cmpe202.db.dao.MembershipDao;
import edu.sjsu.cmpe202.db.domain.Notification;
import edu.sjsu.cmpe202.notification.NotificationSystem;
import lombok.Data;

import java.util.Date;
import java.util.Scanner;

/**
 * @author rwatsh on 8/6/16.
 */
@Data
public class Membership {

    private String firstName;
    private String lastName;
    private String dob;
    private String address;
    private String phone;
    private String email;

    // properties needed for driver only
    private String driverLicence;
    private String expiration;

    public static void printMembershipMenu() {
        System.out.println("\t [1] Sign up as Rider");
        System.out.println("\t [2] Sign up as Driver");
        System.out.println("\t [3] Go back to main menu");
    }


    public void handleRiderSignup() {
        System.out.println("\t Signing up Rider:");
        handleMemberSignup();
        int notifyUserId = MembershipDao.createRider(this);
        System.out.println("Rider created: " + this);
        //System.out.println("Rider Id: " + id);
        Date d = new Date();
        String date = Utilities.dateFormat.format(d);
        String message = "Rider Created";

        Notification n = new Notification(notifyUserId, d, message);
        NotificationSystem.getInstance().sendNotification(n);
    }


    private void handleMemberSignup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t First Name: ");
        firstName = scanner.nextLine();
        System.out.println("\t\t Last Name: ");
        lastName = scanner.nextLine();
        String dobMsg = "\t\t D.O.B (YYYY-MM-DD): ";
        dob = Utilities.getDateStr(dobMsg);
        System.out.println("\t\t Address: ");
        address = scanner.nextLine();
        String phoneMsg = "\t\t Phone: ";
        phone = Utilities.getIntStr(phoneMsg);
        System.out.println("\t\t Email: ");
        email = scanner.nextLine();
    }


    public void handleDriverSignup() {
        System.out.println("\t Signing up Driver:");
        handleMemberSignup();
        handleDriverLicence();
        Integer driverId = MembershipDao.createDriver(this);
        String message = "Driver created: " + this;
        System.out.println(message);
        Notification n = new Notification(driverId, new Date(), message);
        NotificationSystem.getInstance().sendNotification(n);
    }

    private void handleDriverLicence() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t Driver Licence: ");
        driverLicence = scanner.nextLine();
        String expirationMsg = "\t\t Expiration (YYYY-MM-DD): ";
        expiration = Utilities.getDateStr(expirationMsg);
    }
}