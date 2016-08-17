package edu.sjsu.cmpe202.db.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author rwatsh on 8/15/16.
 */
@Data
public class RidesByMember {
    private int memberId;
    private String firstName;
    private String lastName;
    private String email;
    private String location; //source location
    private String destLocation;
    private Date createDate;
    private Date startDate;
    private String status; // ride status
    private String paymentStatus;
    private String totalRides;
}
