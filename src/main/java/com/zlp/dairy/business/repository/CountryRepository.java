package com.zlp.dairy.business.repository;

import com.zlp.dairy.business.entity.Country;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public interface CountryRepository extends
        PagingAndSortingRepository<Country, Long>,
        JpaSpecificationExecutor<Country> {

    List<Country> findAllByCodeInAndStatus(Set<String> code, Integer status);

    Country findByCodeAndLanguageAndStatus(String code, String language, Integer status);

    @Query(nativeQuery = true, value = " " +
            " select t.country_code from tb_country t where t.status = 1 group by t.country_code")
    List<String> findCodeGroupByCode();

    List<Country> findByCodeAndStatus(String code, Integer status);
}
