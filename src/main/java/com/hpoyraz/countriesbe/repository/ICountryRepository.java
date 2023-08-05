package com.hpoyraz.countriesbe.repository;

import com.hpoyraz.countriesbe.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICountryRepository extends JpaRepository<Country, String> {
}
