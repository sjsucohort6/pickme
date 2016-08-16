package edu.sjsu.cmpe202.ride;

import edu.sjsu.cmpe202.cli.CarpoolStatus;
import edu.sjsu.cmpe202.cli.PickMe;
import edu.sjsu.cmpe202.cli.RideStatus;
import edu.sjsu.cmpe202.cli.VehicleStatus;
import edu.sjsu.cmpe202.db.dao.*;
import edu.sjsu.cmpe202.db.domain.*;
import edu.sjsu.cmpe202.graph.*;
import lombok.Data;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Carpool Builder - uses Builder (creational) pattern.
 *
 * @author rwatsh on 8/13/16.
 */
@Data
public class CarpoolGroup {
    public static final int MAX_CARPOOL_SIZE = 4;

    private List<RideDetails> rideList;
    private int capacity;
    private Date pickupTime;
    private Location location;
    private Vehicle vehicle;
    private Member driver;
    private String route;
    private RideStateContext stateContext = new RideStateContext();

    private NotificationDao notificationDao = new NotificationDao();

    public static class CarpoolBuilder {
        private List<RideDetails> rideList;
        private int capacity;
        private Date pickupTime;
        private Location location;
        private Vehicle vehicle;
        private Member driver;
        private String route;

        public CarpoolBuilder(List<RideDetails> rideList) {
            this.rideList = rideList;
        }

        public CarpoolBuilder capacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public CarpoolBuilder pickupTime(Date pickupTime) {
            this.pickupTime = pickupTime;
            return this;
        }

        public CarpoolBuilder location(Location location) {
            this.location = location;
            return this;
        }

        public CarpoolBuilder vehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
            return this;
        }

        public CarpoolBuilder driver(Member driver) {
            this.driver = driver;
            return this;
        }

        public CarpoolBuilder route(String route) {
            this.route = route;
            return this;
        }

        public CarpoolGroup build() {
            return new CarpoolGroup(this);
        }
    }


    private CarpoolGroup(CarpoolBuilder builder) {
        this.route = builder.route;
        this.pickupTime = builder.pickupTime;
        this.capacity = builder.capacity;
        this.driver = builder.driver;
        this.location = builder.location;
        this.vehicle = builder.vehicle;
        this.rideList = builder.rideList;
    }

    public void createCarpool() {
        CarpoolDetails details = new CarpoolDetails();
        DriverDetails driverDetailsId = MembershipDao.getDriverDetailsFromMemberId(driver.getMemberId());
        details.setDriverId(driverDetailsId.getId());
        details.setPassengerCount(rideList.size());
        details.setVehicleId(vehicle.getVehicleId());
        details.setRoute(route);
        details.setStatus((rideList.size() >= MAX_CARPOOL_SIZE) ? CarpoolStatus.FULL.name() : CarpoolStatus.HAS_VACANCY.name());
        int poolId = CarpoolDao.createCarpool(details);
        CarpoolDao.createDispatcher(poolId, rideList, pickupTime);
        // Set all rides to scheduled.
        RideDao.updateRideStatus(rideList, RideStatus.SCHEDULED.name());
        CarpoolDao.updateCarpoolStatus(poolId, CarpoolStatus.SCHEDULED.name());
        stateContext.setState(new RideScheduledState(rideList));
        stateContext.handleInput();

        if(this.vehicle.getStatus() == VehicleStatus.OUT_OF_ORDER.name()) {
            int notifyUserId = details.getDriverId();
            Date d = new Date();
            String message = "Vehicle Broke Down";
            Notification n = new Notification(notifyUserId,d,message);
            notificationDao.sendNotifications(n);
        }
    }

    /**
     * Computes all shortest path route.
     *
     * @param rideList
     * @param routingStrategy
     * @return
     */
    public static String computeRoute(List<RideDetails> rideList, RoutingStrategy routingStrategy) {
        StringBuilder route = new StringBuilder();

        for (RideDetails ride: rideList) {
            PickMe.algorithm.setRoutingStrategy(routingStrategy);
            Location sourceLocation = RouteMapDao.getLocationById(ride.getSourceId());
            Location destLocation = RouteMapDao.getLocationById(ride.getDestId());
            Vertex src = new Vertex(sourceLocation.getLocationId() + "", sourceLocation.getName());
            Vertex dest = new Vertex(destLocation.getLocationId() + "", destLocation.getName());
            PickMe.algorithm.execute(src);
            LinkedList<Vertex> path = PickMe.algorithm.getPath(dest);
            int i = 0;
            for (Vertex v: path) {
                if (i >= path.size() - 1) {
                    route.append(v.getName());
                } else {
                    route.append(v.getName()).append("->");
                }
                i++;
            }
            route.append("\n");
        }
        return route.toString();
    }
}
