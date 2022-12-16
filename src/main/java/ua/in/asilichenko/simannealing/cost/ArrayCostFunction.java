package ua.in.asilichenko.simannealing.cost;

/**
 * Copyright (C) 2022 Oleksii Sylichenko (a.silichenko@gmail.com)
 * <p>
 * License: LGPL-3.0-or-later
 *
 * @author Oleksii Sylichenko (a.silichenko@gmail.com)
 * Creation date: 16.12.2022
 */
public class ArrayCostFunction extends CostFunction {

    private final long[][] matrix;

    /**
     * @param matrix cost matrix
     */
    public ArrayCostFunction(long[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public long cost(Integer[] key) {
        long retval = 0;

        for (int i = 0, j = 1; j < key.length; i = j++) retval += matrix[i][j];
        retval += matrix[key[key.length - 1]][key[0]];

        return retval;
    }
}
