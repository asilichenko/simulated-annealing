package ua.in.asilichenko.simannealing.service;

import ua.in.asilichenko.simannealing.cost.CostFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ua.in.asilichenko.simannealing.util.ArrayUtils.swap;

/**
 * Copyright (C) 2022 Oleksii Sylichenko (a.silichenko@gmail.com)
 * <p>
 * License: LGPL-3.0-or-later
 *
 * @author Oleksii Sylichenko (a.silichenko@gmail.com)
 * Creation date: 14.12.2022
 */
public class SimAnnealingByCostFunction extends SimAnnealing<Integer> {

    private final int keySize;
    private final CostFunction costFunction;

    public SimAnnealingByCostFunction(CostFunction costFunction, int keySize) {
        this.costFunction = costFunction;
        this.keySize = keySize;
    }

    @Override
    protected Integer[] randomKey() {
        final List<Integer> retval = new ArrayList<>();
        for (int i = 0; i < keySize; i++) retval.add(i);
        Collections.shuffle(retval);
        return retval.toArray(new Integer[0]);
    }

    @Override
    protected Integer[] randomKey(Integer[] key) {
        final Integer[] retval = Arrays.copyOf(key, key.length);

        final int i = random().nextInt(key.length);
        int j = i;
        while (j == i) j = random().nextInt(key.length);

        swap(retval, i, j);

        return retval;
    }

    @Override
    protected long cost(Integer[] key) {
        return costFunction.cost(key);
    }
}
