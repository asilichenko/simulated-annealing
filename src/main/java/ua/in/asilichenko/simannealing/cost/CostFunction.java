package ua.in.asilichenko.simannealing.cost;

/**
 * Copyright (C) 2022 Oleksii Sylichenko (a.silichenko@gmail.com)
 * <p>
 * License: LGPL-3.0-or-later
 *
 * @author Oleksii Sylichenko (a.silichenko@gmail.com)
 * Creation date: 16.12.2022
 */
public abstract class CostFunction {

    public abstract long cost(Integer[] key);
}
