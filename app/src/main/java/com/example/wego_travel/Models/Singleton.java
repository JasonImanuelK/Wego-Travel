package com.example.wego_travel.Models;

public class Singleton {
    private static Singleton instance = null;

    public String token;

    private Singleton(){
        token = "";
    }

    public static Singleton getInstance(){
        if (instance == null){
            instance = new Singleton();
        }

        return instance;
    }
}