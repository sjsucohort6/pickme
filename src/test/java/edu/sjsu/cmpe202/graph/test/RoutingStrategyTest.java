package edu.sjsu.cmpe202.graph.test;

import edu.sjsu.cmpe202.graph.*;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author rwatsh on 8/12/16.
 */
public class RoutingStrategyTest extends TestCase {

    private Graph graph;

    @BeforeClass
    public void setUp() {
        List<Vertex> vertices = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        Vertex a = new Vertex("1", "A");
        vertices.add(a);
        Vertex b = new Vertex("2", "B");
        vertices.add(b);
        Vertex c = new Vertex("3", "C");
        vertices.add(c);
        Vertex d = new Vertex("4", "D");
        vertices.add(d);
        Vertex e = new Vertex("5", "E");
        vertices.add(e);
        Vertex f = new Vertex("6", "F");
        vertices.add(f);

        edges.add(new Edge("E1", a, b, 7, 7));
        edges.add(new Edge("E2", a, c, 20, 19));
        edges.add(new Edge("E3", b, c, 10, 10));
        edges.add(new Edge("E4", b, d, 15, 15));
        edges.add(new Edge("E5", c, d, 11, 11));
        edges.add(new Edge("E6", c, f, 2, 2));
        edges.add(new Edge("E7", d, e, 6, 6));
        edges.add(new Edge("E8", e, f, 9, 9));
        edges.add(new Edge("E9", f, a, 14, 14));
        graph = new Graph(vertices, edges);
    }

    @Test
    public void testShortestPath() {
        DijkstraAlgorithm algorithm = new DijkstraAlgorithm(graph);
        algorithm.setRoutingStrategy(new ShortestPathStrategy(algorithm));
        Vertex a = new Vertex("1", "A");
        Vertex f = new Vertex("6", "F");
        algorithm.execute(a);
        LinkedList<Vertex> path = algorithm.getPath(f);
        System.out.println(path);
        assertNotNull(path);
        assertTrue(path.size() > 0);
    }

    @Test
    public void testFastestTime() {
        DijkstraAlgorithm algorithm = new DijkstraAlgorithm(graph);
        algorithm.setRoutingStrategy(new FastestTimeStrategy(algorithm));
        Vertex a = new Vertex("1", "A");
        Vertex f = new Vertex("6", "F");
        algorithm.execute(a);
        LinkedList<Vertex> path = algorithm.getPath(f);
        System.out.println(path);
        assertNotNull(path);
        assertTrue(path.size() > 0);
    }

    @Test
    public void testUndirectedShortestPath() {
        DijkstraAlgorithm algorithm = new DijkstraAlgorithm(new UndirectedGraphDecorator(graph));
        algorithm.setRoutingStrategy(new ShortestPathStrategy(algorithm));
        Vertex a = new Vertex("1", "A");
        Vertex f = new Vertex("6", "F");
        algorithm.execute(a);
        LinkedList<Vertex> path = algorithm.getPath(f);
        System.out.println(path);
        assertNotNull(path);
        assertTrue(path.size() > 0);
    }
}