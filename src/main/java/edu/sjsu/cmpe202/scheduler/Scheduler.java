package edu.sjsu.cmpe202.scheduler;

import edu.sjsu.cmpe202.cli.CarpoolStatus;
import edu.sjsu.cmpe202.cli.RideStatus;
import edu.sjsu.cmpe202.db.dao.CarpoolDao;
import edu.sjsu.cmpe202.db.dao.MembershipDao;
import edu.sjsu.cmpe202.db.dao.RideDao;
import edu.sjsu.cmpe202.db.domain.CarpoolDetails;
import edu.sjsu.cmpe202.db.domain.Member;
import edu.sjsu.cmpe202.db.domain.RideDetails;
import edu.sjsu.cmpe202.db.domain.Vehicle;
import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Scheduler schedules the rides to carpools.
 *
 * The logic currently is simply to put the rides with same origin location and same pickup time in one carpool
 * with a max carpool group size of 4.
 *
 * A singleton enum.
 *
 * @author rwatsh on 8/13/16.
 */
public enum Scheduler {

    INSTANCE;

    private RideDetails rideDetails;

    public void scheduleRides() {
        Map<CarpoolKey,List<RideDetails>> carpoolMap = new HashMap<>();

        // Look up ride table for rides that are in pending state
        // and check the pickup time and src location
        List<RideDetails> rideDetailsList = RideDao.getRidesByStatus(RideStatus.PENDING.name());

        /*
         * For rides that start at the same time and from same location
         * put them in carpool with a max of 4 people in one carpool group.
         */
        for (RideDetails ride: rideDetailsList) {
            List<RideDetails> ridesList = carpoolMap.get(new CarpoolKey(ride.getSourceId(), ride.getStartDate()));
            if (ridesList == null) {
                ridesList = new ArrayList<>();
            }
            ridesList.add(ride);
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
                        CarpoolGroup carpoolGroup = new CarpoolGroup.CarpoolBuilder(carpoolRideList)
                                .capacity(CarpoolGroup.MAX_CARPOOL_SIZE)
                                .driver(driver)
                                .location(CarpoolDao.getLocation(key.getLocationId()))
                                .pickupTime(key.getPickupTime())
                                .vehicle(vehicle)
                                .build();
                        carpoolGroup.createCarpool();

                    } else {
                        throw new IllegalStateException("No available vehicle for carpool. Please register more vehicles.");
                    }
                }
            }
        }
    }

    public void dispatchCarpools() {
        // Get all carpools that are scheduled and dispatch them.
        List<CarpoolDetails> carpoolDetailsList = CarpoolDao.findCarpoolsByStatus(CarpoolStatus.SCHEDULED.name());

        // dispatch the carpools
        for (CarpoolDetails carpoolDetails: carpoolDetailsList) {
            CarpoolDao.dispatchCarpool(carpoolDetails);
        }
    }
}
