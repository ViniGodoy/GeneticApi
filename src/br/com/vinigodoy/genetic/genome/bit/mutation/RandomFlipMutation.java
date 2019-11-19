package br.com.vinigodoy.genetic.genome.bit.mutation;

import br.com.vinigodoy.genetic.genome.bit.BitGenome;

import java.util.Random;

public class RandomFlipMutation implements BitMutationMethod {
    private static final Random RANDOM = new Random();

    private int maxBitsToMutate;

    public RandomFlipMutation() {
        this(4);
    }

    public RandomFlipMutation(int maxBitsToMutate) {
        if (maxBitsToMutate < 1)
            maxBitsToMutate = 1;
        this.maxBitsToMutate = maxBitsToMutate;
    }

    @Override
    public void mutate(BitGenome genome) {
        for (var i = 0; i < RANDOM.nextInt(maxBitsToMutate) + 1; i++) {
            var bit = RANDOM.nextInt(genome.size());
            genome.set(bit, !genome.get(bit));
        }
    }
}
