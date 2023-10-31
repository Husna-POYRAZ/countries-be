package com.hpoyraz.countriesbe.controller;

import com.hpoyraz.countriesbe.dto.CountryDto;
import com.hpoyraz.countriesbe.entity.Country;
import com.hpoyraz.countriesbe.mapper.ICountryMapper;
import com.hpoyraz.countriesbe.service.interfaces.ICountryService;
import com.hpoyraz.countriesbe.service.interfaces.II18nMessageService;
import com.hpoyraz.countriesbe.util.GenericResponse;
import com.hpoyraz.countriesbe.util.constants.Api;
import com.hpoyraz.countriesbe.util.constants.i18n.I18nConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping(Api.Country.BASE_URL)
public class CountryController {

    private final ICountryService countryService;
    private final ICountryMapper countryMapper;
    private final II18nMessageService messageService;
    @GetMapping
    public GenericResponse<List<Country>> getAllCountries() {
        var response = countryService.getAllCountries();
        var locale = LocaleContextHolder.getLocale();

        return GenericResponse.<List<Country>>builder()
                .success(Boolean.TRUE)
                .message(messageService.getMessage(I18nConstants.GET_ALL_COUNTRIES_SUCCESS, locale))
                .data(response)
                .build();
    }

    @PostMapping
    public GenericResponse<CountryDto> insertCountry(@RequestBody CountryDto countryDto) {
        Country mappedCountry = countryMapper.toCountry(countryDto);
        Country country = countryService.insertCountry(mappedCountry);
        var locale = LocaleContextHolder.getLocale();
        var response = countryMapper.fromCountry(country);
        return GenericResponse.<CountryDto>builder()
                .success(Boolean.TRUE)
                .message(messageService.getMessage(I18nConstants.COUNTRY_INSERT_SUCCESS, locale))
                .data(response)
                .build();
    }

    @PostMapping(Api.Country.INSERT_ALL)
    public GenericResponse<List<Country>> insertAllCountries() {
        var responce = countryService.insertAllCountries();
        var locale = LocaleContextHolder.getLocale();
        return GenericResponse.<List<Country>>builder()
                .success(Boolean.TRUE)
                .message(messageService.getMessage(I18nConstants.COUNTRY_INSERT_SUCCESS, locale))
                .data(responce)
                .build();
    }
}
