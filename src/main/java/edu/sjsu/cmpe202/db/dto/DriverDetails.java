package edu.sjsu.cmpe202.db.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author rwatsh on 8/7/16.
 */
@Data
public class DriverDetails {
    private int id;
    private int memberId;
    private String licenseNumber;
    private Date expiryDate;
}
