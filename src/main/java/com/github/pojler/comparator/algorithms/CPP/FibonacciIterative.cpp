#include<iostream>
#include<chrono>
#include<array>

const int n = 200;
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
    std::chrono::high_resolution_clock::time_point begin = std::chrono::high_resolution_clock::now();
    long int n = fib();
    std::chrono::high_resolution_clock::time_point end = std::chrono::high_resolution_clock::now();
    std::cout << n << std::endl;
    std::cout << "Elapsed time in miliseconds : "<< std::chrono::duration_cast<std::chrono::milliseconds>(end -begin).count()<< " ms";
    return 0;
}