package edu.sjsu.cmpe202.db.test;

import edu.sjsu.cmpe202.db.dao.NotificationDao;
import edu.sjsu.cmpe202.db.domain.Notification;
import org.junit.Test;

import java.util.List;

/**
 * Created by cpunekar on 13-Aug-16.
 */
public class NotificationTest {

    @Test
    public void testNotification() {
        List<Notification> list = NotificationDao.getNotifications();
        System.out.println("Test notification result:\n" +list.toString());
    }
}
