package com.dynatrace.recruitment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CityCache {

    private Set<City> cities = new HashSet<>();

    public City getCity(String cityName) {
        City searchedCity = new City(cityName);
        return cities.stream()
                .filter(searchedCity::equals)
                .findAny()
                .orElse(null);
    }

    public void addCity(City city) {
        cities.remove(city);
        cities.add(city);
    }

    public List<City> getCities() {
        return new ArrayList<>(cities);
    }
}
