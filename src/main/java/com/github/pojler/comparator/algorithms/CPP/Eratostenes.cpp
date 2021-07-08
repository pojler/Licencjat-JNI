#include <iostream>
#include <chrono>
#include <math.h>
#include <array>

const int N = 10000;

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
    std::chrono::high_resolution_clock::time_point begin = std::chrono::high_resolution_clock::now();
    std::array<bool, N> arr = eratostenes();
    std::chrono::high_resolution_clock::time_point end = std::chrono::high_resolution_clock::now();
    if(N <=10000){
        for(int i = 0; i<N; i++){
            if(arr.at(i)){
                std::cout << i << " ";
            }
        }
    }
    std::cout << "Elapsed time in miliseconds : "<< std::chrono::duration_cast<std::chrono::milliseconds>(end -begin).count()<< " ms";
}