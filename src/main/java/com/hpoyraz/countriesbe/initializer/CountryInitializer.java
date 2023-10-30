package com.hpoyraz.countriesbe.initializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hpoyraz.countriesbe.entity.Country;import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.*;

@Slf4j
public class CountryInitializer {
    public static List<Country>  readCountries() {
        String root = System.getProperty("user.dir");

        List<Country> countryList = new ArrayList<>();

        try {
            File countryJSON = new File(root + "/asset/countries.json");
            Map<String, Map<String, Object>> result = new ObjectMapper().readValue(countryJSON, HashMap.class);

            for (String code : result.keySet()) {
                Map<String, Object> valueMap = result.get(code);
                String name = valueMap.get("name").toString();
                String nativeName = valueMap.get("native").toString();
                String capital = valueMap.get("capital").toString();
                String continent = valueMap.get("continent").toString();
                String currency = valueMap.get("currency").toString();
                String languages = valueMap.get("languages").toString();
                int phone;
                try {
                    phone = Integer.parseInt(valueMap.get("phone").toString());
                } catch (NumberFormatException numberFormatException) {
                    phone = -1;
                }
                String flag = generateFlagUrl(code);

                Country country = Country.builder()
                        .code(code)
                        .name(name)
                        .nativeName(nativeName)
                        .capital(capital)
                        .continent(continent)
                        .currency(currency)
                        .languages(languages)
                        .phone(phone)
                        .flag(flag)
                        .build();

                countryList.add(country);
            }
        } catch (Exception exception) {
            System.out.println("An error occurred in file operations!");
            log.error("An error occurred in file operations! Exception detail: " + exception.getMessage());
        }

        return countryList;
    }

    private static String generateFlagUrl(String id) {
        return "http://aedemirsen.bilgimeclisi.com/country_flags/" + id + ".svg";
    }
}
