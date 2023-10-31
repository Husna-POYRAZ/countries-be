package com.hpoyraz.countriesbe.mapper;

import com.hpoyraz.countriesbe.dto.CountryDto;
import com.hpoyraz.countriesbe.entity.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICountryMapper {
    Country toCountry(CountryDto countryDto);
    CountryDto fromCountry(Country country);
}




//public class CountryMapper {
//    public Country fromCountryDto(CountryDto countryDto) {
//        return Country.builder()
//                .code(countryDto.getCode())
//                .name(countryDto.getName())
//                .flag(countryDto.getFlag())
//                .phone(countryDto.getPhone())
//                .capital(countryDto.getCapital())
//                .languages(countryDto.getLanguages())
//                .currency(countryDto.getCurrency())
//                .continent(countryDto.getContinent())
//                .nativeName(countryDto.getNativeName())
//                .build();
//    }
//
//    public CountryDto toCountryDto(Country country) {
//        return CountryDto.builder()
//                .id(country.getId())
//                .code(country.getCode())
//                .name(country.getName())
//                .flag(country.getFlag())
//                .phone(country.getPhone())
//                .capital(country.getCapital())
//                .languages(country.getLanguages())
//                .currency(country.getCurrency())
//                .continent(country.getContinent())
//                .nativeName(country.getNativeName())
//                .build();
//    }
//}
