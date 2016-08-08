package edu.sjsu.cmpe202.db;

import lombok.Data;

/**
 * @author rwatsh on 8/7/16.
 */
@Data
public class Notification {
    private int notifyId;
    private int notifyUserId;
    private String message;
}
