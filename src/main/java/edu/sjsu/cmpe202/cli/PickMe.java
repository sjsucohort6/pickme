package edu.sjsu.cmpe202.cli;

import java.util.Scanner;

/**
 * Command line parsing done using:  http://jcommander.org/
 * In-memory graph data structures are using: https://github.com/jgrapht/jgrapht
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
