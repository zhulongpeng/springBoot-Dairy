package com.zlp.dairy.business.Handle;

import com.zlp.dairy.base.constant.Constant;
import com.zlp.dairy.business.entity.Country;
import com.zlp.dairy.business.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class CountryHandle {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> findCountriesByCodeSet(Set<String> countrySet) {
        return countryRepository.findAllByCodeInAndStatus(countrySet, Constant.Status.vaild);
    }

    public Country findCountryByCodeAndLanguage(String code, String language) {
        return countryRepository.findByCodeAndLanguageAndStatus(code, language, Constant.Status.vaild);
    }

    public Country saveCountry(Country country) {
        return countryRepository.save(country);

    }

    public List<String> findCodeGroupByCode() {
        return countryRepository.findCodeGroupByCode();
    }

    public List<Country> findCountryByCode(String code) {
        return countryRepository.findByCodeAndStatus(code, Constant.Status.vaild);
    }
}
