/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search.backtrack;

import com.cebedo.jaghead.util.GraphUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import com.cebedo.jaghead.Vertex;
import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.algorithm.search.PathFindingAlgorithm;

/**
 *
 * @author Vic
 * @param <T1>
 * @param <T2>
 * @param <T3>
 */
public final class BTPathFinder<T1 extends Vertex, T2 extends Edge<T1>, T3 extends Graph<T1, T2>>
        implements PathFindingAlgorithm<T3, T1> {

    private final List<List<T1>> paths;
    private final Set<T2> visited;
    private final Set<SourceToEdge> visitedPairSet;
    private final List<T1> path;

    private BTPathFinder() {
        this.paths = new ArrayList<>();
        this.visited = new HashSet<>();
        this.visitedPairSet = new HashSet<>();
        this.path = new LinkedList<>();
    }

    public static PathFindingAlgorithm newInstance() {
        return new BTPathFinder();
    }

    /**
     * Get path from source to target by backtracking.
     *
     * @param graph
     * @param srcId
     * @param tgtId
     * @return
     */
    @Override
    public List<List<T1>> findPath(T3 graph, String srcId, String tgtId) {
        if (!graph.isConnected()) {
            throw new IllegalArgumentException("Graph should be connected.");
        }
        T1 src = GraphUtils.getVertexById(graph.getVertices(), srcId);
        T1 tgt = GraphUtils.getVertexById(graph.getVertices(), tgtId);
        path.add(src);
        return backtrack(graph, src, tgt, null);
    }

    private boolean isVisited(SourceToEdge e) {
        return visitedPairSet.contains(e);
    }

    private List<List<T1>> backtrack(T3 graph, T1 parent, T1 destination, T1 ancestor) {
        // Explore all outgoing edge of current vertex.
        for (T2 edge : graph.getIncidentEdgesOutgoing(parent)) {

            // We are now visiting this edge.
            // Check if has already been visited so that we dont do cycle.
            SourceToEdge pair = SourceToEdge.newInstance(ancestor.getId(), edge.getId());
            if (this.isVisited(pair)) {
                continue;
            }

            // If not yet visited,
            // keep track.
            T1 currentVertx = edge.getTarget();
            visited.add(edge);
            visitedPairSet.add(pair);
            path.add(currentVertx);

            // If this is destination, OR deadend
            // then backtrack to parent of current.
            if (this.isDestination(currentVertx, destination)
                    || this.isDeadend(graph.getIncidentEdgesOutgoing(currentVertx))) {
                paths.add(new LinkedList<>(path));
                path.remove(currentVertx);
                return this.backtrack(graph, parent, destination, ancestor);
            }
            return this.backtrack(graph, currentVertx, destination, parent);
        }

        // If we have visited already all edges,
        // then end operation. Else, backtrack to parent.
        if (GraphUtils.equals(visited, graph.getEdges())) {
            return Collections.unmodifiableList(paths);
        }
        path.remove(parent);
        return this.backtrack(
                graph,
                graph.getPredecessors(parent).iterator().next(),
                destination,
                ancestor);
    }

    private boolean isDestination(T1 currentVertx, T1 destination) {
        return currentVertx.getId().equalsIgnoreCase(destination.getId());
    }

    private boolean isDeadend(Set<T2> incidentOutgoing) {
        return incidentOutgoing.isEmpty();
    }

    private static final class SourceToEdge {

        private final String id;

        private SourceToEdge(String srcId, String edgeId) {
            this.id = (srcId == null ? "NULL" : srcId) + "_" + edgeId;
        }

        public static SourceToEdge newInstance(String srcId, String edgeId) {
            return new SourceToEdge(srcId, edgeId);
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 97 * hash + Objects.hashCode(this.id);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final SourceToEdge other = (SourceToEdge) obj;
            if (!Objects.equals(this.id, other.id)) {
                return false;
            }
            return true;
        }
    }
}
