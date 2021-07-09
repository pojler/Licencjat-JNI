#include <iostream>
#include <chrono>
#include <array>
#include <vector>
#include <math.h>
#include "datacor.hpp"

#define START(timer) timer = std::chrono::steady_clock::now()
#define ITV(start, stop) std::chrono::duration_cast<std::chrono::nanoseconds> (stop - start).count() / 1000000.0
#define MEASURE(timer) ITV(timer, std::chrono::steady_clock::now())

const int N = 499849;
const int height =707;
const int width = 707;
const int M = height*height;

int getIndex(int x, int y, int width){
    return x+ (y * width);
}

std::vector<double> normalize(std::vector<double> data){
    std::vector<double> result = data;
    double sum = 0;
    double avg = 0;
    double len = 0;
    for(int y = 0; y < height; y++){
        sum = 0;
        len = 0;
        for(int x = 0; x < width; x++){
            sum += result.at(getIndex(x, y, width));
        }
        avg = sum/(width * 1.0);
        for(int x = 0; x < width; x++){
            result.at(getIndex(x,y, width)) -= avg;
        }
        for(int x = 0; x < width; x++){
            len += result.at(getIndex(x,y, width)) * result.at(getIndex(x,y, width));
        }
        len = sqrt(len);
        for (int x = 0; x < width; x++){
            result.at(getIndex(x, y, width)) = result.at(getIndex(x, y, width)) / len;
        }
    }
    return result;
}

std::vector<double> correlationMatrix(std::vector<double> data){

    std::vector<double> result(M);
    std::vector<double> normalized(N);
    normalized= normalize(data);
    try{
        for(int y = 0; y<height; y++){
            for(int x = 0; x < height; x++){
                double res = 0;
                for(int i = 0; i < width; i++){
                    res += normalized.at(getIndex(i,y,width)) * normalized.at(getIndex(i,x,width));
                }
                result.at(getIndex(x,y,height)) = res;
            }
        }
    }
    catch(const std::out_of_range e){
        std::cout << "errror in correlation matrix";
    }
    return result;
}

int main(){
    std::vector<double> data = arr5;
    START(auto code_timer);
    //std::chrono::high_resolution_clock::time_point begin = std::chrono::high_resolution_clock::now();
    std::vector<double> arr(M);
     arr= correlationMatrix(data);
    auto time = MEASURE(code_timer);
    //std::chrono::high_resolution_clock::time_point end = std::chrono::high_resolution_clock::now();
//    for (int y = 0; y < height; y++){
//            for (int x = 0; x < height; x++){
//                std::cout << arr.at(getIndex(x,y,height)) << "\t";
//            }
//            std::cout << std::endl;
//        }
    std::cout << "Elapsed time in miliseconds : "<< time<< " ms";
    return 0;
}