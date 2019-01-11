package com.zlp.dairy.business.service.impl;

import com.zlp.dairy.base.util.XaUtil;
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

    @Override
    public Boolean findCountryByCodeAndLanguage(String code, String language) {
        Country country = countryHandle.findCountryByCodeAndLanguage(code, language);
        if(XaUtil.isNotEmpty(country)){
            return false;
        }
        return true;
    }

    @Override
    public String saveCountry(Country country) {
        Country saveCountry = countryHandle.saveCountry(country);
        if(XaUtil.isEmpty(saveCountry)) return null;
        return "SUCCESS";
    }

    @Override
    public List<String> findCodeGroupByCode() {
        return countryHandle.findCodeGroupByCode();
    }

    @Override
    public List<Country> findCountriesByCode(String code) {
        return countryHandle.findCountryByCode(code);
    }
}
