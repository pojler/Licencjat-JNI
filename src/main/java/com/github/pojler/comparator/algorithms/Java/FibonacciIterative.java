package com.github.pojler.comparator.algorithms.Java;

public class FibonacciIterative {
    long fib(int n){
        long[] tab = new long[n];
        tab[0] = 0;
        tab[1] = 1;
        for(int i = 2; i < n; i++){
            tab[i] = tab[i-1] + tab[i-2];
        }
        return tab[n-1];
    }

    public static void main(String[] args) {
        FibonacciIterative fib = new FibonacciIterative();
        long startTime = System.nanoTime();
//        long n = fib.fib(50);
//        long n = fib.fib(500);
//        long n = fib.fib(5000);
//        long n = fib.fib(50000);
        long n = fib.fib(500000);
        long elapsed = System.nanoTime() -startTime;
        System.out.println(n);
        System.out.println("Elapsed: " +elapsed/1e6 +" ms");

    }

}
