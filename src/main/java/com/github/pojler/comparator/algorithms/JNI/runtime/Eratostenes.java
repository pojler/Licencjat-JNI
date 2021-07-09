package com.github.pojler.comparator.algorithms.JNI.runtime;

public class Eratostenes {

    static {
        System.loadLibrary("eratostenes");
    }

    native private static boolean[] eratostenes (int n);

    public static void main(String[] args) {

        long startTime = System.nanoTime();
//        boolean[] table = eratostenes(50);
//        boolean[] table = eratostenes(500);
//        boolean[] table = eratostenes(5000);
//        boolean[] table = eratostenes(50000);
        boolean[] table = eratostenes(500000);
        long elapsed = System.nanoTime() -startTime;
        if(table.length < 10000) {
            for (int i = 0; i < table.length; i++) {
                System.out.print(table[i] ? i + " " : "");
            }
        }
        System.out.println();
        System.out.println("Elapsed: " +elapsed/1e6 +" ms");

    }

}


