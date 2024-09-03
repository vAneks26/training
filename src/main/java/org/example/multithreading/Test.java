package org.example.multithreading;

public class Test {
    public static void main(String[] args) {
        MyTread myTread = new MyTread();
        myTread.start();

        MyTread myTread2 =new MyTread();
        myTread2.start();

//        System.out.println("Hi main");
    }
}

class MyTread extends Thread{
    public void run() {
        for(int i = 0; i <= 100; i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hi " + i);
        }
    }
}