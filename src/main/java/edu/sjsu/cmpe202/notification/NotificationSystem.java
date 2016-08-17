package edu.sjsu.cmpe202.notification;

import edu.sjsu.cmpe202.db.dao.NotificationDao;
import edu.sjsu.cmpe202.db.domain.Notification;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rwatsh on 8/15/16.
 */
@Data
public class NotificationSystem implements Subject {
    List<Observer> observersList;
    private boolean stateChange;
    private Notification notification;
    private static final NotificationSystem INSTANCE = new NotificationSystem(); // Eager initialization

    private NotificationSystem() {
        this.observersList = new ArrayList<Observer>();
        stateChange = false;
        this.notification = null;
    }

    public static NotificationSystem getInstance() {
        return INSTANCE;
    }

    @Override
    public void registerObserver(Observer observer) {
        observersList.add(observer);
    }

    @Override
    public void notifyObserver() {
        if (stateChange) {
            for (Observer observer : observersList) {
                observer.update();
            }
        }
    }

    @Override
    public void unRegisterObserver(Observer observer) {
        observersList.remove(observer);
    }

    @Override
    public Object getUpdate() {
        return notification;
    }

    public synchronized void sendNotification(Notification n) {
        stateChange = true;
        this.notification = n;
        notification.setMessage("Notification: " + n.getMessage());
        NotificationDao.sendNotifications(notification);
        System.out.println(n.getMessage());
        notifyObserver();
    }
}
