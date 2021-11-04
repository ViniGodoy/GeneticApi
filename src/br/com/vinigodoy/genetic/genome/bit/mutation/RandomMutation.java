package br.com.vinigodoy.genetic.genome.bit.mutation;

import java.util.Random;

import br.com.vinigodoy.genetic.genome.bit.BitGenome;


public class RandomMutation implements BitMutationMethod {

    private double ratePerBit;

    public RandomMutation(double ratePerBit) {
        if (ratePerBit <= 0 || ratePerBit >= 1) {
            throw new IllegalArgumentException("The chance per bit must be greather than 0 and smaller than 1!");
        }

        this.ratePerBit = ratePerBit;
    }

    public void mutate(BitGenome genes) {
        Random random = new Random();
        for (var i = 0; i < genes.size(); i++) {
            if (random.nextDouble() <= ratePerBit) {
                genes.set(i, !genes.get(i));
            }
        }
    }
}
