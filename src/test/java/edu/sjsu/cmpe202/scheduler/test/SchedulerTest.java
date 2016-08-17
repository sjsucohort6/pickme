package edu.sjsu.cmpe202.scheduler.test;

import edu.sjsu.cmpe202.facade.*;
import edu.sjsu.cmpe202.db.dao.MembershipDao;
import edu.sjsu.cmpe202.db.dao.RideDao;
import edu.sjsu.cmpe202.db.dao.VehicleDao;
import edu.sjsu.cmpe202.db.domain.RideDetails;
import edu.sjsu.cmpe202.scheduler.CarpoolScheduler;
import edu.sjsu.cmpe202.scheduler.Scheduler;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

/**
 * @author rwatsh on 8/15/16.
 */
public class SchedulerTest {
    private static Integer riderId;
    private static Integer driverId;
    private static RideDetails rideDetails;


    @BeforeClass
    public static void setupTest() {
        Membership rider = createMember();


        Membership driver = createDriver();

        createVehicle(driver);

        createRide(rider);
    }

    private static void createVehicle(Membership driver) {
        VehicleRegistration v = new VehicleRegistration();
        v.setStatus(VehicleStatus.AVAILABLE.name());
        v.setCapacity("4");
        v.setMailId(driver.getEmail());
        v.setOwnerID(driverId);
        v.setRegistration_id("66889");
        VehicleDao.createVehicle(v);
    }

    private static void createRide(Membership rider) {
        Ride ride = new Ride();
        ride.setStatus(RideStatus.PENDING.name());
        ride.setCreateDate(Utilities.dateFormat.format(new Date()));
        ride.setStartDate(Utilities.dateTimeFormat.format(new Date()));
        ride.setDestid("6");
        ride.setSourceid("1");
        ride.setEmailID(rider.getEmail());
        ride.setUserid(riderId);
        rideDetails = RideDao.addRideRequest(ride);
    }

    private static Membership createDriver() {
        Membership driver = new Membership();
        driver.setAddress("Def");
        driver.setDob("1999-09-09");
        driver.setEmail("def@gmail.com");
        driver.setFirstName("Joe");
        driver.setLastName("Moe");
        driver.setPhone("123");
        driver.setDriverLicence("55677");
        driver.setExpiration("2018-01-01");
        driverId = MembershipDao.createDriver(driver);
        return driver;
    }

    private static Membership createMember() {
        Membership rider = new Membership();
        rider.setAddress("Abc");
        rider.setDob("1999-09-09");
        rider.setEmail("abc@gmail.com");
        rider.setFirstName("Watsh");
        rider.setLastName("Rajneesh");
        rider.setPhone("567");
        riderId = MembershipDao.createRider(rider);
        return rider;
    }

    @Test
    public void testSchedulerWithTemplateMethod() {
        Scheduler scheduler = CarpoolScheduler.getInstance();
        scheduler.scheduleDispatchAndCompleteARide(rideDetails);
    }
}
