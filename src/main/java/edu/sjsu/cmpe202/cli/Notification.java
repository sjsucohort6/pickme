package edu.sjsu.cmpe202.cli;

import edu.sjsu.cmpe202.db.DBOperations;
import lombok.Data;

import java.util.Date;
import java.util.Scanner;

/**
 * @author rwatsh on 8/6/16.
 */
@Data
public class Notification {
    private String notify_id;
    private String notifyuser_id;
    private String date;
    private String message;

    public static void printNotificationMenu(){
        System.out.println("\t [1] Send Notification");
        System.out.println("\t [2] Go back to main menu");
    }

    public void sendNotification() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\t\t Notify ID: ");
        notify_id = scanner.nextLine();
        System.out.println("\t\t Notify User ID: ");
        notifyuser_id = scanner.nextLine();
        System.out.println("\t\t Notify ID: ");
        date = scanner.nextLine();
        System.out.println("\t\t Notify ID: ");
        message = scanner.nextLine();

        DBOperations.sendNotification(this);
        System.out.println("\t\t User has been notified: " + this);
    }

}
