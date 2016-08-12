package edu.sjsu.cmpe202.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author rwatsh on 8/12/16.
 */
public class FastestTimeStrategy implements RoutingStrategy {
    private DijkstraAlgorithm algorithm;

    public FastestTimeStrategy(DijkstraAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public void execute(Vertex source) {
        algorithm.setSettledNodes(new HashSet<Vertex>());
        algorithm.setUnSettledNodes(new HashSet<Vertex>());
        algorithm.setDistance(new HashMap<Vertex, Integer>());
        algorithm.setTime(new HashMap<Vertex, Integer>());
        algorithm.setPredecessors(new HashMap<Vertex, Vertex>());
        algorithm.getDistance().put(source, 0);
        algorithm.getTime().put(source, 0);
        Set<Vertex> unSettledNodes = algorithm.getUnSettledNodes();
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Vertex node = getMinimumTime(unSettledNodes);
            algorithm.getSettledNodes().add(node);
            unSettledNodes.remove(node);
            findMinimalTimes(node);
        }
    }

    public Vertex getMinimumTime(Set<Vertex> vertexes) {
        Vertex minimum = null;
        for (Vertex vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getFastestTime(vertex) < getFastestTime(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private void findMinimalTimes(Vertex node) {
        List<Vertex> adjacentNodes = algorithm.getNeighbors(node);
        for (Vertex target : adjacentNodes) {
            if (getFastestTime(target) > getFastestTime(node)
                    + getTime(node, target)) {
                algorithm.getTime().put(target, getFastestTime(node)
                        + getTime(node, target));
                algorithm.getPredecessors().put(target, node);
                algorithm.getUnSettledNodes().add(target);
            }
        }

    }

    private int getFastestTime(Vertex destination) {
        Integer d = algorithm.getTime().get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    private int getTime(Vertex node, Vertex target) {
        for (Edge edge : algorithm.getEdges()) {
            if ((edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) ||
                    (edge.getSource().equals(target)
                            && edge.getDestination().equals(node))) {
                return edge.getWeightTime();
            }
        }
        throw new RuntimeException("Should not happen");
    }
}
