package com.mycompany.statdata;


import static java.lang.Math.sqrt;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.*;




//5. Рассчитать коэффициенты ковариации для всех пар случайных чисел



public class StatIndicators {
    
    public static double meanGeom(double[] data) {
         return StatUtils.geometricMean(data);
    }
    
    
    public static double mean(double[] data) {
         return StatUtils.mean(data);
    }
    
    public static int N (double[] data){
        return data.length;
    }
    
    public static double R(double[] data) {
        return StatUtils.max(data) - StatUtils.min(data);
    }
    
    public static double min(double[] data) {
        return StatUtils.min(data);
    }
    
    public static double max(double[] data) {
        return StatUtils.max(data);
    }
    
    public static double sd(double[] data){
        return sqrt(StatUtils.variance(data));
    }
    
    public static double k_var(double[] data) {
        return var(data)/mean(data);
    }
    
    public static double var(double[] data) {
        return StatUtils.variance(data);
    }
    
    public static String confInterval(double[] data){
        double confidenceLevel = 0.95;
        // Т-критерий
        TDistribution tDist = new TDistribution(N(data) - 1);
        double t = tDist.inverseCumulativeProbability((1 + confidenceLevel) / 2);
        
        double marginOfError = t * sqrt(var(data)/N(data));
        
        double lowBorder = mean(data) -marginOfError;
        double upperBorder = mean(data) + marginOfError;
        
        return ("Доверительный интервал: [" + lowBorder + "," + upperBorder + "]");  
    }
}
