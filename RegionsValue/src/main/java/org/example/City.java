package org.example;

public class City {
    private String name;
    private String foundation;
    private String population;
    private String region;
    private String district;
    City(String name,String region,String district,String population,String foundation){
        this.region =region;
        this.district =district;
        this.name =name;
        this.population =population;
        this.foundation = foundation;
    }
}


