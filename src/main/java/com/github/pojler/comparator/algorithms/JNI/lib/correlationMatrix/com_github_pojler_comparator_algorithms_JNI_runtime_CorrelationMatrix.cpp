#include "com_github_pojler_comparator_algorithms_JNI_runtime_CorrelationMatrix.hpp"
#include <iostream>
#include <math.h>
#include <array>

extern "C"

int getIndex(int x, int y, int width){
    return x+ (y * width);
}

double* normalize(double* data, int width, int height){
    double * result = data;
    double sum = 0;
    double avg = 0;
    double len = 0;
    for(int y = 0; y < height; y++){
        sum = 0;
        len = 0;
        for(int x = 0; x < width; x++){
            sum += result[getIndex(x, y, width)];
        }
        avg = sum/(width * 1.0);
        for(int x = 0; x < width; x++){
            result[getIndex(x,y, width)] -= avg;
        }
        for(int x = 0; x < width; x++){
            len += result[getIndex(x,y, width)] * result[getIndex(x,y, width)];
        }
        len = sqrt(len);
        for (int x = 0; x < width; x++){
            result[getIndex(x, y, width)] = result[getIndex(x, y, width)] / len;
        }
    }
    return result;
}

double * correlationMatrix(double* data, int width, int height){
    double* result = new double(height*height) ;
    double* normalized = normalize(data, width, height);
    for(int y = 0; y<height; y++){
        for(int x = 0; x < height; x++){
            double res = 0;
            for(int i = 0; i < width; i++){
                res += normalized[getIndex(i,y,width)] * normalized[getIndex(i,x,width)];
            }
            result[getIndex(x,y,height)] = res;
        }
    }
    return result;
}

JNIEXPORT jdoubleArray JNICALL Java_com_github_pojler_comparator_algorithms_JNI_runtime_CorrelationMatrix_correlationMatrix
(JNIEnv * env, jobject thisObject, jdoubleArray data, jint width, jint height){
        jboolean j = JNI_FALSE;
        double* d = env -> GetDoubleArrayElements(data, &j);
        double* result = correlationMatrix(d, (int)width, (int)height);
        jdoubleArray jArray = env-> NewDoubleArray((int)width*(int)width);
        env -> SetDoubleArrayRegion(jArray, 0, (int)width*(int)width, result);
        return jArray;
}

