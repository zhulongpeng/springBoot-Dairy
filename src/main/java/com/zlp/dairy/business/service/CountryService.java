package com.zlp.dairy.business.service;


import com.zlp.dairy.business.entity.Country;

import java.util.List;
import java.util.Set;

public interface CountryService {

    List<Country> findCountriesByCodeSet(Set<String> countrySet);

    Boolean findCountryByCodeAndLanguage(String code, String language);

    String saveCountry(Country country);

    List<String> findCodeGroupByCode();

    List<Country> findCountriesByCode(String code);
}
