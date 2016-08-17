package edu.sjsu.cmpe202.db.dao;

import edu.sjsu.cmpe202.db.domain.ParkingDetails;
import edu.sjsu.cmpe202.facade.Payment;
import edu.sjsu.cmpe202.facade.PaymentStatus;
import edu.sjsu.cmpe202.db.SQLConnection;
import edu.sjsu.cmpe202.db.domain.CarpoolDetails;
import edu.sjsu.cmpe202.db.domain.Member;
import org.sql2o.Connection;

import java.util.List;

import static java.sql.Types.NULL;

/**
 * Created by Ashutosh on 8/13/2016.
 */
public class PaymentDao {

    public static void addCreditCard(Payment payment) {

        String fetchMemberId = "SELECT member_id from member WHERE email = :email ";
        String sql =
                "INSERT INTO payment_details(member_id, card_number, card_type, expiry_date)" +
                        "VALUES (:member_id, :card_number, :card_type, :expiry_date)";


        try (Connection con = (new SQLConnection()).getConnection()) {

            Member m = MembershipDao.getMemberByEmail(payment.getMemberEmailId());
            con.createQuery(sql)
                    .addParameter("member_id", m.getMemberId())
                    .addParameter("card_number", payment.getCardNumber())
                    .addParameter("card_type", payment.getCardType())
                    .addParameter("expiry_date", payment.getExpiryDate())
                    .executeUpdate();

        }

    }

    public static List<Payment> showPayment(String memberEmailId) {
        String paymentHistory = "SELECT  member_id ,card_number, card_type, expiry_date FROM payment_details where member_id = :member_id";
        try (Connection con = (new SQLConnection()).getConnection()) {
            Member m = MembershipDao.getMemberByEmail(memberEmailId);
            List<Payment> paymentDetails = con.createQuery(paymentHistory)
                    .addParameter("member_id", m.getMemberId())
                    .executeAndFetch(Payment.class);

            return paymentDetails;
        }
    }

    public static List<Payment> checkCard(String memberEmailId) {

        String fetchMemberId = "SELECT member_id from member WHERE email = :email ";
        String paymentCheck = "SELECT card_number, card_type, expiry_date FROM payment_details where member_id = :member_id";
        try (Connection con = (new SQLConnection()).getConnection()) {
            Member m = MembershipDao.getMemberByEmail(memberEmailId);
            List<Payment> cardCheck = con.createQuery(paymentCheck)
                    .addParameter("member_id", m.getMemberId())
                    .executeAndFetch(Payment.class);

            return cardCheck;
        }

    }

    public static void initiatePayment(Payment payment) {
        String paymentInit = "INSERT INTO payment(member_id, carpool_id,parking_id amount, status) " +
                "VALUES (:member_id, :carpool_id,parking_id :amount, :status)";
        try (Connection con = (new SQLConnection()).getConnection()) {
            Member m = MembershipDao.getMemberByEmail(payment.getMemberEmailId());
            con.createQuery(paymentInit)
                    .addParameter("member_id", m.getMemberId())
                    .addParameter("carpool_id", payment.getCarpoolId())
                    .addParameter("parking_id", NULL)
                    .addParameter("amount", payment.getAmount())
                    .addParameter("status", "PAID")
                    .executeUpdate();
            updatePaymentStatus(m.getMemberId(), PaymentStatus.PAID.name());
        }
    }

    public static List<CarpoolDetails> getCount(String memberEmailId, String carpoolId) {
        String fetchMemberId = "SELECT member_id from member WHERE email = :email ";
        String getCount = "SELECT * from carpool_details WHERE pool_id = :pool_id ";
        try (Connection con = (new SQLConnection()).getConnection()) {
            List<Member> memberId = con.createQuery(fetchMemberId)
                    .addParameter("email", memberEmailId)
                    .executeAndFetch(Member.class);
            Member m = memberId.get(0);
            List<CarpoolDetails> carpoolInfo = con.createQuery(getCount)
                    .addParameter("pool_id", carpoolId)
                    .executeAndFetch(CarpoolDetails.class);
            //System.out.println("Details" + carpoolInfo);
            return carpoolInfo;
        }
    }

    public static void updatePaymentStatus(int userId, String status) {
        final String sql = "UPDATE ride_details set payment_status = :status where user_id = :user_id";

        try (Connection con = (new SQLConnection()).getConnection()) {
            con.createQuery(sql)
                    .addParameter("user_id", userId)
                    .addParameter("status", status)
                    .executeUpdate();
        }
    }


    public static void parkingPayment(Payment payment) {
        String fetchParkingDetails = "select parking_id from parking_details where parker_id = :parker_id";
        String parkingPayment = " INSERT INTO payment(member_id, carpool_id,parking_id,amount,status) " +
                "VALUES (:member_id, :carpool_id,:parking_id ,:amount, :status)";
        try (Connection con = (new SQLConnection()).getConnection()) {
            Member m = MembershipDao.getMemberByEmail(payment.getMemberEmailId());
            List<ParkingDetails> parkingInfo = con.createQuery(fetchParkingDetails)
                    .addParameter("parker_id", m.getMemberId())
                    .executeAndFetch(ParkingDetails.class);
            ParkingDetails  p = parkingInfo.get(0);

            con.createQuery(parkingPayment)
                    .addParameter("member_id", m.getMemberId())
                    .addParameter("carpool_id", NULL)
                    .addParameter("parking_id", p.getParkingId())
                    .addParameter("amount", payment.getAmount())
                    .addParameter("status", "PAID")
                    .executeUpdate();
        }
    }

}
