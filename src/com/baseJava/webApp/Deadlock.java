package com.baseJava.webApp;

public class Deadlock {
    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        thread2.start();
    }
}

class Thread1 extends Thread {
    @Override
    public void run() {
        System.out.println(Thread1.currentThread().getName() + " attempt to lock the monitor 'lock1'");
        synchronized (Deadlock.lock1) {
            System.out.println(Thread1.currentThread().getName() + " has blocked");
            System.out.println(Thread1.currentThread().getName() + " attempt to lock the monitor lock2");
            synchronized (Deadlock.lock2) {
                System.out.println(Thread1.currentThread().getName() + " The lock1 and lock2 object monitor is locked");
            }
        }
    }
}

class Thread2 extends Thread {
    @Override
    public void run() {
        System.out.println(Thread2.currentThread().getName() + " attempt to lock the monitor 'lock2'");
        synchronized (Deadlock.lock2) {
            System.out.println(Thread2.currentThread().getName() + " has blocked");
            System.out.println(Thread2.currentThread().getName() + " attempt to lock the monitor lock1");
            synchronized (Deadlock.lock1) {
                System.out.println(Thread2.currentThread().getName() + " The lock1 and lock2 object monitor is locked");
            }
        }
    }
}
