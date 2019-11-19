package br.com.vinigodoy.genetic.scaling;

import br.com.vinigodoy.genetic.ScoredPopulation;
import br.com.vinigodoy.genetic.domain.Individual;

/**
 * <i>Replaces the fitness score with the individual position in the 
 * sorted order of the fitness scores. The lowest score will become 1, the 
 * second lowest will become 2 and, the last score will become equal to the 
 * population size. This eliminates the chance of premature convergence, but the 
 * function may take much longer to converge.
 */
public class RankScaling<T extends Individual> implements ScalingFunction<T> {

    public ScoredPopulation<T> scale(ScoredPopulation<T> population) {
        var scaled = new ScoredPopulation<T>();
        
        var i = 1;
        for (var scored : population)
            scaled.add(scored, i++);
        return scaled;
    }
}
