package edu.sjsu.cmpe202.scheduler;

import edu.sjsu.cmpe202.cli.CarpoolStatus;
import edu.sjsu.cmpe202.cli.RideStatus;
import edu.sjsu.cmpe202.db.dao.CarpoolDao;
import edu.sjsu.cmpe202.db.dao.RideDao;
import edu.sjsu.cmpe202.db.domain.*;
import lombok.Data;

import java.util.Date;
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

    static class CarpoolBuilder {
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
        details.setDriverId(driver.getMemberId());
        details.setPassengerCount(rideList.size());
        details.setRoute(route);
        details.setStatus((rideList.size() >= MAX_CARPOOL_SIZE) ? CarpoolStatus.FULL.name() : CarpoolStatus.HAS_VACANCY.name());
        CarpoolDao.createCarpool(details);
        // Set all rides to scheduled.
        RideDao.updateRideStatus(rideList, RideStatus.SCHEDULED.name());
    }
}
