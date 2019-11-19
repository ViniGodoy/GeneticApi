package br.com.vinigodoy.genetic;

import br.com.vinigodoy.genetic.domain.FitnessFunction;
import br.com.vinigodoy.genetic.domain.Individual;
import br.com.vinigodoy.genetic.scaling.NoScaling;
import br.com.vinigodoy.genetic.scaling.ScalingFunction;
import br.com.vinigodoy.genetic.selection.AsymptoticSelection;
import br.com.vinigodoy.genetic.selection.SelectionFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generation<T extends Individual> {
    private static Random random = new Random();

    private int number;
    private ScoredPopulation<T> scored = new ScoredPopulation<T>();
    private FitnessFunction<T> fitnessFunction;
    private ScalingFunction<T> scalingFunction;
    private SelectionFunction<T> selectionFunction;
    private double crossoverRate;
    private boolean ellitism;
    private double mutationRate;

    /**
     * Create a new generation using the above defaults:
     * <ul>
     * <li>No scaling;
     * <li>Asymptotic selection
     * <li>75% crossover rate;
     * <li>10% mutation rate (the mutation rate should be defined by the genome);
     * <li>do ellitism;
     * </ul>
     * 
     * @param population
     * @param fitnessFunction
     */
    public Generation(List<? extends T> population, FitnessFunction<T> fitnessFunction) {
        this(population, fitnessFunction, new NoScaling<T>(), new AsymptoticSelection<T>(), 0.75, 0.1, true);
    }

    public Generation(List<? extends T> population, FitnessFunction<T> fitnessFunction,
            ScalingFunction<T> scalingFunction, SelectionFunction<T> selectionFunction, double crossoverRate,
            double mutationRate, boolean ellitism) {

        if (population == null) {
            throw new IllegalArgumentException("Invalid initial population!");
        }
        if (fitnessFunction == null) {
            throw new IllegalArgumentException("Invalid fitness function!");
        }

        if (scalingFunction == null) {
            throw new IllegalArgumentException("Invalid scaling function!");
        }

        if (selectionFunction == null) {
            throw new IllegalArgumentException("Invalid selection function!");
        }

        if (crossoverRate < 0 || crossoverRate > 1) {
            throw new IllegalArgumentException("Crossover rate should be between 0 and 1!");
        }
        this.number = 0;
        this.fitnessFunction = fitnessFunction;
        this.scalingFunction = scalingFunction;
        this.selectionFunction = selectionFunction;
        this.crossoverRate = crossoverRate;
        this.ellitism = ellitism;
        this.mutationRate = mutationRate;

        doCalculations(population);
    }

    /**
     * Construtor for the next generation
     */
    private Generation(List<? extends T> population, Generation<T> other) {
        this(population, other.fitnessFunction, other.scalingFunction, other.selectionFunction, other.crossoverRate,
                other.mutationRate, other.ellitism);
        number = other.number + 1;
    }

    public T getBestIndividual() {
        return scored.getBest().getIndividual();
    }

    public int getBestIndividualScore() {
        return scored.getBest().getScore();
    }

    private void doCalculations(List<? extends T> population) {
        population.forEach(i -> scored.add(new Scored<T>(i, fitnessFunction)));
        scored.sort();
    }

    @SuppressWarnings("unchecked")
    public Generation<T> next() {
        var next = new ArrayList<T>();

        // Separate the elite, if any
        if (ellitism)
            next.add(getBestIndividual());

        // Scale the remaining population
        var scaled = scalingFunction.scale(scored);

        // Select candidates for crossover
        var selected = selectionFunction.select(scaled, scaled.size());

        // For each selected pair
        int i = 0;
        while (next.size() < size()) {
            var p1 = selected.get(i).getIndividual();
            var p2 = selected.get(i + 1).getIndividual();
            i = (i + 2) % (selected.size() - 1);

            var children = new ArrayList<Individual>();

            // Cross the selection or not, according to the crossover rate
            if (random.nextDouble() <= crossoverRate) {
                children.addAll(p1.crossover(p2));
            } else {
                children.add(p1);
                children.add(p2);
            }

            // Mutate the selection, if need.
            children.forEach(child -> {
                if (random.nextDouble() <= mutationRate) {
                    child.mutate();
                }
            });

            // Then, add the elements to the return list
            children.forEach(child -> next.add((T) child));
        }

        return new Generation<T>(next, this);
    }

    private int size() {
        return scored.size();
    }

    public Generation<T> next(int count) {
        var generation = this;
        for (var i = 0; i < count; i++) {
            generation = generation.next();
        }

        return generation;
    }
}
