package ru.usov.testbankapp.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DBProperties {
    public static java.util.Properties properties() {
        java.util.Properties property = new java.util.Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("src/main/resources/db.properties");
        } catch (
                FileNotFoundException e) {
            System.out.println("db.properties file not found");
            e.printStackTrace();
        }
        try {
            property.load(fis);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return property;
    }
}
