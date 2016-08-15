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
public class NotificationMessage implements Subject {
    List<Observer> observersList;
    private boolean stateChange;
    private Notification notification;
    private static final NotificationMessage INSTANCE = new NotificationMessage(); // Eager initialization

    private NotificationMessage() {
        this.observersList = new ArrayList<Observer>();
        stateChange = false;
        this.notification = null;
    }

    public static NotificationMessage getInstance() {
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
        NotificationDao.sendNotifications(n);
        System.out.println(n.getMessage());
        notifyObserver();
    }
}
