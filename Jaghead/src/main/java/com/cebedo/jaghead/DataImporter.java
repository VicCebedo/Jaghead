/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead;

import java.util.Set;

/**
 *
 * @author Vic
 */
public interface DataImporter {

    Set<Vertex> getVertices();

    Set<Edge> getEdges();

}
