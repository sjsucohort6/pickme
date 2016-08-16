package edu.sjsu.cmpe202.ride;

import edu.sjsu.cmpe202.db.domain.RideDetails;

import java.util.List;

/**
 * @author rwatsh on 8/15/16.
 */
public abstract class RideState {
    protected List<RideDetails> ridesList;

    public RideState(List<RideDetails> ridesList) {
        this.ridesList = ridesList;
    }
    protected void handleInput(RideStateContext context) {
        // do nothing here
    }
}
