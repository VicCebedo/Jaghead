/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search;

import com.cebedo.jaghead.GenericEdge;
import com.cebedo.jaghead.GenericVertex;
import com.cebedo.jaghead.Graph;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 */
public class EdgeBFSearch<T1 extends GenericVertex, T2 extends GenericEdge<T1, T1>>
        implements SearchAlgorithm<Graph<T1, T2>, T2, EdgeCondition<T2>> {

    @Override
    public Set search(Graph<T1, T2> graph, EdgeCondition<T2> condition) {

        // The queue of the search.
        Queue<T1> toVisit = new LinkedList<>();
        toVisit.add(graph.getVertices().iterator().next());

        // List of visited vertices.
        Set<T1> done = new HashSet<>();
        Set<T2> returnSet = new HashSet<>();

        // Loop through all vertices.
        while (!toVisit.isEmpty()) {
            T1 next = toVisit.poll();
            done.add(next);

            // Check conditions for this node.
            graph.getEdges(next).forEach(edge -> {
                if (condition.check(edge)) {
                    returnSet.add(edge);
                }
            });

            // Add the neighbors to visit.
            graph.getAdjacentVerticesAll(next).forEach(neighbor -> {
                if (!done.contains(neighbor)) {
                    toVisit.add(neighbor);
                }
            });
        }
        return returnSet;
    }
}