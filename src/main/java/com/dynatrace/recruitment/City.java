package com.dynatrace.recruitment;

public class City {

    public String name;
    public CountryCode countryCode;

    public City(String name, CountryCode countryCode) {
        this.name = name;
        this.countryCode = countryCode;
    }

    public City(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getCountryName() {
        switch (countryCode) {
            case AT: return "Austria";
            case PL: return "Poland";
            case US: return "United States";
            default: return null;
        }
    }
}
