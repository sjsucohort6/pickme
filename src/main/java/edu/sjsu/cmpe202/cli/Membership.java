package edu.sjsu.cmpe202.cli;

import edu.sjsu.cmpe202.dbaLayer.DBOperations;
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
        System.out.println("\t\t D.O.B (YYYY-MM-DD): ");
        dob = scanner.nextLine();
        while (!CommonValidations.isDateValid(dob)) {
            System.out.println("\t\t D.O.B (YYYY-MM-DD): ");
            dob = scanner.nextLine();
        }
        System.out.println("\t\t Address: ");
        address = scanner.nextLine();
        System.out.println("\t\t Phone: ");
        phone = scanner.nextLine();
        while (!CommonValidations.isValidContact(phone)) {
            System.out.println("\t\t Phone: ");
            phone = scanner.nextLine();
        }
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
        System.out.println("\t\t Expiration: ");
        expiration = scanner.nextLine();
    }

}
