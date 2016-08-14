package edu.sjsu.cmpe202.db.dao;

import edu.sjsu.cmpe202.cli.Payment;
import edu.sjsu.cmpe202.db.SQLConnection;
import edu.sjsu.cmpe202.db.domain.Member;
import org.sql2o.Connection;

import java.util.List;

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
            List<Member> memberId = con.createQuery(fetchMemberId)
                    .addParameter("email", payment.getMemberEmailId())
                    .executeAndFetch(Member.class);

            Member m = memberId.get(0);
            con.createQuery(sql)
                    .addParameter("member_id", m.getMemberId())
                    .addParameter("card_number", payment.getCardNumber())
                    .addParameter("card_type", payment.getCardType())
                    .addParameter("expiry_date", payment.getExpiryDate())
                    .executeUpdate();

        }

    }

    public static List<Payment> showPayment(String memberEmailId) {
        String fetchMemberId = "SELECT member_id from member WHERE email = :email ";
        String paymentHistory = "SELECT  member_id ,card_number, card_type, expiry_date FROM payment_details where member_id = :member_id";
        try (Connection con = (new SQLConnection()).getConnection()) {
            List<Member> memberId = con.createQuery(fetchMemberId)
                    .addParameter("email", memberEmailId)
                    .executeAndFetch(Member.class);
            Member m = memberId.get(0);
            // System.out.println("Member Id"+ memberId);
            List<Payment> paymentDetails = con.createQuery(paymentHistory)
                    .addParameter("member_id", m.getMemberId())
                    .executeAndFetch(Payment.class);

            return paymentDetails;
        }
    }
}


