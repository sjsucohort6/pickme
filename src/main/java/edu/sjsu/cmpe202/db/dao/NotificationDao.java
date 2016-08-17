package edu.sjsu.cmpe202.db.dao;

import edu.sjsu.cmpe202.db.domain.Notification;
import edu.sjsu.cmpe202.db.SQLConnection;
import org.sql2o.Connection;

import java.util.List;

/**
 * @author rwatsh on 8/12/16.
 */
public class NotificationDao {
    public static Notification sendNotifications(Notification n) {
        return null;
    }

    public static List<Notification> getNotifications() {
        String fetchRouteMapSql = "SELECT * FROM notification";

        try (Connection con = (new SQLConnection()).getConnection()) {
            return con.createQuery(fetchRouteMapSql)
                    .executeAndFetch(Notification.class);
        }
    }
}
