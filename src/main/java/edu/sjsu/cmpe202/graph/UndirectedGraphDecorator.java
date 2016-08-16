package edu.sjsu.cmpe202.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Decorator for undirected graph.
 *
 * @author rwatsh on 8/14/16.
 */
public class UndirectedGraphDecorator extends GraphDecorator {
    public UndirectedGraphDecorator(Graph g) {
        super(g);
    }

    public List<Vertex> getVertexes() {
        return super.getVertexes();
    }

    /**
     * This method returns the modified (decorated) edges list.
     *
     * @return
     */
    public List<Edge> getEdges() {
        List<Edge> edges = super.getEdges();
        List<Edge> revEdges = new ArrayList<>();
        // For each edge create a reverse edge
        for (Edge e: edges) {
            revEdges.add(new Edge("R" + e.getId(), e.getDestination(), e.getSource(), e.getWeightTime(), e.getWeightDistance()));
        }
        edges.addAll(revEdges);
        return edges;
    }
}
