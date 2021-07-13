#include <iostream>
#include <chrono>
#include <math.h>
#include <array>

#define START(timer) timer = std::chrono::steady_clock::now()
#define ITV(start, stop) std::chrono::duration_cast<std::chrono::nanoseconds> (stop - start).count() / 1000000.0
#define MEASURE(timer) ITV(timer, std::chrono::steady_clock::now())


const int N = 500000;

std::array<bool, N> generateTable(){
    std::array<bool, N> table;
    table.at(0) = false;
    table.at(1) = false;
    for(int i =2; i < N; i++){
        table.at(i) = true;
    }
    return table;
}

std::array<bool, N> eratostenes(){
    std::array<bool, N> table = generateTable();
    int bound = sqrt(N);
    for (int i= 0; i < bound; i++){
        if(table.at(i) == true){
            for(int j = i*2; j < N; j+=i){
                table.at(j) = false;
            }
        }
    }
    return table;
}

int main(){
    START(auto code_timer);
    //std::chrono::high_resolution_clock::time_point begin = std::chrono::high_resolution_clock::now();
    std::array<bool, N> arr = eratostenes();
    //std::chrono::high_resolution_clock::time_point end = std::chrono::high_resolution_clock::now();
    auto time = MEASURE(code_timer);
    if(N <=10000){
        for(int i = 0; i<N; i++){
            if(arr.at(i)){
                std::cout << i << " ";
            }
        }
    }
    std::cout << "Elapsed time in miliseconds : "<< time<< " ms";
}