package com.baseJava.webApp;

public class Deadlock {
    public static String lock1 = "Lock_1";
    public static String lock2 = "Lock_2";

    public static void main(String[] args) {
        deadlock(lock1, lock2);
        deadlock(lock2, lock1);
    }

    private static void deadlock(String lock1, String lock2) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " attempt to lock the monitor 'lock1'");
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + " has blocked");
                System.out.println(Thread.currentThread().getName() + " attempt to lock the monitor lock2");
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + " The lock1 and lock2 object monitor is locked");
                }
            }
        }).start();
    }
}