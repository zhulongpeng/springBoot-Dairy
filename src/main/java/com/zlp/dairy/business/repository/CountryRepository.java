package com.zlp.dairy.business.repository;

import com.zlp.dairy.business.entity.Country;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public interface CountryRepository extends
        PagingAndSortingRepository<Country, Long>,
        JpaSpecificationExecutor<Country> {

    List<Country> findAllByCodeInAndStatus(Set<String> code, Integer status);

}
