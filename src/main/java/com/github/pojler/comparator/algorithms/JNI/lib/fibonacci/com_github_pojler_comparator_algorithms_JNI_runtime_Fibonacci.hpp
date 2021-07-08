#include "jni.h"

#ifndef _Included_com_github_pojler_comparator_algorithms_JNI_runtime_Fibonacci
#define _Included_com_github_pojler_comparator_algorithms_JNI_runtime_Fibonacci
#ifdef __cplusplus
extern "C" {
#endif
/*
* Class:     com_pojler_jni_Main
* Method:    add
* Signature: (II)I
*/
JNIEXPORT jint JNICALL Java_com_github_pojler_comparator_algorithms_JNI_runtime_Fibonacci_fibonacciIterative
(JNIEnv *, jobject, jint);
JNIEXPORT jint JNICALL Java_com_github_pojler_comparator_algorithms_JNI_runtime_Fibonacci_fibonacciRecursive
(JNIEnv *, jobject, jint);
#ifdef __cplusplus
}
#endif
#endif