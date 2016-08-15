package edu.sjsu.cmpe202.db.domain;

import lombok.Data;

/**
 * @author rwatsh on 8/14/16.
 */
@Data
public class Parking {
    private int parkingId;
    private int ownerId;
    private int capacity;
    private int locationId;
    private String status;
}
