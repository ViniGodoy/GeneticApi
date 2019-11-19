package br.com.vinigodoy.genetic.domain;

/**
 * Use this interface to implement your domain specific fitness function. The 
 * function is only required to provide a positive score, given an individual. 
 * The bigger the score is, better the individual solves the problem. */
public interface FitnessFunction<T extends Individual> {
    
    /* Calculate the fitness value. This function is only required to provide a 
     positive score, given for the given individual. The bigger the score is, 
     the better the individual solves the problem.
     */
    int calculateFitness(T individual);
}
