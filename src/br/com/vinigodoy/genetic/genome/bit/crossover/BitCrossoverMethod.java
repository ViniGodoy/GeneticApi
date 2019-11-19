package br.com.vinigodoy.genetic.genome.bit.crossover;

import br.com.vinigodoy.genetic.genome.bit.BitGenome;
import br.com.vinigodoy.genetic.util.Two;
/**
 * Indicate how two genomes can be combined.
 */
public interface BitCrossoverMethod {
    /** 
     * Combine two genomes, generating two children.
     * @param genome1 The first genome to be combined
     * @param genome2 The second genome to be combined.
     */
    Two<BitGenome> crossover(BitGenome genome1, BitGenome genome2);
}