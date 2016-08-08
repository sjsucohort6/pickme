package edu.sjsu.cmpe202.db.domain;

import lombok.Data;

/**
 * @author rwatsh on 8/7/16.
 */
@Data
public class RouteMap {
    private int location1;
    private int location2;
    private int distance;
    private int time;
}
