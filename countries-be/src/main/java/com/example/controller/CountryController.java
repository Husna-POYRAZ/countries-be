package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Country;
import com.example.utils.FileOperation;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;


@CrossOrigin
@RestController
public class CountryController {

    private final List<Country> countries = new ArrayList<>();
    private Map<String, Map<String, Object>> countriesMap = new HashMap<>();

    public CountryController() {
        try {
            countriesMap = FileOperation.readJsonFile("/Users/hp/development/country/countries-be/countries-be/src/asset/countries.json");
            // create Country obje
        // get all country codes
        Set<String> keySet = countriesMap.keySet();
        for(String key : keySet) {
            Map<String, Object> country = countriesMap.get(key);
            String name = country.get("name").toString();
            String nativeName = country.get("native").toString();
            int phone;
            try {
                phone = Integer.parseInt(country.get("phone").toString());
            } catch (NumberFormatException e) {
                phone = -999;
            }
            String continent = country.get("continent").toString();
            String capital = country.get("capital").toString();
            String currency = country.get("currency").toString();
            List<String> languages = (ArrayList<String>) country.get("languages");
            Country c = new Country(key, name, nativeName, phone, continent, capital, currency, languages);
            
            countries.add(c);
        }
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }

        
        
    }

    @GetMapping("/countries")
    public List<Country> getCountries() throws StreamReadException, DatabindException {
        return countries;
    }


    @GetMapping("/countries/{id}")
    public List<Country> getCountryByCountryCode(@PathVariable String id) throws IOException {
        List<Country> filteredByCountryCode = countries.stream().filter(country -> country.getId().equals(id)).collect(Collectors.toList());
        return filteredByCountryCode;
    }

    @GetMapping("/countries/{name}")
    public List<Country> getCountryByCountryName(@PathVariable String name) throws IOException {
        List<Country> filteredByCountryName = countries.stream().filter(country -> country.getName().equals(name)).collect(Collectors.toList());
        return filteredByCountryName;
    }
}
