package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) {
        City maxPopulationCity = null;
        int maxPopulation = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\danil\\Downloads\\zadacha.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] valuesold = line.split(";");
                String[] values = new String[valuesold.length + 1];
                System.arraycopy(valuesold, 0, values, 0, valuesold.length);
                int index = Integer.parseInt(values[0]);
                int population = Integer.parseInt(values[4]);

                City city = new City(index,population);

                if (population > maxPopulation) {
                    maxPopulation = population;
                    maxPopulationCity = city;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if (maxPopulationCity != null) {
            System.out.println(maxPopulationCity.PrintCity());
        } else {
            System.out.println("Нет данных о городах.");
        }
    }
}
