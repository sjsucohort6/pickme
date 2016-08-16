package edu.sjsu.cmpe202.graph;

/**
 * Directed graph implementation.
 *
 * @author rwatsh on 8/12/16.
 */
import lombok.Data;

import java.util.List;

@Data
public class Graph implements GraphInterface {
    private final List<Vertex> vertexes;
    private final List<Edge> edges;

    public Graph(List<Vertex> vertexes, List<Edge> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    @Override
    public List<Vertex> getVertexes() {
        return vertexes;
    }

    @Override
    public List<Edge> getEdges() {
        return edges;
    }
}
