package org.example;

import java.util.List;

public class Country {
    String name;
    List<CityVO> city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityVO> getCity() {
        return city;
    }

    public void setCity(List<CityVO> city) {
        this.city = city;
    }

    public String printCity() {
        StringBuilder sb = new StringBuilder();

        for (CityVO cityVO : city) {
            sb.append(cityVO.array_element);
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);

        return sb.toString();
    }

    @Override
    public String toString() {
        return "{ name: " + name + ", city: " + printCity() +" }";
    }
}
