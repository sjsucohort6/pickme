package edu.sjsu.cmpe202.facade;

import edu.sjsu.cmpe202.db.dao.PaymentDao;
import edu.sjsu.cmpe202.db.domain.CarpoolDetails;
import edu.sjsu.cmpe202.db.domain.ParkingDetails;
import edu.sjsu.cmpe202.db.domain.PaymentDetails;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @author rwatsh on 8/6/16.
 */
@Data
public class Payment {
    private String memberEmailId;
    private String cardNumber;
    private String cardType;
    private String expiryDate;
    private String memberId;
    private String carpoolId;
    private int amount;
    private String status;
    private int parkerId;
    private int parkingId;
    private Date startTime;
    private Date endTime;

    public static final int RideAmount = 16;
    public static final int ParkingAmount = 1;

    public void printPaymentMenu() {
        // TODO Auto-generated method stub
        System.out.println("\t [1] Add Card");
        System.out.println("\t [2] Ride Payment");
        System.out.println("\t [3] Parking Payment");
        System.out.println("\t [4] Show Payment Details");
        System.out.println("\t [5] Go back to main menu");

    }

    public void handleRidePayment() {
        System.out.println("Pay for Ride");
        System.out.println("Email ID :");
        Scanner scanner = new Scanner(System.in);
        memberEmailId = scanner.nextLine();
        System.out.println("Pool ID  :");
        carpoolId = scanner.nextLine();
        amount = calculateAmount(RideAmount);
        if (validCard()) {
            PaymentDao.initiatePayment(this);
            System.out.println(" Received Payment of $ " + amount);
        } else {
            System.out.println("Please Add card First");
        }

    }

    private int calculateAmount(int rideAmount) {
        int count = showNoPassenger();
        System.out.println("count" + count);
        int amount = rideAmount / count;
        System.out.println("Amount" + amount);
        return amount;
    }

    private Boolean validCard() {
        List<Payment> card = PaymentDao.checkCard(memberEmailId);
        if (!card.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void handleParkingPayment() {
        // TODO Auto-generated method stub
        System.out.println("Pay for Parking");
        System.out.println("Email ID :");
        Scanner scanner = new Scanner(System.in);
        memberEmailId = scanner.nextLine();
        amount = ParkingAmount;
        PaymentDao.parkingPayment(this);
        System.out.println(" Received Parking Payment of $ " + amount);

    }

    public void addCard() {
        System.out.println("\t Add  Credit Card  Details");
        handleAddCard();
        PaymentDao.addCreditCard(this);
        System.out.println("Card Added");
    }

    private void handleAddCard() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t Email Id: ");
        memberEmailId = scanner.nextLine();
        System.out.println("\t\t Card Number: ");
        cardNumber = scanner.nextLine();
        System.out.println("\t\t Card Type: ");
        cardType = scanner.nextLine();
        System.out.println("\t\t Expiry Date: ");
        expiryDate = scanner.nextLine();
    }

    private void showPaymentDetails(List<Payment> paymentDetails) {
        System.out.println("\t\t MemberId" + " " + "CardNum" + " " + " cardType " + " " + " ExpiryDate ");
        for (Payment p : paymentDetails) {
            System.out.println("\t\t " + p.getMemberId() + " " + p.getCardNumber() + " " + p.getCardType() + " " + p.getExpiryDate());
        }
    }

    public int showNoPassenger() {
        List<CarpoolDetails> count = PaymentDao.getCount(memberEmailId, carpoolId);
        CarpoolDetails cd = count.get(0);
        int countPassenger = cd.getPassengerCount();
        return countPassenger;
    }

    public void handlePaymentDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t Enter email id");
        memberEmailId = scanner.nextLine();
        System.out.println("\t Payment Details ");
        List<Payment> payment = PaymentDao.showPayment(memberEmailId);
        showPaymentDetails(payment);

    }
}

