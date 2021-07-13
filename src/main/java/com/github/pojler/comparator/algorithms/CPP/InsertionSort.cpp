#include <iostream>
#include <chrono>
#include <array>
#include <math.h>
#include "data.hpp"

#define START(timer) timer = std::chrono::steady_clock::now()
#define ITV(start, stop) std::chrono::duration_cast<std::chrono::nanoseconds> (stop - start).count() / 1000000.0
#define MEASURE(timer) ITV(timer, std::chrono::steady_clock::now())


const int N = 500000;

std::array<int, N> sort(std::array<int, N> arr){
    std::array<int, N> result = arr;
    for(int i = 1; i < N; ++i){
        int key = result.at(i);
        int j = i -1;
    

        while(j >= 0 && result.at(j) > key){
            result.at(j+1) = result.at(j);
            j = j-1;
        }
        result.at(j+1) = key;
    }
    return result;
}

int main(){
    std::array<int, N> arr = arr5;
    START(auto code_timer);
    //std::chrono::high_resolution_clock::time_point begin = std::chrono::high_resolution_clock::now();
    std::array<int, N> result = sort(arr);
    auto time = MEASURE(code_timer);
    //std::chrono::high_resolution_clock::time_point end = std::chrono::high_resolution_clock::now();
    //  for (int i = 0; i < N; i++)
    //         std::cout<<result.at(i) << " ";
    std::cout << std::endl;
    std::cout << "Elapsed time in miliseconds : "<< time<< " ms";
    return 0;

}