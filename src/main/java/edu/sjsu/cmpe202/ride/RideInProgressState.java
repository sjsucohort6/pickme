package edu.sjsu.cmpe202.ride;

import edu.sjsu.cmpe202.db.domain.RideDetails;

import java.util.List;

/**
 * @author rwatsh on 8/15/16.
 */
public class RideInProgressState extends RideState {
    public RideInProgressState(List<RideDetails> ridesList) {
        super(ridesList);
    }

    @Override
    public void handleInput(RideStateContext context) {
        System.out.println("Ride In Progress for rides: " + ridesList);
        context.setState(new RideCompletedState(ridesList));
    }
}
