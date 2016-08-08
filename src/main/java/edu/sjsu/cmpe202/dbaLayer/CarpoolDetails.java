package edu.sjsu.cmpe202.dbaLayer;

import lombok.Data;

/**
 * @author rwatsh on 8/7/16.
 */
@Data
public class CarpoolDetails {
    private int poolId;
    private int vehicleId;
    private int driverId;
    private int passengerCount;
    private String status;
    private String route;
}
