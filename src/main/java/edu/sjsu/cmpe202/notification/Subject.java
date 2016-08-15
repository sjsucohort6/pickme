package edu.sjsu.cmpe202.notification;



/**
 * @author rwatsh on 8/15/16.
 */
public interface Subject {

    public void registerObserver(Observer observer);

    public void notifyObserver();

    public void unRegisterObserver(Observer observer);

    public Object getUpdate();

}

