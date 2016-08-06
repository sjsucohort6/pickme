package edu.sjsu.cmpe202.cli;

/**
 * @author rwatsh on 8/6/16.
 */
public class Membership {

    public static void printMembershipMenu() {
        System.out.println("\t [1] Sign up as Rider");
        System.out.println("\t [2] Sign up as Driver");
        System.out.println("\t [3] Go back to main menu");
    }


    public static void handleRiderSignup() {
        System.out.println("\t Signing up Rider:");
        handleMemberSignup();
    }

    private static void handleMemberSignup() {
        System.out.println("\t\t Name: ");
        System.out.println("\t\t D.O.B: ");
        System.out.println("\t\t Address: ");
        System.out.println("\t\t Phone: ");
        System.out.println("\t\t Email: ");
    }


    public static void handleDriverSignup() {
        System.out.println("\t Signing up Driver:");
        handleMemberSignup();
    }


}
