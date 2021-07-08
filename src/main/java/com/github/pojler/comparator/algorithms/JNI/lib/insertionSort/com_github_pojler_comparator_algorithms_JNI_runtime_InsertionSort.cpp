#include "com_github_pojler_comparator_algorithms_JNI_runtime_Fibonacci.hpp"
#include <iostream>
#include <math.h>
#include <array>

extern "C"
int * array sort(int* arr){
    int result[] = arr;
    for(int i = 1; i < N; ++i){
        int key = result[i];
        int j = i -1;


        while(j >= 0 && result[j] > key){
            result[j+1] = result[j];
            j = j-1;
        }
        result[j] = key;
    }
    return result;
}


JNIEXPORT jintArray JNICALL Java_com_github_pojler_comparator_algorithms_JNI_runtime_Eratostenes_insertionSort
(JNIEnv * env, jobject thisObject, jintArray data){
    jboolean j = JNI_FALSE;
    int* d = env -> GetIntArrayElements(data, &j);
    d = sort(data);
    jintArray jArray = env-> NewIntArray(sizeof(d)/sizeof(d[0]));
    env -> SetIntArrayRegion(javaArray, 0, sizeof(d)/sizeof(d[0]), d);
    return javaArray;
}

