#include <iostream>
#include <chrono>
#include <array>
#include <math.h>

const int N = 12;
const int height = 3;
const int width = 4;
const int M = height*height;

int getIndex(int x, int y, int width){
    return x+ (y * width);
}

std::array<double, N> normalize(std::array<double, N> data){
    std::array<double, N> result = data;
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

std::array<double, M> correlationMatrix(std::array<double, N> data){
    std::array<double, M> result;
    std::array<double, N> normalized = normalize(data);
    for(int y = 0; y<height; y++){
        for(int x = 0; x < height; x++){
            double res = 0;
            for(int i = 0; i < width; i++){
                res += normalized.at(getIndex(i,y,width)) * normalized.at(getIndex(i,x,width));
            }
            result.at(getIndex(x,y,height)) = res;
        }
    }
    return result;
}

int main(){
    std::array<double, N> data = {2,1,3,7,1,9,9,8,80,50,70,20};
    std::chrono::high_resolution_clock::time_point begin = std::chrono::high_resolution_clock::now();
    std::array<double, M> arr = correlationMatrix(data);
    std::chrono::high_resolution_clock::time_point end = std::chrono::high_resolution_clock::now();
    for (int y = 0; y < height; y++){
            for (int x = 0; x < height; x++){
                std::cout << arr.at(getIndex(x,y,height)) << "\t";
            }
            std::cout << std::endl;
        }
    std::cout << "Elapsed time in miliseconds : "<< std::chrono::duration_cast<std::chrono::milliseconds>(end -begin).count()<< " ms";
    return 0;
}