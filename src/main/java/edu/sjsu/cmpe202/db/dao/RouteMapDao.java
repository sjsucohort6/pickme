package edu.sjsu.cmpe202.db.dao;

import edu.sjsu.cmpe202.db.SQLConnection;
import edu.sjsu.cmpe202.db.domain.Location;
import edu.sjsu.cmpe202.db.domain.RouteMap;
import org.sql2o.Connection;

import java.util.List;

/**
 * @author rwatsh on 8/12/16.
 */
public class RouteMapDao {
    public static List<RouteMap> getAllRouteMaps() {

        String fetchRouteMapSql = "SELECT * FROM route_map";


        try (Connection con = (new SQLConnection()).getConnection()) {
            return con.createQuery(fetchRouteMapSql)
                    .executeAndFetch(RouteMap.class);
        }

    }

    public static List<Location> getAllLocations() {

        String fetchLocationSql = "SELECT * FROM location";


        try (Connection con = (new SQLConnection()).getConnection()) {
            return con.createQuery(fetchLocationSql)
                    .executeAndFetch(Location.class);
        }

    }

    public static Location getLocationById(int id) {
        String fetchLocationSql = "SELECT * FROM location WHERE location_id = :location_id";


        try (Connection con = (new SQLConnection()).getConnection()) {
            return con.createQuery(fetchLocationSql)
                    .addParameter("location_id", id)
                    .executeAndFetchFirst(Location.class);
        }
    }
}
