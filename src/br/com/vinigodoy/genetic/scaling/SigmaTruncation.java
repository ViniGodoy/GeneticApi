package br.com.vinigodoy.genetic.scaling;

import br.com.vinigodoy.genetic.Scored;
import br.com.vinigodoy.genetic.ScoredPopulation;
import br.com.vinigodoy.genetic.domain.Individual;

/**
 * <i>"The scaled fitness value (F') is equated F' = F - (F^ - c* sigma). F is
 * the average fitness, sigma is the population standard deviation, and c is 
 * a reasonable multiplier (usually between 1 and 3). Negative results are set 
 * to 0. Scaling everyone's fitness using the standard deviation of the entire
 * group, which means that there is more scaling when the group is more wildly
 * different (hence, the beginning of the simulation) and will gradually take 
 * less effect as convergence gets under way and fitness scores start to become
 * similar." (Schwab, Brian)
 * <p>
 * This function uses a non compensated sum, which may generate some imprecision 
 * for large or homogeneous distributions. 
 * 
 * @param <T> The individual 
 */
public class SigmaTruncation<T extends Individual> implements ScalingFunction<T> {
    private double c;

    /**
     * Create a new Sigma Truncation {@link ScalingFunction}.
     * @param c A constant that is usually between 1 and 3.
     */
    public SigmaTruncation(double c) {
        this.c = c;
    }

    public ScoredPopulation<T> scale(ScoredPopulation<T> population) {
        //Then, calculate the standard deviation (sigma)
        var sum2 = 0.0;
        var avg = population.getAverage();
        for (Scored<T> scored : population) {
            var dif = scored.getScore() - avg;
            sum2 += dif * dif;
        }
        var sigma = Math.sqrt(sum2 / (population.size() - 1));

        //Finally, calculate the fitness
        var scoredPopulation = new ScoredPopulation<T>();
        for (var scored : population) {
            var fitness = scored.getScore() - (avg - c * sigma);
            scoredPopulation.add(scored, fitness < 0 ?  0 : (int)fitness);
        }
        return scoredPopulation;
    }
}
