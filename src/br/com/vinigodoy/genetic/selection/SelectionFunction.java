package br.com.vinigodoy.genetic.selection;

import br.com.vinigodoy.genetic.ScoredPopulation;
import br.com.vinigodoy.genetic.domain.Individual;

/**
 * Implements a selection method. There's no need to worry about elitism since
 * it's an external functionality. 
 * 
 * @param <T> The individual type.
 */
public interface SelectionFunction<T extends Individual> {

    /**
     * Selects amount individuals from the population.
     * 
     * @param population Population to select from.
     * @param amount Number of invidivuals to select
     * @return The selected individuals.
     */
    ScoredPopulation<T> select(ScoredPopulation<T> population, int amount);
}
