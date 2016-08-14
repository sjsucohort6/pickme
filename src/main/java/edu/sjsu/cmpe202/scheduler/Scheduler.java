package edu.sjsu.cmpe202.scheduler;

import edu.sjsu.cmpe202.db.dao.RideDao;
import edu.sjsu.cmpe202.db.domain.RideDetails;

import java.util.*;

/**
 * @author rwatsh on 8/13/16.
 */
public class Scheduler {

    private RideDetails rideDetails;

    public void scheduleRides() {
        Map<Integer,List<Integer>> srcLocationToRideMap = new HashMap<>();
        Map<Date, List<Integer>> pickupTimeToRideMap = new HashMap<>();

        // Look up ride table for rides that are in pending state
        // and check the pickup time and src location
        List<RideDetails> rideDetailsList = RideDao.getPendingRides();

        for (RideDetails ride: rideDetailsList) {
            List<Integer> ridesListForSameLocation = srcLocationToRideMap.get(ride.getSourceId());
            if (ridesListForSameLocation == null) {
                ridesListForSameLocation = new ArrayList<>();
            }
            ridesListForSameLocation.add(ride.getRideId());

            List<Integer> ridesListForSamePickupTime = pickupTimeToRideMap.get(ride.getStartDate());
            if (ridesListForSamePickupTime == null) {
                ridesListForSamePickupTime = new ArrayList<>();
            }
            ridesListForSamePickupTime.add(ride.getRideId());

            // determine if carpool possible for 2 rides?
            for (Map.Entry<Integer, List<Integer>> entry : srcLocationToRideMap.entrySet()) {
                // if src location is same
                int srcLocationId = entry.getKey();
                List<Integer> rideIdList = entry.getValue();

                // Sort by pickup times
                Set<Date> dates = pickupTimeToRideMap.keySet();
                List<Date> datesList = new ArrayList<>();
                datesList.addAll(dates);

                Collections.sort(datesList, new Comparator<Date>() {
                    @Override
                    public int compare(Date lhs, Date rhs) {
                        if (lhs.getTime() < rhs.getTime())
                            return -1;
                        else if (lhs.getTime() == rhs.getTime())
                            return 0;
                        else
                            return 1;
                    }
                });

                // create carpools
                // if pickup times is within 30 mins
                // and size of carpool is not more than 4 riders
                int maxRidersCount = 4;
                int ridersCount = 1;
                Date previousPickupTime = null;
                CarpoolGroup carpoolGroup = null;

                for (Date pickupTime: datesList) {
                    List<Integer> ridesWithSamePickupTime = pickupTimeToRideMap.get(pickupTime);

                    // TBD - also get rides with pickup times within 30 mins boundary

                    long diff = pickupTime.getTime() - ((previousPickupTime != null) ? previousPickupTime.getTime(): 0);
                    long diffMinutes = diff / (60 * 1000) % 60;

                    for (Integer id: ridesWithSamePickupTime) {
                        if ((ridersCount > maxRidersCount) || (previousPickupTime == null) || (diffMinutes > 30)) {
                            ridersCount = 1;
                            carpoolGroup = new CarpoolGroup(maxRidersCount, pickupTime);
                            previousPickupTime = pickupTime;
                        } else {
                            carpoolGroup.addRide(id);
                            ridersCount++;
                            previousPickupTime = pickupTime;
                        }
                    }



                }




            }

        }

    }

}
