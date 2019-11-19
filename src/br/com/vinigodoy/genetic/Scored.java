/*
 */
package br.com.vinigodoy.genetic;

import br.com.vinigodoy.genetic.domain.FitnessFunction;
import br.com.vinigodoy.genetic.domain.Individual;

/**
 * An individual already scored by a fitness function, or scaled by a scale function.
 */
public final class Scored<T extends Individual> implements Comparable<Scored<T>> {

    private T individual;
    private int score;

    public Scored(T individual, int score) {
        if (individual == null) {
            throw new IllegalArgumentException("Invalid individual!");
        }

        if (score < 0) {
            throw new IllegalArgumentException("Invalid score!");
        }

        this.individual = individual;
        this.score = score;
    }

    public Scored(T individual, FitnessFunction<T> function) {
        this(individual, function.calculateFitness(individual));
    }

    public int getScore() {
        return score;
    }

    public T getIndividual() {
        return individual;
    }

    public int compareTo(Scored<T> o) {
        return o.getScore() - getScore();
    }
}
