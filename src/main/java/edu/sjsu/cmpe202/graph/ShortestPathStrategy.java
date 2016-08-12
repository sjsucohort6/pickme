package edu.sjsu.cmpe202.graph;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author rwatsh on 8/12/16.
 */
public class ShortestPathStrategy implements RoutingStrategy {
    private DijkstraAlgorithm algorithm;

    public ShortestPathStrategy(DijkstraAlgorithm algorithm) {
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
            Vertex node = getMinimumDistance(unSettledNodes);
            algorithm.getSettledNodes().add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    public Vertex getMinimumDistance(Set<Vertex> vertexes) {
        Vertex minimum = null;
        for (Vertex vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private void findMinimalDistances(Vertex node) {
        List<Vertex> adjacentNodes = algorithm.getNeighbors(node);
        for (Vertex target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                algorithm.getDistance().put(target, getShortestDistance(node)
                        + getDistance(node, target));
                algorithm.getPredecessors().put(target, node);
                algorithm.getUnSettledNodes().add(target);
            }
        }

    }

    private int getShortestDistance(Vertex destination) {
        Integer d = algorithm.getDistance().get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    private int getDistance(Vertex node, Vertex target) {
        for (Edge edge : algorithm.getEdges()) {
            if ((edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) ||
                    (edge.getSource().equals(target)
                            && edge.getDestination().equals(node))) {
                return edge.getWeightDistance();
            }
        }
        throw new RuntimeException("Should not happen");
    }

}
