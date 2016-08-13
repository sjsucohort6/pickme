package edu.sjsu.cmpe202.cli;

import lombok.Data;

/**
 * @author rwatsh on 8/6/16.
 */
@Data
public class Payment {
    private String memberEmailId;
    private String cardNumber;
    private String cardType;
    private String expiryDate;
}
