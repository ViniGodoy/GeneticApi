package br.com.vinigodoy.genetic.selection;

import java.util.ArrayList;
import java.util.List;

import br.com.vinigodoy.genetic.ScoredPopulation;
import br.com.vinigodoy.genetic.domain.Individual;


/**
 * Divides the universe in a wheel, which evey piece proportional to the 
 * individual score. Then, instead of rolling the wheel, just turn it in a
 * fixed degree and take whoever individual is selected. 
 * 
 * @param <T> The individual type.
 */
public class StochasticUniversalSelection<T extends Individual> 
        extends AbstractWheelSelection<T> {

    @Override
    protected List<Long> randomizeSums(int amount, ScoredPopulation<T> population) {
        var division = population.getSum() / (double)amount;
        var sums = new ArrayList<Long>();
        for (var i = 0; i < amount; i++) {
            sums.add((long)(i*division));
        }
        
        return sums;
    }
}
