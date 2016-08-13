package edu.sjsu.cmpe202.graph;

/**
 * @author rwatsh on 8/12/16.
 */
import lombok.Data;

import java.util.*;

@Data
public class DijkstraAlgorithm {

    protected final List<Vertex> nodes;
    protected final List<Edge> edges;
    protected Set<Vertex> settledNodes;
    protected Set<Vertex> unSettledNodes;
    protected Map<Vertex, Vertex> predecessors;
    protected Map<Vertex, Integer> distance;
    protected Map<Vertex, Integer> time;
    protected RoutingStrategy routingStrategy = null;

    public DijkstraAlgorithm(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<Vertex>(graph.getVertexes());
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }

    /**
     * Set the routing strategy.
     */
    public void setRoutingStrategy(RoutingStrategy strategy) {
        this.routingStrategy = strategy;
    }

    public void execute(Vertex source) {
        if (routingStrategy == null) {
            // default to shortest path strategy
            routingStrategy = new ShortestPathStrategy(this);
        }
        routingStrategy.execute(source);
    }



    public List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }



    private boolean isSettled(Vertex vertex) {
        return settledNodes.contains(vertex);
    }



    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<Vertex> getPath(Vertex target) {
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        Vertex step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

}
