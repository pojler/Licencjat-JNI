#include<iostream>
#include<chrono>
#include<math.h>

bool* generateTable (int n){
    bool* table = new bool[n+1];
    table[0] = false;
    table[1] = false;
    for(int i = 2; i <n+1; i++){
        table[i] = true;
    }
    return table;
}

bool* eratostenes (int n){
    bool* table = generateTable(n);
    int bound = sqrt(n);
    for (int i = 0; i <bound; i++){
        if(table[i] == true){
            for(int j = i*2; j<n+1; j+=i){
                table[j] = false;
            }
        }

    }
    return table;
}

int main(){
    int n = 1000000000;
    std::chrono::high_resolution_clock::time_point begin = std::chrono::high_resolution_clock::now();
    bool* table = eratostenes(n);
    std::chrono::high_resolution_clock::time_point end = std::chrono::high_resolution_clock::now();
    if(n <= 10000){
        for (int i = 0; i < n; i++){
            if(table[i]){
                std::cout << i << " ";
            }
        }
    }
    std::cout << "Elapsed time in miliseconds : "<< std::chrono::duration_cast<std::chrono::milliseconds>(end -begin).count()<< " ms";
    delete[] table;
}