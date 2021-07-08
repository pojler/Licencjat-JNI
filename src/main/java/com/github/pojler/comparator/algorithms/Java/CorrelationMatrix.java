package com.github.pojler.comparator.algorithms.Java;

import java.util.Random;

public class CorrelationMatrix {

    private void randomFill(double[] arr){
        Random r = new Random();
        for(int i = 0; i < arr.length; i++){
            arr[i] = r.nextInt(2000000);
        }
    }


    private int getIndex(int x, int y, int width){
        return x + (y * width);
    }

    private double[] normalize(double[] data, int width, int height){
        if(data.length != width * height){
            System.out.println("Incorrect data");
            return null;
        }
        double[] result = data;
        double sum = 0;
        double avg = 0;
        double len = 0;
        for (int y = 0; y < height; y++){
           sum = 0;
           len = 0;
           for (int x = 0; x < width; x++){
               sum += result[getIndex(x, y, width)];
           }
           avg = sum/(width * 1.0);
           for (int x = 0; x < width; x++){
               result[getIndex(x, y, width)]-= avg;
           }
           for (int x = 0; x < width; x++){
               len += result[getIndex(x, y, width)] * result[getIndex(x, y, width)];
           }
           len = Math.sqrt(len);
           for (int x = 0; x < width; x++){
               result[getIndex(x, y, width)] = result[getIndex(x, y, width)] / len;
           }

        }
        return result;
    }
    private double[] correlationMatrix(double[] data, int width, int height){
        double[] result = new double[height*height];
        double[] normalizedData = normalize(data, width, height);
        for(int y = 0; y<height; y++){
            for(int x =0; x<height; x++){
                double res = 0;
                for(int i  = 0; i < width; i++){
                    res += normalizedData[getIndex(i,y,width)]* normalizedData[getIndex(i,x,width)];
                }
                result[getIndex(x,y,height)] = res;
            }
        }
         return result;
    }

    public static void main(String[] args) {
        CorrelationMatrix c = new CorrelationMatrix();
//        double[] data = new double[49];
//        double[] data = new double[484];
//        double[] data = new double[4900];
//        double[] data = new double[49729];
        double[] data = new double[499849];
        c.randomFill(data);
        int width = (int)Math.sqrt(data.length);
        int height = (int)Math.sqrt(data.length);
        long startTime = System.nanoTime();
        double[] result = c.correlationMatrix(data, width, height);
        long elapsed = System.nanoTime() -startTime;
        for (int y = 0; y < height; y++){
            for (int x = 0; x < height; x++){
                System.out.print(result[c.getIndex(x,y, height)] + "\t");
            }
            System.out.println();
        }
        System.out.println("Elapsed: " +elapsed/1e6 +" ms");
    }

}
