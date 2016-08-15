package edu.sjsu.cmpe202.db.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author rwatsh on 8/14/16.
 */
@Data
public class ParkingDetails {
    private int parkingDetailsId;
    private int parkerId;
    private int parkingId;
    private Date startTime;
    private Date endTime;
    private String status;
}
