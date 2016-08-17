package edu.sjsu.cmpe202.scheduler;

import edu.sjsu.cmpe202.db.domain.RideDetails;
import edu.sjsu.cmpe202.facade.PickMeFacade;
import edu.sjsu.cmpe202.graph.RoutingStrategy;
import edu.sjsu.cmpe202.graph.ShortestPathStrategy;

/**
 * @author rwatsh on 8/15/16.
 */
public abstract class Scheduler {
    protected RideDetails rideDetails;

    public abstract void scheduleRides(RoutingStrategy routingStrategy);

    public abstract void dispatchCarpools();

    public abstract void completeCarpoolRides();

    /**
     * Template method to test all parts of the scheduler - scheduling, dispatching and completing a ride.
     */
    public final void scheduleDispatchAndCompleteARide(RideDetails rideDetails) {
        this.rideDetails = rideDetails;
        scheduleRides(new ShortestPathStrategy(PickMeFacade.algorithm));
        dispatchCarpools();
        completeCarpoolRides();
    }
}
