package br.com.vinigodoy.genetic.genome.bit.crossover;

import java.util.HashSet;
import java.util.Set;

import br.com.vinigodoy.genetic.genome.bit.BitGenome;
import br.com.vinigodoy.genetic.util.Two;

/**
 * Implement a n-point cut crossover. 
 * The genome 0000000 combined with 1111111 with cuts in 2 and 5 will generate two offspring:
 * <p>
 * parent1 - 00|000|00 <br>
 * parent2 - 11|111|11 <br>
 * <p>
 * offspring1 - 0011100
 * offspring2 - 1100011
 */
public class PointCrossover implements BitCrossoverMethod {
    private Set<Integer> cutSet;

    /**
     * Create a new a n-point cut crossover. 
     * The genome 0000000 combined with 1111111 with cuts in 2 and 5 will 
     * generate two offspring:
     * <p>
     * parent1 - 00|000|00 <br>
     * parent2 - 11|111|11 <br>
     * <p>
     * offspring1 - 0011100
     * offspring2 - 1100011
     * 
     * @param cuts Point of the cuts. Duplicate cuts will be discarded, as well as
     * indexes outside the genome boundaries.
     */
    public PointCrossover(int... cuts) {
        if (cuts.length == 0) {
            throw new IllegalArgumentException("Provide at least one cut!");
        }

        cutSet = new HashSet<Integer>(cuts.length);
        for (var cut : cuts) {
            cutSet.add(cut);
        }
    }

    public Two<BitGenome> crossover(BitGenome genome1, BitGenome genome2) {
       var mixes = new Two<BitGenome>(new BitGenome(genome1.size()),
                new BitGenome(genome2.size()));

        var flip = false;

        for (int i = 0; i < genome1.size(); i++) {
            if (cutSet.contains(i)) {
                flip = !flip;
            }

            mixes.getOne().set(i, flip ? genome1.get(i) : genome2.get(i));
            mixes.getTwo().set(i, flip ? genome2.get(i) : genome1.get(i));
        }

        return mixes;
    }
}
