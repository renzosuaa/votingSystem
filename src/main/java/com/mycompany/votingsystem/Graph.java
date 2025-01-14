package com.mycompany.votingsystem;

import java.util.HashMap;

public class Graph {
    
    HashMap<Integer,Node> graph = new HashMap<>();
    
    void add(int nodeID, int ID){
        if (!graph.containsKey(nodeID)||graph == null){
            graph.put(ID, new Node(ID));
        } else {
            graph.get(nodeID).addVertex(new Node(ID));
        }
    }
}
    
    

    
   
