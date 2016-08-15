package edu.sjsu.cmpe202.cli;

import edu.sjsu.cmpe202.db.dao.PaymentDao;
import lombok.Data;

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


    public void printPaymentMenu() {
        // TODO Auto-generated method stub
        System.out.println("\t [1] Add Card");
        System.out.println("\t [2] Ride Payment");
        System.out.println("\t [3] Parking Payment");
        System.out.println("\t [4] Go back to main menu");

    }

    public void handleRidePayment() {
        // TODO Auto-generated method stub
        System.out.println("Please Confirm your Payment");

    }

    public void handleParkingPayment() {
        // TODO Auto-generated method stub

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
        memberEmailId= scanner.nextLine();
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


    public void handlePaymentDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t Enter email id");
        memberEmailId = scanner.nextLine();
        System.out.println("\t Payment Details ");
        List<Payment> payment = PaymentDao.showPayment(memberEmailId);
        showPaymentDetails(payment);


    }
}
