package ua.in.asilichenko.simannealing.domain;

import java.util.Arrays;

/**
 * Copyright (C) 2022 Oleksii Sylichenko (a.silichenko@gmail.com)
 * <p>
 * License: LGPL-3.0-or-later
 *
 * @author Oleksii Sylichenko (a.silichenko@gmail.com)
 * Creation date: 16.12.2022
 */
public record Result<T>(T[] key, long cost) {

    @Override
    public String toString() {
        return "Result: " + cost + "\n" + Arrays.toString(key);
    }
}
