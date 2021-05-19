#include "com_github_pojler_comparator_algorithms_JNI_runtime_Eratostenes.hpp"
#include <iostream>
#include <math.h>

extern "C"

bool* generateTable (int n){
    bool* table = new bool[n+1];
    table[0] = false;
    table[1] = false;
    for(int i = 2; i <n+1; i++){
        table[i] = true;
    }
    return table;
}

bool* eratostenes (int n){
    bool* table = generateTable(n);
    int bound = sqrt(n);
    for (int i = 0; i <bound; i++){
        if(table[i] == true){
            for(int j = i*2; j<n+1; j+=i){
                table[j] = false;
            }
        }

    }
    return table;
}


JNIEXPORT jbooleanArray JNICALL Java_com_github_pojler_comparator_algorithms_JNI_runtime_Eratostenes_eratostenes
(JNIEnv * env, jobject thisObject, jint n){
    jboolean *data  = (jboolean*) eratostenes(n);
    jbooleanArray result = env->NewBooleanArray(n);
    env->SetBooleanArrayRegion(result, 0, n, data);
    return result;
}