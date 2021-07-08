package com.github.pojler.comparator.algorithms.Java;

import java.util.Random;

class InsertionSort {

  private void randomFill(int[] arr){
        Random r = new Random();
        for(int i = 0; i < arr.length; i++){
            arr[i] = r.nextInt(2000000);
        }
    }


    void sort(int arr[])
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;


            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }



    public static void main(String args[])
    {
        int arr[] = new int [50];
//        int arr[] = new int [500];
//        int arr[] = new int [5000];
//        int arr[] = new int [50000];
//        int arr[] = new int [500000];

        InsertionSort ob = new InsertionSort();
        ob.randomFill(arr);
        long startTime = System.nanoTime();
        ob.sort(arr);
        long elapsed = System.nanoTime() -startTime;
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println("Elapsed: " +elapsed/1e6 +" ms");
    }
}