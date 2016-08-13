package edu.sjsu.cmpe202.graph;

import lombok.Data;

/**
 * @author rwatsh on 8/12/16.
 */
@Data
public class Edge {
    private final String id;
    private final Vertex source;
    private final Vertex destination;
    private final int weightTime;
    private final int weightDistance;

    public Edge(String id, Vertex source, Vertex destination, int weightTime, int weightDistance) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weightTime = weightTime;
        this.weightDistance = weightDistance;
    }
}
