package edu.sjsu.cmpe202.cli;


import edu.sjsu.cmpe202.graph.DijkstraAlgorithm;
import edu.sjsu.cmpe202.graph.Graph;
import edu.sjsu.cmpe202.graph.UndirectedGraphDecorator;
import edu.sjsu.cmpe202.route.RouteMapGraph;

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
    private static final Graph routeMapGraph = RouteMapGraph.loadRouteMap();
    public static final DijkstraAlgorithm algorithm = new DijkstraAlgorithm(new UndirectedGraphDecorator(routeMapGraph));

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
                    handleVehicleRegistration();
                    break;
                case "3":
                    handleRides();
                    break;
                case "4":
                    handlePayment();
                    break;
                case "5":
                    handleNotifications();
                    break;
                case "6":
                    handleParking();
                    break;
                case "7":
                    handleReports();
                    break;
                case "8":
                    System.exit(0);
                default:
                    System.out.println("ERROR: Unknown menu option. Please retry.");
                    break;
            }
        }
    }

    private static void handleReports() {
        Scanner scanner= new Scanner(System.in);


        loop: while(true){
            ReportGeneration reportGeneration = new ReportGeneration();
            ReportGeneration.printReportMenu();
            String menuSelected = scanner.nextLine();
            switch (menuSelected.trim()) {
                case "1":
                    reportGeneration.handleRidesByMemberReport();
                    break;
                case "2":
                    break loop;
                default:
                    System.out.println("ERROR: Unknown menu option. Please retry.");
                    break;
            }
        }

    }

    private static void handleVehicleRegistration() {
        Scanner scanner= new Scanner(System.in);


        loop: while(true){
            VehicleRegistration vehicle = new VehicleRegistration();
            vehicle.printVehicleMenu();
            String menuSelected = scanner.nextLine();
            switch (menuSelected.trim()) {
                case "1":
                    vehicle.handleVehicleRegistration();
                    break;
                case "2":
                    vehicle.handleDeleteVehicle();;
                    break;
                case "3":
                    break loop;
                default:
                    System.out.println("ERROR: Unknown menu option. Please retry.");
                    break;
            }
        }
    }

    private static void handleParking() {
        Scanner scanner = new Scanner(System.in);


        loop:while(true) {
            ParkingHandler pDetails= new ParkingHandler();
            pDetails.printReserveParkingMenu();
            String menuSelected = scanner.nextLine();
            switch (menuSelected.trim()) {
                case "1":
                    pDetails.handleAddParking();
                    break;
                case "2":
                    pDetails.handleListParking();
                    break;
                case "3":
                    pDetails.handleParkingReservation();
                    break;
                case "4":
                    pDetails.handleParkingCancellation();
                    break;
                case "5":
                    break loop;
                default:
                    System.out.println("ERROR: Unknown menu option. Please retry.");
                    break;
            }
        }
    }


    private static void handlePayment() {
        Scanner scanner = new Scanner(System.in);

        loop:
        while (true) {
            Payment payment = new Payment();
            payment.printPaymentMenu();
            String menuSelected = scanner.nextLine();
            switch (menuSelected.trim()) {
                case "1":
                    payment.addCard();
                    break;
                case "2":
                    payment.handleRidePayment();
                    break;
                case "3":
                    payment.handleParkingPayment();
                    break;
                case "4":
                    payment.handlePaymentDetails();
                    break;
                case "5":
                    break loop;
                default:
                    System.out.println("ERROR: Unknown menu option. Please retry.");
                    break;
            }
        }

    }

    private static void handleNotifications() {
        Scanner scanner = new Scanner(System.in);


        loop: while(true) {
            Notifier notifier = new Notifier();
            notifier.printNotificationMenu();
            String menuSelected = scanner.nextLine();
            switch ((menuSelected.trim())) {
                case "1":
                    notifier.showNotification();
                    break;
                case "2":
                    break loop;
                case "3":
                    System.out.println("ERROR: Unknown menu option. Please retry.");
            }
        }

    }

    private static void handleRides() {
        Scanner scanner= new Scanner(System.in);


        loop: while(true){
            Ride ride = new Ride();
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
                    ride.handleSchedule();
                    break;
                case "4":
                    ride.handleDispatch();
                    break;
                case "5":
                    ride.handleRideTracking();
                    break;
                case "6":
                    break loop;
                default:
                    System.out.println("ERROR: Unknown menu option. Please retry.");
                    break;
            }
        }
    }

    private static void handleMembership() {
        Scanner scanner = new Scanner(System.in);

        loop: while (true) {
            Membership membership = new Membership();
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
        System.out.println("[2] Vehicle Registration");
        System.out.println("[3] Ride");
        System.out.println("[4] Payment");
        System.out.println("[5] Notifications");
        System.out.println("[6] Parking");
        System.out.println("[7] Reports");
        System.out.println("[8] Quit");
        System.out.println();
        System.out.println("Enter your choice: ");
    }
}