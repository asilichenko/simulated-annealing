package ua.in.asilichenko.simannealing;

import com.google.common.base.Stopwatch;
import ua.in.asilichenko.simannealing.cost.ArrayCostFunction;
import ua.in.asilichenko.simannealing.domain.Result;
import ua.in.asilichenko.simannealing.sample.Sample;
import ua.in.asilichenko.simannealing.service.SimAnnealingByCostFunction;

import java.time.LocalTime;

import static ua.in.asilichenko.simannealing.sample.Sample.*;

/**
 * Copyright (C) 2022 Oleksii Sylichenko (a.silichenko@gmail.com)
 * <p>
 * License: LGPL-3.0-or-later
 *
 * @author Oleksii Sylichenko (a.silichenko@gmail.com)
 * Creation date: ${DATE}
 */
public class SimAnnealingMain {

    @SuppressWarnings("CommentedOutCode")
    public static void main(String[] args) {
        System.out.println(LocalTime.now() + " >>> Start");
        final Stopwatch stopwatch = Stopwatch.createStarted();

        sample5();
//        sample6();
//        sample17();
//        sample26();
//        sample42();

        System.out.println(LocalTime.now() + " >>> Finished. Elapsed: " + stopwatch.elapsed());
    }

    @SuppressWarnings("unused")
    private static void sample5() {
        search(SAMPLE_5, 100, 10);
    }

    @SuppressWarnings("unused")
    private static void sample6() {
        search(SAMPLE_6, 1e4, 72);
    }

    @SuppressWarnings("unused")
    private static void sample17() {
        search(SAMPLE_17, 1e4, (long) 4e5);
    }

    @SuppressWarnings("unused")
    private static void sample26() {
        search(SAMPLE_26, 1e3, (long) 1e6);
    }

    @SuppressWarnings("unused")
    private static void sample42() {
        search(SAMPLE_42, 1e3, (long) 8e7);
    }

    private static void search(Sample sample, double startTemp, long steps) {
        final long[][] costMtx = sample.costMtx();

        final SimAnnealingByCostFunction simAnnealing
                = new SimAnnealingByCostFunction(new ArrayCostFunction(costMtx), costMtx.length);

        final Result<Integer> result = simAnnealing.search(startTemp, steps);

        System.out.println();
        System.out.println(result);
        System.out.println("Expected: " + sample.expected());
        System.out.println();
    }
}