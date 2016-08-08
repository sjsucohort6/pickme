package edu.sjsu.cmpe202.dbaLayer;

import lombok.Data;

import java.util.Date;

/**
 * @author rwatsh on 8/7/16.
 */
@Data
public class RideDetails {
    private int rideId;
    private int userId;
    private int sourceId;
    private int destId;
    private Date createDate;
    private Date startDate;
    private String status;
}
