package edu.sjsu.cmpe202.route;

import edu.sjsu.cmpe202.db.dao.RouteMapDao;
import edu.sjsu.cmpe202.db.domain.Location;
import edu.sjsu.cmpe202.db.domain.RouteMap;
import edu.sjsu.cmpe202.graph.Edge;
import edu.sjsu.cmpe202.graph.Graph;
import edu.sjsu.cmpe202.graph.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rwatsh on 8/12/16.
 */
public class RouteMapGraph {

    public static Graph loadRouteMap() {
        List<Location> locations = RouteMapDao.getAllLocations();
        List<RouteMap> routeMaps = RouteMapDao.getAllRouteMaps();

        Map<String, String> locationsMap = new HashMap<>();


        List<Vertex> vertices = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();

        for (Location l : locations) {
            Vertex a = new Vertex("" + l.getLocationId(), l.getName());
            vertices.add(a);
            locationsMap.put("" + l.getLocationId(), l.getName());
        }

        for (RouteMap r: routeMaps) {
            Vertex src = new Vertex("" + r.getLocation1(), locationsMap.get("" + r.getLocation1()));
            Vertex dest = new Vertex("" + r.getLocation2(), locationsMap.get("" + r.getLocation2()));

            edges.add(new Edge("E_" + src.getName() + dest.getName(), src, dest, r.getTime(), r.getDistance()));
        }

        return new Graph(vertices, edges);
    }

}
