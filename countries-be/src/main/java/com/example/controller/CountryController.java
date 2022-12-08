package com.example.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Country;
import com.example.utils.FileOperation;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
public class CountryController {

    @GetMapping("countries")
    public Map<String, Map<String, Object>> countries() throws StreamReadException, DatabindException, IOException {
        List<Country> countries = new ArrayList<Country>(); 
        Map<String, Map<String, Object>> countriesMap = FileOperation.readJsonFile("/Users/hp/development/country/countries-be/countries-be/src/asset/countries.json");

        return countriesMap;
    }

    @GetMapping("country")
    public List<Country> countries(
            @RequestParam(value = "countryCode", defaultValue = "") String countryCode) throws IOException {

        List<Country> countries = new ArrayList<>();

        // parse json to country models and add them to list
        File jsonFile = new File("src/assets/countries.json");
        Map<String, Map<String, Object>> result = new ObjectMapper().readValue(jsonFile, HashMap.class);

        // all country codes.
        Set<String> keySet = result.keySet();
        // if there is requested param, key set will be updated.
        if (!countryCode.isEmpty()) {
            keySet = new HashSet<>();
            keySet.add(countryCode);
        }

        // Stream for flag data
        InputStream in = null;

        for (String id : keySet) {
            in = null;
            Map<String, Object> m = result.get(id);
            String name = m.get("name").toString();
            String nativeName = m.get("native").toString();
            int phone;
            try {
                phone = Integer.parseInt(m.get("phone").toString());
            } catch (NumberFormatException e) {
                phone = -999;
            }
            String continent = m.get("continent").toString();
            String capital = m.get("capital").toString();
            String currency = m.get("currency").toString();
            List<String> languages = (ArrayList<String>) m.get("languages");
            in = new BufferedInputStream(
                    new FileInputStream("src/assets/country_flags/" + id.toLowerCase(Locale.ENGLISH) + ".svg"));
            Country c = new Country(id, name, nativeName, phone, continent, capital, currency, languages,
                    toList(in.readAllBytes()));
            countries.add(c);
        }

        Collections.sort(countries);
        in.close();
        return countries;
    }

    private List<Byte> toList(byte[] bytesArr) {
        Byte[] b = IntStream.range(0, bytesArr.length)
                .mapToObj(i -> bytesArr[i])
                .toArray(Byte[]::new);
        return Arrays.asList(b);
    }

    
}
