package br.com.vinigodoy.genetic.domain;

import java.util.List;

/**
 * An individual is a domain specific class that encapsulates the logic of 
 * dealing with the genome structure.
 */
public interface Individual {

    /**
     * Crosses this individual with the other one, generating one or two 
     * offspring. Both the other individual and the return must be of the same 
     * class.
     * @param other The other individual to cross with.
     * @return The offspring. 
     */
    List<Individual> crossover(Individual other);

    /**
     * Mutate the individual.
     */
    void mutate();
}
