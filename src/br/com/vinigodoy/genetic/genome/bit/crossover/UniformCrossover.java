package br.com.vinigodoy.genetic.genome.bit.crossover;

import java.util.Random;

import br.com.vinigodoy.genetic.genome.bit.BitGenome;
import br.com.vinigodoy.genetic.util.Two;


/**
 * Randomly cross the bit from the parent with the children.
 * If the chancePerBit is hit, the bit of the parent and mother will be 
 * switched.
 * A too small or a too big chance will result in children identical to it's 
 * parent.
 */
public class UniformCrossover implements BitCrossoverMethod {

    private double chancePerBit;

    /**
     * Randomly cross the bit from the parent with the children.
     * If the chancePerBit is hit, the bit of the parent and mother will be 
     * switched. A too small or a too big chance will result in children 
     * identical to it's parent.
     * 
     * @param chancePerBit Chance to cross one single bit. This chance will be 
     * applyed to every bit.
     */
    public UniformCrossover(double chancePerBit) {
        if (chancePerBit < 0 || chancePerBit > 1) {
            throw new IllegalArgumentException("The chance per bit must be between 0 and 1!");
        }
        this.chancePerBit = chancePerBit;
    }

    public Two<BitGenome> crossover(BitGenome genome1, BitGenome genome2) {
        var random = new Random();
        var children = new Two<BitGenome>(new BitGenome(genome1.size()),
                new BitGenome(genome2.size()));

        for (int i = 0; i < genome1.size(); i++) {
            if (random.nextDouble() <= chancePerBit) {
                children.getOne().set(i, genome2.get(i));
                children.getTwo().set(i, genome1.get(i));
            } else {
                children.getOne().set(i, genome1.get(i));
                children.getTwo().set(i, genome2.get(i));
            }
        }
        return children;
    }
}
