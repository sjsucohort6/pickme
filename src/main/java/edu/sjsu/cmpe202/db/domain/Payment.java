package edu.sjsu.cmpe202.db.domain;

import lombok.Data;

/**
 * @author rwatsh on 8/14/16.
 */
@Data
public class Payment {
    private int paymentId;
    private int memberId;
    private int carpoolId;
    private String amount;
    private String status;
}
