/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.vinigodoy.genetic.selection;

import java.util.Random;

import br.com.vinigodoy.genetic.ScoredPopulation;
import br.com.vinigodoy.genetic.domain.Individual;


/**
 * Selects two random individuals. Choose the better scored one. 
 * Repeat this process until all requested individuals are chosen.
 */
public class TournamentSelection<T extends Individual> implements SelectionFunction<T> {
    private Random random = new Random();

    public ScoredPopulation<T> select(ScoredPopulation<T> population, int amount) {

        var selected = new ScoredPopulation<T>();
        
        for (int i = 0; i < amount; i++)
        {
            var one = population.get(random.nextInt(population.size()));
            var two = population.get(random.nextInt(population.size()));
            population.add(one.compareTo(two) > 0 ? one : two);
        }
        return selected;
    }

}
