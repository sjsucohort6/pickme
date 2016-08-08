package edu.sjsu.cmpe202.dbaLayer;

import lombok.Data;

import java.util.Date;

/**
 * @author rwatsh on 8/7/16.
 */
@Data
public class Dispatcher {
    private int poolId;
    private int rideId;
    private Date startTime;
}
