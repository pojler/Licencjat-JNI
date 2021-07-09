package com.github.pojler.comparator.algorithms.Java;

//0(log n)
public class FibonacciRecursive {

    static int fib(int n)
    {
        if (n <= 1)
            return n;
        return fib(n-1) + fib(n-2);
    }

    public static void main (String args[])
    {
        FibonacciRecursive fib = new FibonacciRecursive();
        long startTime = System.nanoTime();
//        long n = fib.fib(10);
//        long n = fib.fib(20);
//        long n = fib.fib(30);
//        long n = fib.fib(40);
        long n = fib.fib(50);
        long elapsed = System.nanoTime() -startTime;
        System.out.println(n);
        System.out.println("Elapsed: " +elapsed/1e6 +" ms");
    }

}
