/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.domain;

import com.cebedo.giraffe.algorithm.ITraversalAlgorithm;

/**
 *
 * @author Vic
 */
public class GraphServant {

    public boolean checkConnectivity(IGraph graph, ITraversalAlgorithm algo) {
        return algo.traverse(graph).equals(graph.getVertices());
    }

}
