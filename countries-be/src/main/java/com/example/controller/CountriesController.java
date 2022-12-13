package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Country;
import com.example.utils.FileOperation;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;


@CrossOrigin
@RestController
@RequestMapping("/countries")
public class CountriesController {

    private final List<Country> countries = new ArrayList<>();
    private Map<String, Map<String, Object>> countriesMap = new HashMap<>();

    public CountriesController() {
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


    @GetMapping
    public List<Country> getAllCountries() throws StreamReadException, DatabindException {
        return countries;
    }

    @GetMapping("/params")
    public List<Country> getCountriesByParams(@RequestParam(value = "id", defaultValue = "") String id,
                                           @RequestParam(value = "name", defaultValue = "") String name,
                                           @RequestParam(value = "continent", defaultValue = "") String continent,
                                           @RequestParam(value = "currency", defaultValue = "") String currency,
                                           @RequestParam(value = "phoneCode", defaultValue = "") String phoneCode,
                                           @RequestParam(value = "order", defaultValue = "") String orderBy){
        List<Country> queriedCountries = id.isEmpty() ? countries : countries.stream().filter(f->f.getId().equals(id)).collect(Collectors.toList());
        queriedCountries = name.isEmpty() ? queriedCountries : queriedCountries.stream().filter(f->f.getName().equals(name)).collect(Collectors.toList());
        queriedCountries = continent.isEmpty() ? queriedCountries : queriedCountries.stream().filter(f->f.getContinent().equals(continent)).collect(Collectors.toList());
        queriedCountries = currency.isEmpty() ? queriedCountries : queriedCountries.stream().filter(f->f.getCurrency().equals(currency)).collect(Collectors.toList());
        queriedCountries = phoneCode.isEmpty() ? queriedCountries : queriedCountries.stream().filter(f->f.getPhoneCode() == Integer.parseInt(phoneCode)).collect(Collectors.toList());
        queriedCountries = orderBy.isEmpty() || orderBy.equals("asc") ? queriedCountries.stream().sorted(Comparator.comparingInt(Country::getPhoneCode))
                                                                        .collect(Collectors.toList()) : queriedCountries.stream()
                                                                        .sorted(Comparator.comparingInt(Country::getPhoneCode).reversed())
                                                                        .collect(Collectors.toList());


        return queriedCountries;

    }
    
 /*   
    @GetMapping
    public List<Country> getCountryByParams(@RequestParam(value="id", defaultValue = "") Optional<String> id) {
        if(id.isPresent()){
            List<Country> filteredByCountryCode = countries.stream().filter(country -> country.getId().equals(id)).collect(Collectors.toList());
            return  filteredByCountryCode;
        }  
        return countries;
    }
 */ 
}
