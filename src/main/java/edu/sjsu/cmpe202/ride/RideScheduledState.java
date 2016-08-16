package edu.sjsu.cmpe202.ride;

import edu.sjsu.cmpe202.db.domain.RideDetails;

import java.util.List;

/**
 * @author rwatsh on 8/15/16.
 */
public class RideScheduledState extends RideState {
    public RideScheduledState(List<RideDetails> rideList) {
        super(rideList);
    }

    @Override
    public void handleInput(RideStateContext context) {
        System.out.println("Ride scheduled");
        context.setState(new RideInProgressState(ridesList));
    }
}
