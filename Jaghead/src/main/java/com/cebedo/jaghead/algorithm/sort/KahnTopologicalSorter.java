/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.sort;

import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * TODO [Run in sample, test, then doc].
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 */
public final class KahnTopologicalSorter<T1 extends Vertex, T2 extends Graph<T1, ? extends Edge>>
        implements TopologicalSortingAlgorithm<T2, T1> {

    private KahnTopologicalSorter() {
    }

    public static TopologicalSortingAlgorithm newInstance() {
        return new KahnTopologicalSorter();
    }

    @Override
    public List<T1> sort(T2 graph) {

        // Initialize the count of visited nodes as 0.
        Map<T1, Integer> vertexDegreeMap = new HashMap<>();
        Queue<T1> toVisit = new LinkedList<>();
        List<T1> topologicalOrder = new LinkedList<>();
        int visitedCount = 0;

        graph.getVertices().forEach(vertx -> {
            // Get in-degree (number of incoming edges) for each of the vertex.
            // Pick all the vertices with in-degree as 0 and add them into a queue.
            int degrees = graph.getDegreesIncoming(vertx);
            if (degrees == 0) {
                toVisit.add(vertx);
            }
            vertexDegreeMap.put(vertx, degrees);
        });

        while (!toVisit.isEmpty()) {
            // Remove a vertex from the queue.
            // Increment count of visited nodes by 1.
            T1 next = toVisit.poll();
            topologicalOrder.add(next);
            visitedCount++;

            // Decrease in-degree by 1 for all its neighbors.
            graph.getAdjacentVertices(next).forEach(neighbor -> {
                int updatedDegrees = vertexDegreeMap.get(neighbor) - 1;
                vertexDegreeMap.put(neighbor, updatedDegrees);

                // If in-degree of a neighbor is reduced to zero,
                // then add it to the queue.
                if (updatedDegrees == 0) {
                    toVisit.add(neighbor);
                }
            });
        }

        // If count of visited nodes is NOT equal to the number of nodes in the graph
        // then the topological sort is NOT possible for the given graph.
        if (visitedCount != graph.getVertices().size()) {
            throw new IllegalArgumentException("Graph is not topologically sortable.");
        }
        return topologicalOrder;
    }
}