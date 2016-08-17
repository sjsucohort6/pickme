package edu.sjsu.cmpe202.db.dao;

import edu.sjsu.cmpe202.facade.Membership;
import edu.sjsu.cmpe202.db.SQLConnection;
import edu.sjsu.cmpe202.db.domain.DriverDetails;
import edu.sjsu.cmpe202.db.domain.Member;
import org.sql2o.Connection;

/**
 * @author rwatsh on 8/12/16.
 */
public class MembershipDao {

    public static Integer createRider(Membership membership) {
        String sql =
                "INSERT INTO member (first_name, last_name, dob, address, contact, email, is_driver) " +
                        "VALUES (:first_name, :last_name, :dob, :address, :contact, :email, :is_driver)";

        try (Connection con = (new SQLConnection()).getConnection()) {
            return con.createQuery(sql)
                    .addParameter("first_name", membership.getFirstName())
                    .addParameter("last_name", membership.getLastName())
                    .addParameter("dob", membership.getDob())
                    .addParameter("address", membership.getAddress())
                    .addParameter("contact", membership.getPhone())
                    .addParameter("email", membership.getEmail())
                    .addParameter("is_driver", "N")
                    .executeUpdate()
                    .getKey(Integer.class);
        }
    }

    public static Integer createDriver(Membership membership) {
        String memberSql =
                "INSERT INTO member (first_name, last_name, dob, address, contact, email, is_driver) " +
                        "VALUES (:first_name, :last_name, :dob, :address, :contact, :email, :is_driver)";

        String fetchMemberSql = "SELECT member_id FROM member WHERE email = :email";

        String driverSql = "INSERT INTO driver_details (member_id, license_number, expiry_date) " +
                "VALUES (:member_id, :license_number, :expiry_date)";

        try (Connection con = (new SQLConnection()).getConnection()) {
             int driverId = con.createQuery(memberSql)
                    .addParameter("first_name", membership.getFirstName())
                    .addParameter("last_name", membership.getLastName())
                    .addParameter("dob", membership.getDob())
                    .addParameter("address", membership.getAddress())
                    .addParameter("contact", membership.getPhone())
                    .addParameter("email", membership.getEmail())
                    .addParameter("is_driver", "Y")
                    .executeUpdate().getKey(Integer.class);

             con.createQuery(driverSql)
                    .addParameter("member_id", driverId)
                    .addParameter("license_number", membership.getDriverLicence())
                    .addParameter("expiry_date", membership.getExpiration())
                    .executeUpdate();
            return driverId;
        }

    }

    public static DriverDetails getDriverDetailsFromMemberId(int memberId) {
        String fetchMemberSql = "SELECT * FROM driver_details WHERE member_id = :member_id";
        try (Connection con = (new SQLConnection()).getConnection()) {
            return con.createQuery(fetchMemberSql)
                    .addParameter("member_id", memberId)
                    .executeAndFetchFirst(DriverDetails.class);
        }
    }

    public static Member getMemberById(int id) {
        String fetchMemberSql = "SELECT * FROM member WHERE member_id = :member_id";
        try (Connection con = (new SQLConnection()).getConnection()) {
            return con.createQuery(fetchMemberSql)
                    .addParameter("member_id", id)
                    .executeAndFetchFirst(Member.class);
        }
    }

    public static Member getMemberByEmail(String email) {
        String fetchMemberSql = "SELECT * FROM member WHERE email = :email";
        try (Connection con = (new SQLConnection()).getConnection()) {
            return con.createQuery(fetchMemberSql)
                    .addParameter("email", email)
                    .executeAndFetchFirst(Member.class);
        }
    }
}
