package edu.sjsu.cmpe202.notification;

import edu.sjsu.cmpe202.db.domain.Notification;

import java.util.Date;
import java.util.logging.Logger;

/**
 * @author rwatsh on 8/16/16.
 */
public class FileLogger implements Observer {
    private final static Logger logger = Logger.getLogger(FileLogger.class.getName());

    public FileLogger() {

    }

    @Override
    public void update() {
        Notification notification = (Notification) NotificationSystem.getInstance().getUpdate();
        if (notification != null) {
            logger.info("Received notification at: " + (new Date()).toString() + " => " + notification.getMessage());
        }
    }

    @Override
    public void setSubject(Subject subject) {

    }
}
