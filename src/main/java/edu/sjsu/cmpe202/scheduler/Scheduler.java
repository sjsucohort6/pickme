package edu.sjsu.cmpe202.scheduler;

import edu.sjsu.cmpe202.db.domain.RideDetails;
import edu.sjsu.cmpe202.facade.PickMeFacade;
import edu.sjsu.cmpe202.graph.RoutingStrategy;
import edu.sjsu.cmpe202.graph.ShortestPathStrategy;
import edu.sjsu.cmpe202.payment.PaymentProcessor;

/**
 * @author rwatsh on 8/15/16.
 */
public abstract class Scheduler {
    protected RideDetails rideDetails;
    protected PaymentProcessor paymentProcessor;

    public Scheduler(PaymentProcessor pp) {
        this.paymentProcessor = pp;
    }

    public abstract void scheduleRides(RoutingStrategy routingStrategy);

    public abstract void dispatchCarpools();

    public abstract void completeCarpoolRides();

    public abstract void acceptPaymentForRide();

    /**
     * Template method to test all parts of the scheduler - scheduling, dispatching and completing a ride.
     */
    public final void scheduleDispatchAndCompleteARide(RideDetails rideDetails) {
        this.rideDetails = rideDetails;
        scheduleRides(new ShortestPathStrategy(PickMeFacade.algorithm));
        dispatchCarpools();
        completeCarpoolRides();
        paymentProcessor.processPayment();
    }
}
