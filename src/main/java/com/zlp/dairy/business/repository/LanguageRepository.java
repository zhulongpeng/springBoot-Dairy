package com.zlp.dairy.business.repository;


import com.zlp.dairy.business.entity.Language;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LanguageRepository extends
        PagingAndSortingRepository<Language, Long>,
        JpaSpecificationExecutor<Language> {

    List<Language> findAllByStatus(Integer status);
}
