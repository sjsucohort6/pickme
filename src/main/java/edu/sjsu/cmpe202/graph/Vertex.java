package edu.sjsu.cmpe202.graph;

import lombok.Data;

/**
 * @author rwatsh on 8/12/16.
 */
@Data
public class Vertex {
    final private String id;
    final private String name;


    public Vertex(String id, String name) {
        this.id = id;
        this.name = name;
    }


}
