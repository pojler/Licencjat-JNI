package com.github.pojler.comparator.algorithms.JNI.runtime;

public class CorrelationMatrix {
    static {
        System.loadLibrary("correlation");
    }

    native private static double[] correlationMatrix (double[] data, int width, int height);

    public static void main(String[] args) {
        double[] data = {2,1,3,7,1,9,9,8,80,50,70,20};
        int width = 4;
        int height =3;
        long startTime = System.nanoTime();
        double result[] = correlationMatrix(data, width, height);
        long elapsed = System.nanoTime() -startTime;
        System.out.println();
        System.out.println("Elapsed: " +elapsed/1e6 +" ms in iterative Fib");

    }

}
