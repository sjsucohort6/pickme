package edu.sjsu.cmpe202.graph;

/**
 * @author rwatsh on 8/12/16.
 */
import lombok.Data;

import java.util.List;

@Data
public class Graph {
    private final List<Vertex> vertexes;
    private final List<Edge> edges;

    public Graph(List<Vertex> vertexes, List<Edge> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    public List<Vertex> getVertexes() {
        return vertexes;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
