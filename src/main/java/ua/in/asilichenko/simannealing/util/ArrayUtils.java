package ua.in.asilichenko.simannealing.util;

/**
 * Copyright (C) 2022 Oleksii Sylichenko (a.silichenko@gmail.com)
 * <p>
 * License: LGPL-3.0-or-later
 *
 * @author Oleksii Sylichenko (a.silichenko@gmail.com)
 * Creation date: 14.12.2022
 */
public class ArrayUtils {

    public static void swap(Object[] a, int i, int j) {
        final Object t = a[j];
        a[j] = a[i];
        a[i] = t;
    }
}
