/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.giraffe.algorithm;

import com.cebedo.giraffe.domain.IGraph;
import com.cebedo.giraffe.domain.IVertex;
import java.util.Set;

/**
 *
 * @author Vic
 */
public interface ITraversalAlgorithm {

    void traverse(IGraph graph);

    Set<IVertex> getTraversed();

}
