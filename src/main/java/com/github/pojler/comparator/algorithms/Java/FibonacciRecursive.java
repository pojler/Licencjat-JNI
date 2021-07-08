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

        System.out.println(fib(20));
    }

}
