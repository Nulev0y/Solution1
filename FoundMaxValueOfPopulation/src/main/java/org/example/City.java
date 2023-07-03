package org.example;

public class City {
    private int index;

    private int population;
    City(int index,int population){
        this.index = index;
        this.population =population;

    }

    public String PrintCity() {
        return "["+index +"] " +"= "+ population;
    }
}

