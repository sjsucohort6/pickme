package edu.sjsu.cmpe202.db.domain;

import lombok.Data;

/**
 * @author rwatsh on 8/7/16.
 */
@Data
public class Vehicle {
    private int vehicleId;
    private int ownerId;
    private String registrationId;
    private int capacity;
    private String status;
}
