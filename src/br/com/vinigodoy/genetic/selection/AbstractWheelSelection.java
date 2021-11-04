package br.com.vinigodoy.genetic.selection;

import java.util.List;

import br.com.vinigodoy.genetic.ScoredPopulation;
import br.com.vinigodoy.genetic.domain.Individual;


public abstract class AbstractWheelSelection<T extends Individual> implements SelectionFunction<T> {

    public ScoredPopulation<T> select(ScoredPopulation<T> population, int amount) {
        if (population == null) {
            throw new IllegalArgumentException("Population cannot be null!");
        }
        if (population.size() == 0) {
            throw new IllegalArgumentException("Population cannot be empty!");
        }
        if (amount == 0) {
            throw new IllegalArgumentException("Invalid amount!");
        }
        
        population.sort();
        var rouletteResults = randomizeSums(amount, population);

        var selected = new ScoredPopulation<T>();
        var sum = 0L;
        for (var individual : population) {
            sum += individual.getScore();
            while (rouletteResults.size() > 0 && rouletteResults.get(0) < sum) {
                selected.add(individual);
                rouletteResults.remove(0);
            }
        }

        return selected;
    }
    
    protected abstract List<Long> randomizeSums(int amount, ScoredPopulation<T> population);
}
