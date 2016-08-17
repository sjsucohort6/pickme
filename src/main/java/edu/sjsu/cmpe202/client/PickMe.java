package edu.sjsu.cmpe202.client;


import edu.sjsu.cmpe202.facade.*;
import java.util.Scanner;

/**
 * PickMe is the name of our application for carpooling. It provides a menu based interface.
 [1] Membership
 [2] Vehicle Registrations
 [3] Ride
 [4] Payment
 [5] Notifications
 [6] Parking
 [6] Quit
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
            PickMeFacade.printMainMenu();
            String menuOptionSelected = scanner.nextLine();
            switch (menuOptionSelected.trim()) {
                case "1":
                    PickMeFacade.handleMembership();
                    break;
                case "2":
                    PickMeFacade.handleVehicleRegistration();
                    break;
                case "3":
                    PickMeFacade.handleRides();
                    break;
                case "4":
                    PickMeFacade.handlePayment();
                    break;
                case "5":
                    PickMeFacade.handleNotifications();
                    break;
                case "6":
                    PickMeFacade.handleParking();
                    break;
                case "7":
                    PickMeFacade.handleReports();
                    break;
                case "8":
                    System.exit(0);
                default:
                    System.out.println("ERROR: Unknown menu option. Please retry.");
                    break;
            }
        }
    }
}