package com.interview.graph;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


/**
 * http://www.geeksforgeeks.org/detect-cycle-in-a-graph/
 */
public class CycleInDirectedGraph {
	List<Vertex<Integer>> cycle = new LinkedList<Vertex<Integer>>();

    public boolean hasCycle(Graph<Integer> graph) {
        Set<Vertex<Integer>> whiteSet = new HashSet<>();
        Set<Vertex<Integer>> graySet = new HashSet<>();
        Set<Vertex<Integer>> blackSet = new HashSet<>();

        for (Vertex<Integer> vertex : graph.getAllVertex()) {
            whiteSet.add(vertex);
        }
        System.out.println("whiteSet="+whiteSet);

        System.out.println(String.format("hasCycle ( %s ) \t%-20s \t%-20s\t%-20s <-- R", "V", "whiteSet", "graySet", "blackSet"));
        while (whiteSet.size() > 0) {
            Vertex<Integer> current = whiteSet.iterator().next();
            if (hasCycle(current, whiteSet, graySet, blackSet)) {
            	cycle.add(current);
            	Collections.reverse(cycle);
                return true;
            }
        }
        return false;
    }

    private boolean hasCycle(Vertex<Integer> current, Set<Vertex<Integer>> whiteSet, Set<Vertex<Integer>> graySet,
            Set<Vertex<Integer>> blackSet) {
        System.out.println(String.format("hasCycle ( %s ) \t%-20s \t%-20s\t%-20s", current, whiteSet, graySet, blackSet));
        // move current to gray set from white set and then explore it.
        moveVertex(current, whiteSet, graySet);
        for (Vertex<Integer> neighbor : current.getAdjacentVertexes()) {
            // if in black set means already explored so continue.
            if (blackSet.contains(neighbor)) {
                continue;
            }
            // if in gray set then cycle found.
            if (graySet.contains(neighbor)) {
            	cycle.add(neighbor);
            	return true;
            }
            if (hasCycle(neighbor, whiteSet, graySet, blackSet)) {
                System.out.println(String.format("hasCycle ( %s ) \t%-20s \t%-20s\t%-20s <-- T", current, whiteSet, graySet, blackSet));
            	cycle.add(neighbor);
                return true;
            }
            
        }
        // move vertex from gray set to black set when done exploring.
        moveVertex(current, graySet, blackSet);
        System.out.println(String.format("hasCycle ( %s ) \t%-20s \t%-20s\t%-20s <-- F", current, whiteSet, graySet, blackSet));
        return false;
    }

    private void moveVertex(Vertex<Integer> vertex, Set<Vertex<Integer>> sourceSet,
            Set<Vertex<Integer>> destinationSet) {
        sourceSet.remove(vertex);
        destinationSet.add(vertex);
//        System.out.println(String.format("moved\t ( %s ) from: %-20s to: %-20s", vertex, sourceSet, destinationSet));
    }

    public static void main(String args[]) {
        Graph<Integer> graph = new Graph<>(true);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
//        graph.addEdge(1, 5);
        graph.addEdge(2, 3);
//        graph.addEdge(3, 5);
        graph.addEdge(4, 1);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 4);
        CycleInDirectedGraph cdg = new CycleInDirectedGraph();
        System.out.println(cdg.hasCycle(graph));
        System.out.println(cdg.cycle);
    }
}
