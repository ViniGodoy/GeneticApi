/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vinigodoy.genetic.selection;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import br.com.vinigodoy.genetic.ScoredPopulation;
import br.com.vinigodoy.genetic.domain.Individual;


/**
 * Imagine a roulette wheel which every band is as large as the individual 
 * priority. Every time, you spin the wheel, so, better scored individuals will
 * have a greater chance to be selected, while less scored will have a smaller
 * chance. This algorithm simulates this wheel.
 * 
 * @param <T>
 */
public class RouletteWheelSelection<T extends Individual> extends AbstractWheelSelection<T> {
    private Random random = new Random();

    @Override
    protected List<Long> randomizeSums(int amount, ScoredPopulation<T> population) {
        var rouletteResults = new ArrayList<Long>(amount);
        while (rouletteResults.size() < amount) {
            rouletteResults.add(randomLong(population.getSum()));
        }
        Collections.sort(rouletteResults);

        return rouletteResults;
    }

    private long randomLong(long sum) {
        var roulette = Long.MAX_VALUE;
        while (roulette > sum) {
            roulette = random.nextLong();
        }
        return roulette;
    }
}
