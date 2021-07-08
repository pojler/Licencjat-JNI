package com.github.pojler.comparator.algorithms.JNI.runtime;

import java.util.Random;

public class CorrelationMatrix {
    static {
        System.loadLibrary("correlation");
    }
    private static void randomFill(double[] arr){
        Random r = new Random();
        for(int i = 0; i < arr.length; i++){
            arr[i] = r.nextInt(2000000);
        }
    }


    native private static double[] correlationMatrix (double[] data, int width, int height);

    public static void main(String[] args) {
        double[] data = new double[49];
//        double[] data = new double[484];
//        double[] data = new double[4900];
//        double[] data = new double[49729];
//        double[] data = new double[499849];
        randomFill(data);
        int width = (int)Math.sqrt(data.length);
        int height = (int)Math.sqrt(data.length);
        long startTime = System.nanoTime();
        double result[] = correlationMatrix(data, width, height);
        long elapsed = System.nanoTime() -startTime;
        System.out.println();
        System.out.println("Elapsed: " +elapsed/1e6 +" ms");

    }

}
