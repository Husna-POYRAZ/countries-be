package com.hpoyraz.countriesbe.controller;

import com.hpoyraz.countriesbe.entity.Country;
import com.hpoyraz.countriesbe.service.imp.CountryService;
import com.hpoyraz.countriesbe.util.constants.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Api.Country.BASE_URL)
public class CountryController {

    private final CountryService countryService;
    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }
}
