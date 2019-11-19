package br.com.vinigodoy.genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import br.com.vinigodoy.genetic.domain.Individual;

public class ScoredPopulation<T extends Individual> implements Iterable<Scored<T>> {

    private List<Scored<T>> population = new ArrayList<Scored<T>>();
    private Scored<T> best = null;
    private long sum = 0;

    public void add(Scored<T> individual) {
        population.add(individual);
        sum += individual.getScore();
        if (best == null || individual.getScore() > best.getScore()) {
            best = individual;
        }
    }

    public void add(Scored<T> individual, int newScore) {
        add(new Scored<T>(individual.getIndividual(), newScore));
    }

    public ListIterator<Scored<T>> iterator() {
        return getPopulation().listIterator();
    }

    public ListIterator<Scored<T>> iterator(int index) {
        return getPopulation().listIterator(index);
    }

    public List<Scored<T>> getPopulation() {
        return Collections.unmodifiableList(population);
    }

    public Scored<T> getBest() {
        return best;
    }

    public long getSum() {
        return sum;
    }

    public double getAverage() {
        return sum / (double) population.size();
    }

    public void sort() {
        Collections.sort(population);
    }

    public int size() {
        return population.size();
    }

    public Scored<T> get(int index) {
        return population.get(index);
    }
}
