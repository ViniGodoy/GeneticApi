/*
 */

package br.com.vinigodoy.genetic.scaling;

import br.com.vinigodoy.genetic.ScoredPopulation;
import br.com.vinigodoy.genetic.domain.Individual;

public interface ScalingFunction<T extends Individual> {
    ScoredPopulation<T> scale(ScoredPopulation<T> population);
}
