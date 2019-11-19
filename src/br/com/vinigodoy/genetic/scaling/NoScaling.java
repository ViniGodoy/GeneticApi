package br.com.vinigodoy.genetic.scaling;

import br.com.vinigodoy.genetic.ScoredPopulation;
import br.com.vinigodoy.genetic.domain.Individual;

/**
 * Does not apply any scale to the result. 
 * This can cause <i>premature convergence</i> and <i>stagnation</i>, but it's 
 * very fast.
 * 
 * @author Vinicius
 * @param <T> Individual type.
 */
public class NoScaling<T extends Individual> implements ScalingFunction<T> {
    public ScoredPopulation<T> scale(ScoredPopulation<T> population) {
        return population;
    }
}
