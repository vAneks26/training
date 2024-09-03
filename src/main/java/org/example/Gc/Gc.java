package org.example.Gc;

import org.example.Stream2.Data;



public class Gc {
    public static void main(String[] args) {
        method();
    }

    private static void method(){
        Runtime runtime = Runtime.getRuntime();
        System.out.println("общая память до создания " + runtime.totalMemory());
        System.out.println("свободная память до создания " + runtime.freeMemory());
        for (int i=0;i<1000000000; i++){
            Data date = new Data();
            date=null;
        }
        System.out.println("общая память после создания " + runtime.totalMemory());
        System.out.println("свободная память после создания " + runtime.freeMemory());
        System.gc();
        System.out.println("общая память после gc " + runtime.totalMemory());
        System.out.println("свободная память после gc " + runtime.freeMemory());
    }
}
