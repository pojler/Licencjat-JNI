#include <iostream>
#include <chrono>


int fib(int n){
    if(n <= 1){
        return n;
    }
    return fib(n-1)+fib(n-2);
}

int main(){
    std::chrono::high_resolution_clock::time_point begin = std::chrono::high_resolution_clock::now();
    int n = fib(50);
    std::chrono::high_resolution_clock::time_point end = std::chrono::high_resolution_clock::now();
    std::cout << n << std::endl;
    std::cout << "Elapsed time in miliseconds : "<< std::chrono::duration_cast<std::chrono::milliseconds>(end -begin).count()<< " ms";
    return 0;
}