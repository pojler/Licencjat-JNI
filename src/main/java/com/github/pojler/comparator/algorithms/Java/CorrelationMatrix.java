package com.github.pojler.comparator.algorithms.Java;

public class CorrelationMatrix {

    private int getIndex(int x, int y, int width){
        return x + (y * width);
    }

    private double[] correlationMatrix(double[] data, int width, int height){
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

    public static void main(String[] args) {
        CorrelationMatrix c = new CorrelationMatrix();
        double[] data = {1,2,3,4,5,6,7,8,9,10,11,12};
        int width = 4;
        int height =3;
        double[] result = c.correlationMatrix(data, width, height);
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                System.out.print(result[c.getIndex(x,y, width)] + " ");
            }
            System.out.println();
        }
    }

}
