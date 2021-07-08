package com.github.pojler.comparator.algorithms.Java;

public class Eratostenes {

    boolean[] generateTable(int n){
        boolean[] table = new boolean[n+1];
        table[0] = false;
        table[1] = false;
        for (int i = 2; i < n+1; i++   ){
            table[i] = true;
        }
        return table;
    }

    boolean[] eratostenes(int n){
        boolean[] table = generateTable(n);
        double bound = Math.sqrt(n);
        for(int i = 2; i<bound; i++){
            if(table[i] == true){
                for(int j = i*2; j<n+1; j+=i){
                    table[j] = false;
                }
            }
        }
        return table;
    }


    public static void main(String[] args) {
        Eratostenes eras = new Eratostenes();
        long startTime = System.nanoTime();
        boolean[] table = eras.eratostenes(50);
//        boolean[] table = eras.eratostenes(500);
//        boolean[] table = eras.eratostenes(5000);
//        boolean[] table = eras.eratostenes(50000);
//        boolean[] table = eras.eratostenes(500000);
        long elapsed = System.nanoTime() -startTime;
        if(table.length < 10000) {
            for (int i = 0; i < table.length; i++) {
                System.out.print(table[i] ? i + " " : "");
            }
        }
        System.out.println();
        System.out.println("Elapsed: " +elapsed/1e6 +" ms");

    }

}
