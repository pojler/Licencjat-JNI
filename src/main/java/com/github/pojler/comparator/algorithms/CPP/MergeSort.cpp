#include <iostream>
#include <math.h>
#include <chrono>
#include "datamer.hpp"

void merge(int arr[], int begin, int middle, int end){
    
    int n1 = middle - begin + 1;
    int n2 = end - middle;

    int Set1[n1];
    int Set2[n2];

    for(int i = 0; i <n1; ++i){
        Set1[i] = arr[begin+1];
    }
    for (int j = 0; j < n2; ++j){
        Set2[j] = arr[middle + 1 + j];
    }
    
    int i = 0;
    int j = 0;
    int k = begin;
    while(i < n1 && j < n2){
        if(Set1[i] < Set2[j]){
            arr[k] = Set1[i];
            i++;
        }
        else{
            arr[k] = Set2[j];
            j++;
        }
        k++;
    }
    while (i < n1) {
            arr[k] = Set1[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = Set2[j];
            j++;
            k++;
        }

}

void sort(int arr[], int begin, int end)
{
    if (begin < end) {
        int m =begin+ (end-begin)/2;

        sort(arr, begin, m);
        sort(arr, m + 1, end);

        merge(arr, begin, m, end);
    }
}

int main(){
    std::chrono::high_resolution_clock::time_point begin = std::chrono::high_resolution_clock::now();
    sort(arr5,0, 500000-1);
    std::chrono::high_resolution_clock::time_point end = std::chrono::high_resolution_clock::now();
    for (int i = 0; i < 1000; i++){
            std::cout<<arr1[i] << " ";
    }
    std::cout<<std::endl;
    std::cout << "Elapsed time in miliseconds : "<< std::chrono::duration_cast<std::chrono::milliseconds>(end - begin).count()<< " ms";
    return 0;
}