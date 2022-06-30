package com.baseJava.webApp.storage;

import com.baseJava.webApp.storage.serializer.ObjectStreamStorage;

public class PathStorageTest extends AbstractStorageTest{

    public PathStorageTest() {
        super(new PathStorage("E:\\Программирование\\JAVA\\Java_Курс\\basejava\\storage", new ObjectStreamStorage()));
    }
}