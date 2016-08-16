package edu.sjsu.cmpe202.notification;

/**
 * @author rwatsh on 8/15/16.
 */
public interface Observer {

    public void update();

    public void setSubject(Subject subject);
}