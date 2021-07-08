#include "jni.h"

#ifndef _Included_com_github_pojler_comparator_algorithms_JNI_runtime_MergeSort
#define _Included_com_github_pojler_comparator_algorithms_JNI_runtime_MergeSort
#ifdef __cplusplus
extern "C" {
#endif
/*
* Class:     com_pojler_jni_Main
* Method:    add
* Signature: (II)I
*/
JNIEXPORT jintArray JNICALL Java_com_github_pojler_comparator_algorithms_JNI_runtime_MergeSort_mergeSort
(JNIEnv *, jobject, jintArray);

#ifdef __cplusplus
}
#endif
#endif