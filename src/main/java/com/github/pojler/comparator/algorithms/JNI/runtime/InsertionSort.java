package com.github.pojler.comparator.algorithms.JNI.runtime;

import java.util.Random;

public class InsertionSort {

    static {
        System.loadLibrary("insertion");
    }
    private static void randomFill(int[] arr){
        Random r = new Random();
        for(int i = 0; i < arr.length; i++){
            arr[i] = r.nextInt(2000000);
        }
    }

    native private static int[] insertionSort(int[] data);

    public static void main(String[] args) {
        // n = 1000
//        int arr[] = new int [50];
//        int arr[] = new int [500];
//        int arr[] = new int [5000];
//        int arr[] = new int [50000];
        int arr[] = new int [500000];
        randomFill(arr);
       long startTime = System.nanoTime();
        int sorted[] = insertionSort(arr);
        long elapsed = System.nanoTime() -startTime;
        System.out.println();
        System.out.println("Elapsed: " +elapsed/1e6 +" ms");
    }

}
