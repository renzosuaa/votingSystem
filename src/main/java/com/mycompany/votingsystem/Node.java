package com.mycompany.votingsystem;

import java.util.LinkedList;


public class Node {
   int ID;
   LinkedList<Node> vertex;
   
   Node(int ID){
       this.ID = ID;
       vertex = new LinkedList<>();
   }
   
   void addVertex(Node node){
       this.vertex.add(node);
   }
    

}
