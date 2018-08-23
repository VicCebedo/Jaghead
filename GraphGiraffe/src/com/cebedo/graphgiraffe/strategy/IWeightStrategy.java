/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.graphgiraffe.strategy;

import com.cebedo.graphgiraffe.domain.Weight;
import com.cebedo.graphgiraffe.domain.IWeight;

/**
 *
 * @author Vic
 */
public interface IWeightStrategy {

    <T extends IWeight> T compute(Weight w);

}
