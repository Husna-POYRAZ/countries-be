package com.hpoyraz.countriesbe.service.interfaces;

import com.hpoyraz.countriesbe.entity.Country;

import java.util.List;

public interface ICountryService {
    List<Country> getAllCountries();

    List<Country> insertAllCountries();
}
