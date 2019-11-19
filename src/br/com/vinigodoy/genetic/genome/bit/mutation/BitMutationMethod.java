package br.com.vinigodoy.genetic.genome.bit.mutation;

import br.com.vinigodoy.genetic.genome.bit.BitGenome;

/**
 * The mutation method describes how the genome should be mutated. 
 */
public interface BitMutationMethod {

    /**
     * Changes the genome according to this mutation method criteria.
     * @param genome The genome to mutate.
     */
    void mutate(BitGenome genome);
}
