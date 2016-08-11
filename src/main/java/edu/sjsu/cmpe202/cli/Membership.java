package edu.sjsu.cmpe202.cli;

import edu.sjsu.cmpe202.db.DBOperations;
import lombok.Data;
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
        DBOperations.createRider(this);
        System.out.println("Rider created: " + this);
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
        DBOperations.createDriver(this);
        System.out.println("Driver created: " + this);
    }

    private void handleDriverLicence() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t Driver Licence: ");
        driverLicence = scanner.nextLine();
        String expirationMsg = "\t\t Expiration (YYYY-MM-DD): ";
        expiration = Utilities.getDateStr(expirationMsg);
    }

}
