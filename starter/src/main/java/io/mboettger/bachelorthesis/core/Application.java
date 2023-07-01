package io.mboettger.bachelorthesis.core;

public class Application {
    public static void main(String[] args) {
        System.out.println("Hello World");

        Object a = "123";

        ((String) a).substring(1);
    }
}
