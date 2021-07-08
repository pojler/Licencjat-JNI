package com.github.pojler.comparator.algorithms.JNI.runtime;

public class Fibonacci {

    static {
        System.loadLibrary("fibonacci");
    }

    native private static int fibonacciRecursive (int n);
    native private static int fibonacciIterative(int n);

    public static void main(String[] args) {

        long startTime = System.nanoTime();
        int fib1result = fibonacciIterative(50);
//        int fib1result = fibonacciIterative(500);
//        int fib1result = fibonacciIterative(5000);
//        int fib1result = fibonacciIterative(50000);
//        int fib1result = fibonacciIterative(500000);
        long elapsed = System.nanoTime() -startTime;
        long rec = System.nanoTime();
        int fib2result = fibonacciRecursive(10);
//        int fib2result = fibonacciRecursive(20);
//        int fib2result = fibonacciRecursive(30);
//        int fib2result = fibonacciRecursive(40);
//        int fib2result = fibonacciRecursive(50);
        long elapsedrec = System.nanoTime() -rec;
        System.out.println();
        System.out.println("Elapsed: " +elapsed/1e6 +" ms in iterative Fib");
        System.out.println();
        System.out.println("Elapsed: " +elapsedrec/1e6 +" ms in recursive fib");


    }

}
