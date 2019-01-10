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
}
