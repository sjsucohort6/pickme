package edu.sjsu.cmpe202.cli;

import edu.sjsu.cmpe202.dbaLayer.Member;

import java.util.Scanner;

/**
 * PickMe is the name of our application for carpooling. It provides a menu based interface.
 [1] Membership
 [2] Ride
 [3] Payment
 [4] Notifications
 [5] Quit

 User can register as rider or driver, can reserve, cancel or track a carpool ride,
 make or receive payments and check notifications sent by application to the user at
 various stages.
 */
public class PickMe
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            printMainMenu();
            String menuOptionSelected = scanner.nextLine();
            switch (menuOptionSelected.trim()) {
                case "1":
                    handleMembership();
                    break;
                case "2":
                    handleRides();
                    break;
                case "3":
                    handlePayment();
                    break;
                case "4":
                    handleNotifications();
                    break;
                case "5":
                    System.exit(0);
                default:
                    System.out.println("ERROR: Unknown menu option. Please retry.");
                    break;
            }
        }
    }


    private static void handlePayment() {

    }

    private static void handleNotifications() {


    }



    private static void handleRides() {
        Scanner scanner= new Scanner(System.in);
        Ride ride = new Ride();

        loop: while(true){
            ride.printReserveRideMenu();
            String menuSelected = scanner.nextLine();
            switch (menuSelected.trim()) {
                case "1":
                    ride.handleRideReservation();
                    break;
                case "2":
                    ride.handleRideCancelation();
                    break;
                case "3":
                    ride.handleRideTracking();
                    break;
                case "4":
                    break loop;
                default:
                    System.out.println("ERROR: Unknown menu option. Please retry.");
                    break;
            }
        }
    }

    private static void handleMembership() {
        Scanner scanner = new Scanner(System.in);
        Membership membership = new Membership();
        loop: while (true) {
            membership.printMembershipMenu();
            String menuSelected = scanner.nextLine();

            switch (menuSelected.trim()) {
                case "1":
                    membership.handleRiderSignup();
                    break;
                case "2":
                    membership.handleDriverSignup();
                    break;
                case "3":
                    break loop;
                default:
                    System.out.println("ERROR: Unknown menu option. Please retry.");
                    break;
            }
        }

    }

    private static void printMainMenu() {
        System.out.println("[1] Membership");
        System.out.println("[2] Ride");
        System.out.println("[3] Payment");
        System.out.println("[4] Notifications");
        System.out.println("[5] Quit");
        System.out.println();
        System.out.println("Enter your choice: ");
    }
}
