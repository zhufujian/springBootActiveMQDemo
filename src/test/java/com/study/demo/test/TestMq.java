package com.study.demo.test;

import com.study.demo.controler.Producter;

public class TestMq {
    public static void main(String[] args){
        Producter producter = new Producter();
        producter.init();
        TestMq testMq = new TestMq();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Thread 1
        new Thread(testMq.new ProductorMq(producter)).start();
        //Thread 2
       // new Thread(testMq.new ProductorMq(producter)).start();
        //Thread 3
        new Thread(testMq.new ProductorMq(producter)).start();
    }

    private class ProductorMq implements Runnable{
        Producter producter;
        public ProductorMq(Producter producter){
            this.producter = producter;
        }

        @Override
        public void run() {
            while(true){
                try {
                    producter.sendMessage("ActiveMQ-MQ");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}