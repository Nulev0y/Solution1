package org.example;

import org.example.City;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<City> cities = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\danil\\Downloads\\zadacha.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] valuesold = line.split(";");
                String[] values = new String[valuesold.length + 2];
                System.arraycopy(valuesold, 0, values, 0, valuesold.length);
                String index = values[0];
                String name = values[1];
                String region = values[2];
                String district = values[3];
                String population = values[4];
                String foundation = values[5];

                City city = new City(name,region,district,population,foundation);
                cities.add(city);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(cities, new Comparator<City>() {
            @Override
            public int compare(City city1, City city2) {
                return city2.getName().compareTo(city1.getName());
            }
        });

        for (City city : cities) {
            System.out.println(city.PrintCity());
        }
    }
}
