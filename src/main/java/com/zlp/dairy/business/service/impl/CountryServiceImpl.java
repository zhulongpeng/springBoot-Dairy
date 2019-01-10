package com.zlp.dairy.business.service.impl;

import com.zlp.dairy.business.Handle.CountryHandle;
import com.zlp.dairy.business.entity.Country;
import com.zlp.dairy.business.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("CountryService")
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryHandle countryHandle;

    @Override
    public List<Country> findCountriesByCodeSet(Set<String> countrySet) {
        return countryHandle.findCountriesByCodeSet(countrySet);
    }
}
