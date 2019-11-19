package br.com.vinigodoy.genetic.selection;

import java.util.Random;

import br.com.vinigodoy.genetic.ScoredPopulation;
import br.com.vinigodoy.genetic.domain.Individual;

public class AsymptoticSelection<T extends Individual> implements SelectionFunction<T> {
    private Random random = new Random();
    private double exponent;

    public AsymptoticSelection() {
        exponent = 1.1;
    }

    AsymptoticSelection(double exponent) {
        this.exponent = exponent;
    }

    @Override
    public ScoredPopulation<T> select(ScoredPopulation<T> population, int amount) {
        population.sort();

        var selected = new ScoredPopulation<T>();

        for (var i = 0; i < amount; i++) {
            // Generate a number from 0 to size()-1
            var index = (int) (population.size() * Math.pow(random.nextDouble(), exponent));
            selected.add(population.get(index));
        }
        return selected;
    }

}
