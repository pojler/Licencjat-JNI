#include "jni.h"

#ifndef _Included_com_github_pojler_comparator_algorithms_JNI_runtime_correlationMatrix
#define _Included_com_github_pojler_comparator_algorithms_JNI_runtime_correlationMatrix
#ifdef __cplusplus
extern "C" {
#endif
/*
* Class:     com_pojler_jni_Main
* Method:    add
* Signature: (II)I
*/
JNIEXPORT jdoubleArray JNICALL Java_com_github_pojler_comparator_algorithms_JNI_runtime_CorrelationMatrix_correlationMatrix
(JNIEnv *, jobject, jdoubleArray, jint, jint);
#ifdef __cplusplus
}
#endif
#endif