package edu.sjsu.cmpe202.scheduler;

import edu.sjsu.cmpe202.facade.CarpoolStatus;
import edu.sjsu.cmpe202.facade.RideStatus;
import edu.sjsu.cmpe202.db.dao.CarpoolDao;
import edu.sjsu.cmpe202.db.dao.MembershipDao;
import edu.sjsu.cmpe202.db.dao.RideDao;
import edu.sjsu.cmpe202.db.domain.CarpoolDetails;
import edu.sjsu.cmpe202.db.domain.Member;
import edu.sjsu.cmpe202.db.domain.RideDetails;
import edu.sjsu.cmpe202.db.domain.Vehicle;
import edu.sjsu.cmpe202.graph.RoutingStrategy;
import edu.sjsu.cmpe202.payment.CreditCardPaymentProcessor;
import edu.sjsu.cmpe202.payment.PaymentProcessor;
import edu.sjsu.cmpe202.ride.*;
import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CarpoolScheduler schedules the rides to carpools.
 *
 * The logic currently is simply to put the rides with same origin location and same pickup time in one carpool
 * with a max carpool group size of 4.
 *
 * A singleton enum.
 *
 * @author rwatsh on 8/13/16.
 */
public class CarpoolScheduler extends Scheduler {

    private static final CarpoolScheduler INSTANCE = new CarpoolScheduler(new CreditCardPaymentProcessor());

    private CarpoolScheduler(PaymentProcessor pp) {super(pp);}

    public static CarpoolScheduler getInstance() {
        return INSTANCE;
    }

    @Override
    public void scheduleRides(RoutingStrategy routingStrategy) {
        Map<CarpoolKey,List<RideDetails>> carpoolMap = new HashMap<>();

        // Look up ride table for rides that are in pending state
        // and check the pickup time and src location
        List<RideDetails> rideDetailsList = RideDao.getRidesByStatus(RideStatus.PENDING.name());

        /*
         * For rides that start at the same time and from same location
         * put them in carpool with a max of 4 people in one carpool group.
         */
        for (RideDetails ride: rideDetailsList) {
            CarpoolKey carpoolKey = new CarpoolKey(ride.getSourceId(), ride.getStartDate());
            List<RideDetails> ridesList = carpoolMap.get(carpoolKey);
            if (ridesList == null) {
                ridesList = new ArrayList<>();
            }
            ridesList.add(ride);
            carpoolMap.put(carpoolKey, ridesList);
        }

        for (Map.Entry<CarpoolKey, List<RideDetails>> entry : carpoolMap.entrySet()) {
            CarpoolKey key = entry.getKey();
            List<RideDetails> rideList = entry.getValue();

            if (rideList != null && !rideList.isEmpty()) {
                // Divide the riders in groups of max 4 for carpool
                List<List<RideDetails>> carpoolWiseRidesList = ListUtils.partition(rideList, CarpoolGroup.MAX_CARPOOL_SIZE);

                for (List<RideDetails> carpoolRideList: carpoolWiseRidesList) {
                    // Find available vehicle
                    Vehicle vehicle = CarpoolDao.findFirstAvailableVehicle();
                    if (vehicle != null) {
                        // Find the driver for the vehicle
                        /*Member driver = CarpoolDao.findDriverForVehicle(vehicle.getVehicleId());
                        if (driver == null) {
                            throw new IllegalStateException("No driver for carpool. Please associate a driver for all vehicles in available state.");
                        }*/
                        // Assuming the owner of vehicle is the driver also.
                        Member driver = MembershipDao.getMemberById(vehicle.getOwnerId());
                        // Compute the route for this carpool
                        String route = CarpoolGroup.computeRoute(rideList, routingStrategy);

                        // Build the carpool group
                        CarpoolGroup carpoolGroup = new CarpoolGroup.CarpoolBuilder(carpoolRideList)
                                .capacity(CarpoolGroup.MAX_CARPOOL_SIZE)
                                .driver(driver)
                                .location(CarpoolDao.getLocation(key.getLocationId()))
                                .pickupTime(key.getPickupTime())
                                .vehicle(vehicle)
                                .route(route)
                                .build();

                        // Schedule the carpool
                        carpoolGroup.createCarpool();

                    } else {
                        throw new IllegalStateException("No available vehicle for carpool. Please register more vehicles.");
                    }
                }
            }
        }
    }

    @Override
    public void dispatchCarpools() {
        // Get all carpools that are scheduled and dispatch them.
        List<CarpoolDetails> carpoolDetailsList = CarpoolDao.findCarpoolsByStatus(CarpoolStatus.SCHEDULED.name());

        // dispatch the carpools
        for (CarpoolDetails carpoolDetails: carpoolDetailsList) {
            CarpoolDao.dispatchCarpool(carpoolDetails);
            List<Integer> ridesInACarpool = CarpoolDao.findRidesInACarpool(carpoolDetails.getPoolId());
            List<RideDetails> rideDetailsList = new ArrayList<>();
            for (Integer ride: ridesInACarpool) {
                rideDetailsList.add(RideDao.getRideById(ride));
            }

            RideStateContext context = new RideStateContext();
            context.setState(new RideCompletedState(rideDetailsList));
            context.handleInput();
        }
    }

    @Override
    public void completeCarpoolRides() {
        // Get all carpools that are dispatched and mark them as completed.
        List<CarpoolDetails> carpoolDetailsList = CarpoolDao.findCarpoolsByStatus(CarpoolStatus.DISPATCHED.name());

        for (CarpoolDetails carpoolDetails: carpoolDetailsList) {
            CarpoolDao.updateCarpoolStatus(carpoolDetails.getPoolId(), CarpoolStatus.COMPLETED.name());
            List<Integer> ridesList = CarpoolDao.findRidesInACarpool(carpoolDetails.getPoolId());
            RideDao.updateRideStatus(RideStatus.COMPLETED.name(), ridesList);


            List<RideDetails> rideDetailsList = new ArrayList<>();
            for (Integer ride: ridesList) {
                rideDetailsList.add(RideDao.getRideById(ride));
            }

            RideStateContext context = new RideStateContext();
            context.setState(new RideCompletedState(rideDetailsList));
            context.handleInput();
        }
    }

    @Override
    public void acceptPaymentForRide() {
        paymentProcessor.processPayment();
    }
}
