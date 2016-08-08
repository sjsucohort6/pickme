package edu.sjsu.cmpe202.dbaLayer;

import lombok.Data;

import java.util.Date;

/**
 * @author rwatsh on 8/7/16.
 */
@Data
public class PaymentDetails {
    private int paymentId;
    private int memberId;
    private String cardNumber;
    private String cardType;
    private Date expiryDate;
}
