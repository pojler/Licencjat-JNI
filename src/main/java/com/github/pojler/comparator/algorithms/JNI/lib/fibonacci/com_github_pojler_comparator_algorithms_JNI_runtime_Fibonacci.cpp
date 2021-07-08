#include "com_github_pojler_comparator_algorithms_JNI_runtime_Fibonacci.hpp"
#include <iostream>
#include <math.h>
#include <array>

extern "C"

int fib(int n){
    if(n <= 1){
        return n;
    }
    return fib(n-1)+fib(n-2);
}

long int fibit(int n){

    int tab[n];
    tab[0] = 0;
    tab[1] = 1;

    for (int i = 2; i < n; i++){
        tab[i] = tab[i-1] + tab[i-2];
    }
    return tab[n-1];
}

JNIEXPORT jint JNICALL Java_com_github_pojler_comparator_algorithms_JNI_runtime_Fibonacci_fibonacciIterative
(JNIEnv * env, jobject thisObject, jint n){
    int pos = fibit((int)n);
    return (jint)fibit(n);
}

JNIEXPORT jint JNICALL Java_com_github_pojler_comparator_algorithms_JNI_runtime_Fibonacci_fibonacciRecursive
(JNIEnv * env, jobject thisObject, jint n){
    int pos = fib((int)n);
    return (jint)fib(n);

}