package com.mycompany.statdata;

import static java.lang.Math.sqrt;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.StatUtils;
//import org.apache.commons.math3.stat.correlation.Covariance;
import java.util.HashMap;
import java.util.Map;

// 5. Рассчитать коэффициенты ковариации для всех пар случайных чисел
public class StatIndicators {

    public static String[] nameStatIndicators() {
        String[] names = {"mean", "geometric mean", "min", "max", "N", "R", "variance",
            "standard deviation", "K variance", "Confidence interval"};
        return names;
    }

    public static Map<String, Object> calculateStatIndicators(double[] data) {
        String mean;
        String geomMean;
        String var;
        String sd;
        String k_var;
        
        try {
            mean = String.valueOf(StatUtils.mean(data));
        } catch (Exception e) {
            mean = "-";
        }

        try {
            geomMean = String.valueOf(StatUtils.geometricMean(data));
        } catch (Exception e) {
            geomMean = "-";
        }

        int N = data.length; // Длина массива, может быть полезна для других вычислений
        double R = StatUtils.max(data) - StatUtils.min(data);
        double min = StatUtils.min(data);
        double max = StatUtils.max(data);

        try {
            var = String.valueOf(StatUtils.variance(data));
        } catch (Exception e) {
            var = "-";
        }

        try {
            sd = String.valueOf(sqrt(StatUtils.variance(data)));
        } catch (Exception e) {
            sd = "-";
        }

        try {
            k_var = String.valueOf(StatUtils.variance(data) * 100 / StatUtils.mean(data))+"%";
        } catch (Exception e) {
            k_var = "-";
        }

        Map<String, Object> results = new HashMap<>();
        results.put("mean", mean);
        results.put("geometric mean", geomMean);
        results.put("min", min);
        results.put("max", max);
        results.put("N", N);
        results.put("R", R);
        results.put("variance", var);
        results.put("standard deviation", sd);
        results.put("K variance", k_var);
        results.put("Confidence interval", confInterval(data, StatUtils.mean(data), StatUtils.variance(data), N));

        return results;
    }

    private static String confInterval(double[] data, double mean, double var, int N) {
        double confidenceLevel = 0.95;
        // Т-критерий
        TDistribution tDist = new TDistribution(N - 1);
        double t = tDist.inverseCumulativeProbability((1 + confidenceLevel) / 2);

        double marginOfError = t * sqrt(var / N);
        double lowBorder = mean - marginOfError;
        double upperBorder = mean + marginOfError;

        return "[" + lowBorder + ", " + upperBorder + "]";
    }

}
