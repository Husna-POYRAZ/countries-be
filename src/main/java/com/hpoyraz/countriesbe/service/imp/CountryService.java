package com.hpoyraz.countriesbe.service.imp;

import com.hpoyraz.countriesbe.entity.Country;
import com.hpoyraz.countriesbe.exception.CountryAlreadyExistsException;
import com.hpoyraz.countriesbe.initializer.CountryInitializer;
import com.hpoyraz.countriesbe.repository.ICountryRepository;
import com.hpoyraz.countriesbe.service.interfaces.ICountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService implements ICountryService {

    private final ICountryRepository countryRepository;
    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public List<Country> insertAllCountries() {
        return countryRepository.saveAll(CountryInitializer.readCountries());
    }

    @Override
    public Country insertCountry(Country country) {
        boolean existingCountry = countryRepository.findByCode(country.getCode()).isPresent();
        if (existingCountry) {
            throw new CountryAlreadyExistsException();
        }
        return countryRepository.save(country);
    }
}
