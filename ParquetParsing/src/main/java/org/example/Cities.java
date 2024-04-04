package org.example;

public class Cities {
    String continent;
    Country country;

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "continent: " + continent + ", country: " + country + "\n";
    }
}
