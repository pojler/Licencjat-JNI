#include "com_github_pojler_comparator_algorithms_JNI_runtime_Fibonacci.hpp"
#include <iostream>
#include <math.h>
#include <array>

extern "C"
void merge(int arr[], int begin, int middle, int end){

    int n1 = middle - begin + 1;
    int n2 = end - middle;

    int Set1[n1];
    int Set2[n2];

    for(int i = 0; i <n1; ++i){
        Set1[i] = arr[begin+1];
    }
    for (int j = 0; j < n2; ++j){
        Set2[j] = arr[middle + 1 + j];
    }

    int i = 0;
    int j = 0;
    int k = begin;
    while(i < n1 && j < n2){
        if(Set1[i] < Set2[j]){
            arr[k] = Set1[i];
            i++;
        }
        else{
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

JNIEXPORT jintArray JNICALL Java_com_github_pojler_comparator_algorithms_JNI_runtime_MergeSort_mergeSort
(JNIEnv * env, jobject thisObject, jintArray data){
    jboolean j = JNI_FALSE;
    int* d = env -> GetIntArrayElements(data, &j);
    d = sort(data, 0, (sizeof(d)/sizeof(d[0]))-1);
    jintArray jArray = env-> NewIntArray(sizeof(d)/sizeof(d[0]));
    env -> SetIntArrayRegion(jArray, 0, sizeof(d)/sizeof(d[0]), d);
    return jArray;
}

