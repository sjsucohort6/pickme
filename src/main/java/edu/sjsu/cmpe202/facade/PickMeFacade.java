package edu.sjsu.cmpe202.facade;

import edu.sjsu.cmpe202.graph.DijkstraAlgorithm;
import edu.sjsu.cmpe202.graph.Graph;
import edu.sjsu.cmpe202.graph.UndirectedGraphDecorator;
import edu.sjsu.cmpe202.route.RouteMapGraph;

import java.util.Scanner;

/**
 * Created by cpunekar on 16-Aug-16.
 */
public class PickMeFacade {

    private static final Graph routeMapGraph = RouteMapGraph.loadRouteMap();
    public static final DijkstraAlgorithm algorithm = new DijkstraAlgorithm(new UndirectedGraphDecorator(routeMapGraph));

    public static void handleReports() {
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

    public static void handleVehicleRegistration() {
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

    public static void handleParking() {
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


    public static void handlePayment() {
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

    public static void handleNotifications() {
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

    public static void handleRides() {
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

    public static void handleMembership() {
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

    public static void printMainMenu() {
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
