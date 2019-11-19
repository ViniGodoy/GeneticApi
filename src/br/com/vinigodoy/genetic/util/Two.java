package br.com.vinigodoy.genetic.util;

/**
 * Represents two objects.
 */
public class Two<T> {

    private T one;
    private T two;

    /**
     * Create a holder for two objects of the same type.
     */
    public Two(T one, T two) {
        if (one == null) {
            throw new IllegalArgumentException("You must provide an object!");
        }
        this.one = one;
        this.two = two;
    }

    /** @return the first object */
    public T getOne() {
        return one;
    }

    /** @return the second object */
    public T getTwo() {
        return two;
    }
}
