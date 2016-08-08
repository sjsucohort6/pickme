package edu.sjsu.cmpe202.dbaLayer;

import lombok.Data;

/**
 * @author rwatsh on 8/7/16.
 */
@Data
public class Vehicle {
    private int vehicleId;
    private int ownerId;
    private String name;
    private int capacity;
}
