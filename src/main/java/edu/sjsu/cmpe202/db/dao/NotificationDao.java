package edu.sjsu.cmpe202.db.dao;

import edu.sjsu.cmpe202.db.domain.Notification;
import edu.sjsu.cmpe202.db.SQLConnection;
import org.sql2o.Connection;

import java.util.List;

/**
 * @author rwatsh on 8/12/16.
 */
public class NotificationDao {
    public static void sendNotifications(Notification n) {
        String note = "INSERT into notification(notifyuser_id,date,message)" +
                "VALUES(:notifyuser_id,:date,:message)";
        try (Connection con = (new SQLConnection()).getConnection()) {
            con.createQuery(note)
                    .addParameter("notifyuser_id", n.getNotifyUserId())
                    .addParameter("date", n.getDate())
                    .addParameter("message", n.getMessage())
                    .executeUpdate();
        }
    }

    public static List<Notification> getNotifications() {
        String fetchRouteMapSql = "SELECT * FROM notification";
        List<Notification> notifyList;

        try (Connection con = (new SQLConnection()).getConnection()) {
            notifyList = con.createQuery(fetchRouteMapSql)
                    .executeAndFetch(Notification.class);
        }
        return notifyList;
    }
}
