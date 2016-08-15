package edu.sjsu.cmpe202.graph;

import java.util.List;

/**
 * @author rwatsh on 8/12/16.
 */
public abstract class GraphDecorator implements GraphInterface {
    private Graph graph;

    public GraphDecorator(Graph graph) {
        this.graph = graph;
    }

    @Override
    public List<Vertex> getVertexes() {
        return graph.getVertexes();
    }

    @Override
    public List<Edge> getEdges() {
        return graph.getEdges();
    }
}
