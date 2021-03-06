/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search.bfsdfs;

import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 */
public final class JagheadSearch {

    public static enum BreadthFirst {
        EDGE {
            @Override
            Set<Vertex> run(Graph graph, String sourceId, Checker checker) {
                return BfsEdge.newInstance().search(graph, sourceId, checker);
            }
        },
        VERTEX {
            @Override
            Set<Vertex> run(Graph graph, String sourceId, Checker checker) {
                return BfsVertex.newInstance().search(graph, sourceId, checker);
            }
        };

        abstract Set<Vertex> run(Graph graph, String sourceId, Checker checker);

        public <T extends Checker> Set<Vertex> search(Graph graph, String sourceId, T checker) {
            Objects.requireNonNull(graph);
            Objects.requireNonNull(sourceId);
            Objects.requireNonNull(checker);
            return this.run(graph, sourceId, checker);
        }
    }

    public static enum DepthFirst {
        EDGE {
            @Override
            Set<Vertex> run(Graph graph, String sourceId, Checker checker) {
                return DfsEdge.newInstance().search(graph, sourceId, checker);
            }
        },
        VERTEX {
            @Override
            Set<Vertex> run(Graph graph, String sourceId, Checker checker) {
                return DfsVertex.newInstance().search(graph, sourceId, checker);
            }
        };

        abstract Set<Vertex> run(Graph graph, String sourceId, Checker checker);

        public <T extends Checker> Set<Vertex> search(Graph graph, String sourceId, T checker) {
            Objects.requireNonNull(graph);
            Objects.requireNonNull(sourceId);
            Objects.requireNonNull(checker);
            return this.run(graph, sourceId, checker);
        }
    }
}
