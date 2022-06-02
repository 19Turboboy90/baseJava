package com.baseJava.webApp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainFail {
    public static void main(String[] args) {
        ArrayList<File> fileList = new ArrayList<>();
        getFile(new File("E:\\Программирование\\JAVA\\Java_Курс\\basejava"), fileList);
        for (File file : fileList) {
            System.out.println(file.getAbsolutePath());
        }
    }

    private static void getFile(File rootFile, List<File> fileList) {
        if (rootFile.isDirectory()) {
            File[] directoryFiles = rootFile.listFiles();
            if (directoryFiles != null) {
                for (File file : directoryFiles) {
                    if (file.isDirectory()) {
                        getFile(file, fileList);
                    } else {
                        if (file.isFile()) {
                            fileList.add(file);
                        }
                    }
                }
            }
        }
    }
}
