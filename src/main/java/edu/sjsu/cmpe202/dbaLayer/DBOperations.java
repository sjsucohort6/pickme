package edu.sjsu.cmpe202.dbaLayer;

import edu.sjsu.cmpe202.cli.Membership;
import org.sql2o.Connection;

public class DBOperations {


    public static void createRider(Membership membership) {
        String sql =
                "INSERT INTO member (first_name, last_name, dob, address, contact, email, is_driver) " +
                        "VALUES (:first_name, :last_name, :dob, :address, :contact, :email, :is_driver)";

        try (Connection con = (new SQLConnection()).getConnection()) {
            con.createQuery(sql)
                    .addParameter("first_name", membership.getFirstName())
                    .addParameter("last_name", membership.getLastName())
                    .addParameter("dob", membership.getDob())
                    .addParameter("address", membership.getAddress())
                    .addParameter("contact", membership.getPhone())
                    .addParameter("email", membership.getEmail())
                    .addParameter("is_driver", "N")
                    .executeUpdate();
        }

    }

}
