package edu.sjsu.cmpe202.scheduler;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author rwatsh on 8/13/16.
 */
@Data
public class CarpoolGroup {
    private List<Integer> rideList;
    private int capacity;
    private Date pickupTime;

    public CarpoolGroup(int capacity, Date pickupTime) {
        this.capacity = capacity;
        rideList = new ArrayList<>();
        this.pickupTime = pickupTime;
    }

    public void addRide(int rideId) {

    }
}
