package br.com.vinigodoy.genetic.genome.bit;

import br.com.vinigodoy.genetic.genome.bit.crossover.BitCrossoverMethod;
import br.com.vinigodoy.genetic.genome.bit.mutation.BitMutationMethod;
import br.com.vinigodoy.genetic.util.Two;

import java.util.BitSet;
import java.util.Random;

/**
 * Represents a genome, with genes encoded as binary data. Provide useful methods for setting and getting values into
 * the binary string. Also, provide a crossover and a mutation methods.
 */
public class BitGenome {

    private BitSet genes;
    private int bits;

    /**
     * Creates a new genome with N bit size.
     */
    public BitGenome(int bits) {
        this.bits = bits;
        genes = new BitSet(bits);
    }

    /**
     * Creates a genome with random content.
     */
    public static BitGenome createRandom(int bits) {
        var random = new Random();
        var genes = new BitGenome(bits);
        for (int i = 0; i < bits; i++) {
            genes.set(i, random.nextBoolean());
        }
        return genes;
    }

    /**
     * Sets the given number in the specified position using cont bits.
     * 
     * @param pos
     *            Position to set the number
     * @param number
     *            Number to set. The number range will vary from 0 to (2^bits)-1
     * @param bits
     *            Number of bits that will be used to store this number.
     */
    public void setUnsigned(int pos, long number, int bits) {
        var max = (2 << bits - 1) - 1;

        if (number > max) {
            throw new IllegalArgumentException("Invalid number " + number + "! Maximum value for " + bits + " bits is "
                    + max + "!");
        }
        if (number < 0) {
            throw new IllegalArgumentException("Unsigned numbers cannot be smaller than 0!");
        }
        for (var i = pos + bits - 1; i >= pos; i--) {
            genes.set(i, (number & 1) == 1);
            number = number >> 1;
        }
    }

    /**
     * Sets the given signed number in the specified position using count bits.
     * 
     * @param pos
     *            Position to set the number. The number range will vary from -2^(bits-1)-1 to +2^(bits-1)-1
     * @param number
     *            Number to set.
     * @param bits
     *            Number of bits to use for storing.
     */
    public void setSigned(int pos, long number, int bits) {
        if (number < 0) {
            setUnsigned(pos + 1, -number, bits - 1);
            genes.set(pos);
            return;
        }

        setUnsigned(pos + 1, number, bits - 1);
        genes.clear(pos);
    }

    /**
     * Retrives the group of bits as a number, starting at the given position.
     * 
     * @param pos
     *            Position to start retrieving bits
     * @param bits
     *            Number of bits to retrieve.
     * @return An unsigned number, composed by all bits.
     */
    public long getUnsigned(int pos, int bits) {
        long num = 0;

        for (var i = pos; i < pos + bits; i++) {
            num = (num << 1) + (genes.get(i) ? 1 : 0);
        }
        return num;
    }

    /**
     * Retrives the group of bits as a number, starting at the given position.
     * 
     * @param pos
     *            Position to start retrieving bits
     * @param bits
     *            Number of bits to retrieve.
     * @return An unsigned number, composed by all bits. The first bit will be used to define signal.
     */
    public long getSigned(int pos, int bits) {
        var num = getUnsigned(pos + 1, bits - 1);
        return genes.get(pos) ? -num : num;
    }

    /**
     * Sets the given bit to value.
     * 
     * @param bit
     *            Bit index to set.
     * @param value
     *            value to set.
     */
    public void set(int bit, boolean value) {
        genes.set(bit, value);
    }

    /**
     * Gets the given bit value.
     * 
     * @param bit
     *            Bit index to get.
     * @return The bit avlue.
     */
    public boolean get(int bit) {
        return genes.get(bit);
    }

    /**
     * Make a crossover of this genome with another one.
     * 
     * @param other
     *            The other genome to cross with.
     * @param method
     *            A {@link BitCrossoverMethod} to cross with.
     * @return Two genomes with the crossed genes.
     * @see BitCrossoverMethod
     */
    public Two<BitGenome> crossover(BitGenome other, BitCrossoverMethod method) {
        return method.crossover(this, other);
    }

    /**
     * Flip some bits, causing the genome to mutate.
     * 
     * @param mutationMethod
     *            Define the way bits will be mutated.
     */
    public void mutate(BitMutationMethod mutationMethod) {
        mutationMethod.mutate(this);
    }

    /** @return this genome size. */
    public int size() {
        return bits;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        BitGenome other = (BitGenome) obj;
        return bits == other.bits && genes.equals(other.genes);
    }

    @Override
    public int hashCode() {
        return genes.hashCode();
    }
}
