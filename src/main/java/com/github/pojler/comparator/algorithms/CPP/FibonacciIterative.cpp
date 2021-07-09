#include<iostream>
#include<chrono>
#include<array>

#define START(timer) timer = std::chrono::steady_clock::now()
#define ITV(start, stop) std::chrono::duration_cast<std::chrono::nanoseconds> (stop - start).count() / 1000000.0
#define MEASURE(timer) ITV(timer, std::chrono::steady_clock::now())

const int n = 500000;
long int fib(){

    std::array<long int, n> tab;
    tab.at(0) = 0;
    tab.at(1) = 1;

    for (int i = 2; i < n; i++){
        tab.at(i) = tab.at(i-1) + tab.at(i-2);
    }
    return tab.at(n-1);
}

int main(){
    START(auto code_timer);
    //std::chrono::high_resolution_clock::time_point begin = std::chrono::high_resolution_clock::now();
    long int result = fib();
    auto time = MEASURE(code_timer);
    //std::chrono::high_resolution_clock::time_point end = std::chrono::high_resolution_clock::now();
    std::cout << result << std::endl;
    std::cout << "Elapsed time in miliseconds : "<< time<< " ms";
    return 0;
}