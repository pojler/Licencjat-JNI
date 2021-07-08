package com.github.pojler.comparator.algorithms.Java;

import java.util.Random;

//O(n log n)
class MergeSort
{
  private void randomFill(int[] arr){
        Random r = new Random();
        for(int i = 0; i < arr.length; i++){
            arr[i] = r.nextInt(2000000);
        }
    }

    void merge(int arr[], int begin, int middle, int end)
    {

        int n1 = middle - begin + 1;
        int n2 = end - middle;

        int Set1[] = new int[n1];
        int Set2[] = new int[n2];

        for (int i = 0; i < n1; ++i)
            Set1[i] = arr[begin + i];
        for (int j = 0; j < n2; ++j)
            Set2[j] = arr[middle + 1 + j];

        int i = 0;
        int j = 0;

        int k = begin;
        while (i < n1 && j < n2) {
            if (Set1[i] <= Set2[j]) {
                arr[k] = Set1[i];
                i++;
            }
            else {
                arr[k] = Set2[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = Set1[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = Set2[j];
            j++;
            k++;
        }
    }


    void sort(int arr[], int begin, int end)
    {
        if (begin < end) {
            int m =begin+ (end-begin)/2;

            sort(arr, begin, m);
            sort(arr, m + 1, end);

            merge(arr, begin, m, end);
        }
    }



    public static void main(String args[])
    {
        int arr[] = new int[50];
//        int arr[] = new int[500];
//        int arr[] = new int[5000];
//        int arr[] = new int[50000];
//        int arr[] = new int[500000];

         MergeSort ob = new MergeSort();
         ob.randomFill(arr);
        long startTime = System.nanoTime();
        ob.sort(arr, 0, arr.length - 1);
        long elapsed = System.nanoTime() -startTime;
        for(int i = 0; i< arr.length; i++){
            System.out.print(arr[i]+ " ");
        }
        System.out.println("Elapsed: " +elapsed/1e6 +" ms");

    }
}