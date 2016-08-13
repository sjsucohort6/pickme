package edu.sjsu.cmpe202.db.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author rwatsh on 8/7/16.
 */
@Data
public class Notification {
    private int notifyId;
    private int notifyUserId;
    private Date date;
    private String message;


}
