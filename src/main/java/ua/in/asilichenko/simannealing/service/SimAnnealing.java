package ua.in.asilichenko.simannealing.service;

import ua.in.asilichenko.simannealing.domain.Result;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Simulated Annealing base class.
 * <p>
 * Copyright (C) 2022 Oleksii Sylichenko (a.silichenko@gmail.com)
 * <p>
 * License: LGPL-3.0-or-later
 *
 * @author Oleksii Sylichenko (a.silichenko@gmail.com)
 * Creation date: 14.12.2022
 */
public abstract class SimAnnealing<T> {

    private static final double EPSILON = 0.0085;

    protected Random random() {
        return ThreadLocalRandom.current();
    }

    /**
     * @return initial random key
     */
    protected abstract T[] randomKey();

    /**
     * Generate random key based on previous key.
     *
     * @param key base key
     * @return random key
     */
    protected abstract T[] randomKey(T[] key);

    /**
     * Evaluate cost of the key.
     *
     * @param key key
     * @return cost of the key
     */
    protected abstract long cost(T[] key);

    /**
     * Difference between new cost and previous.
     * Is used to determine which cost is better.
     *
     * @param newCost  cost of new key
     * @param currCost cost of current key
     * @return (new - current) if search for lowest and (current - new) otherwise
     */
    protected long delta(long newCost, long currCost) {
        return newCost - currCost;
    }

    /**
     * Decision method if new key must be accepted, sometimes event if it's worse.
     *
     * @param newCost  cost of new key
     * @param currCost cost of current key
     * @param t        current temperature
     * @return if new key must be accepted
     */
    private boolean accept(long newCost, long currCost, double t) {
        final double delta = delta(newCost, currCost);

        if (delta < 0) return true;
        if (0 == delta) return random().nextBoolean();

        final double acceptanceProbability = Math.exp(-delta / t);
        return EPSILON < acceptanceProbability && acceptanceProbability < random().nextDouble();
    }

    /**
     * Calculate temperature decreasing rate according to expected number of steps.
     *
     * @param startTemp start temperature
     * @param steps     number of steps
     * @return temperature decreasing rate
     */
    protected double calcTempRate(double startTemp, long steps) {
        return Math.pow(1d / startTemp, 1d / steps);
    }

    /**
     * Main search method.
     *
     * @param startTemp start temperature
     * @param steps     number of steps
     * @return the best found result
     */
    public Result<T> search(double startTemp, long steps) {
        T[] key = randomKey();
        long cost = cost(key);
        Result<T> retval = new Result<>(key, cost);

        final double tempRate = calcTempRate(startTemp, steps);
        for (double t = startTemp; t > 1; t *= tempRate) {
            final T[] nextKey = randomKey(key);
            final long nextCost = cost(nextKey);
            if (accept(nextCost, cost, t)) {
                key = nextKey;
                cost = nextCost;
                if (delta(cost, retval.cost()) < 0) retval = new Result<>(key, cost);
            }
        }
        return retval;
    }
}
