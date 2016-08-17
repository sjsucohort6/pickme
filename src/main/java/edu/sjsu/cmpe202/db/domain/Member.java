package edu.sjsu.cmpe202.db.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author rwatsh on 8/7/16.
 */
@Data
public class Member {
    private int memberId;
    private String firstName;
    private String lastName;
    private Date dob;
    private String address;
    private int contact;
    private String email;
    private String isDriver;
}
