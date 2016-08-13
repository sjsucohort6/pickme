package edu.sjsu.cmpe202.cli;

import edu.sjsu.cmpe202.db.dao.NotificationDao;
import edu.sjsu.cmpe202.db.domain.Notification;
import lombok.Data;

import java.util.List;

/**
 * @author rwatsh on 8/6/16.
 */
@Data
public class Notifier {
    private String notify_id;
    private String notifyuser_id;
    private String date;
    private String message;

    public static void printNotificationMenu(){
        System.out.println("\t [1] Send Notifier");
        System.out.println("\t [2] Go back to main menu");
    }

    public void showNotification() {
         List<Notification> result = NotificationDao.getNotifications();
         System.out.println("\t\t Notification: " +result.get(0).getMessage()+" for user" +result.get(0).getNotifyUserId()+"\n");
    }

}
