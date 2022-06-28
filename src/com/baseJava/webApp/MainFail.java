package com.baseJava.webApp;

import java.io.File;

public class MainFail {
    public static void main(String[] args) {
        File file = new File("E:\\Программирование\\JAVA\\Java_Курс\\basejava");
        getFile(file, "");
    }

    private static void getFile(File rootFile, String space) {
        if (rootFile.isDirectory()) {
            File[] directoryFiles = rootFile.listFiles();
            if (directoryFiles != null) {
                for (File file : directoryFiles) {
                    if (file.isFile()) {
                        System.out.println(space + "File: " + file.getName());
                    } else if (file.isDirectory()) {
                        System.out.println(space + "Directory: " + file.getName());
                        getFile(file, space + "   ");
                    }
                }
            }
        }
    }
}

