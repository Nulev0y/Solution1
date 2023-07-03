package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) {
        City maxFoundationCity = null;
        String maxFoundation = null;

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\danil\\Downloads\\zadacha.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] valuesold = line.split(";");
                String[] values = new String[valuesold.length + 1];
                System.arraycopy(valuesold, 0, values, 0, valuesold.length);
                String index = values[0];
                String name = values[1];
                String region = values[2];
                String district = values[3];
                String population = values[4];
                String foundation = values[5];

                City city = new City(name, region, district, population, foundation);

                if (maxFoundation == null || (foundation != null && foundation.compareTo(maxFoundation) > 0)) {
                    maxFoundation = foundation;
                    maxFoundationCity = city;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (maxFoundationCity != null) {
            System.out.println(maxFoundationCity.PrintFoundation());
        } else {
            System.out.println("Нет данных о городах.");
        }
    }
}
