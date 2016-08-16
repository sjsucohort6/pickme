package edu.sjsu.cmpe202.ride;

import lombok.Data;

import java.util.Date;

/**
 * @author rwatsh on 8/13/16.
 */
@Data
public class CarpoolKey implements Comparable<CarpoolKey> {
    private Integer locationId;
    private Date pickupTime;


    public CarpoolKey(int locationId, Date pickupTime) {
        this.locationId = locationId;
        this.pickupTime = pickupTime;
    }

    @Override
    public int compareTo(CarpoolKey that) {
        if (this == that) return 0;
        else if (that == null) return 1;

        int comparison = this.locationId.compareTo(that.locationId);
        if (comparison != 0) return comparison;

        comparison = this.pickupTime.compareTo(that.pickupTime);
        return comparison;
    }
}
