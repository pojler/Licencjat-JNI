#include "com_github_pojler_comparator_algorithms_JNI_runtime_InsertionSort.hpp"
#include <iostream>
#include <math.h>
#include <array>

extern "C"
long * sort(long* arr){
    long * result = arr;
    for(int i = 1; i < (sizeof(result)/sizeof(result[0])); ++i){
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


JNIEXPORT jintArray JNICALL Java_com_github_pojler_comparator_algorithms_JNI_runtime_InsertionSort_insertionSort
(JNIEnv * env, jobject thisObject, jintArray data){
    jboolean j = JNI_FALSE;
    jint* d = (env -> GetIntArrayElements(data, 0));
    d = sort(d);
    jintArray jArray = env-> NewIntArray(sizeof(d)/sizeof(d[0]));
    env -> SetIntArrayRegion(jArray, 0, sizeof(d)/sizeof(d[0]), d);
    return jArray;
}

