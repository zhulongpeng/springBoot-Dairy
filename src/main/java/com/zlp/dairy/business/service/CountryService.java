package com.zlp.dairy.business.service;


import com.zlp.dairy.business.entity.Country;

import java.util.List;
import java.util.Set;

public interface CountryService {

    List<Country> findCountriesByCodeSet(Set<String> countrySet);

}
