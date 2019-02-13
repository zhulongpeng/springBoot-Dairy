package com.zlp.dairy.business.repository;


import com.zlp.dairy.business.entity.Language;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Map;

public interface LanguageRepository extends
        PagingAndSortingRepository<Language, Long>,
        JpaSpecificationExecutor<Language> {

    List<Language> findAllByStatus(Integer status);

    @Query(nativeQuery = true,value = " "+
    " select DISTINCT t.language as T_LANGUAGE ,td.enum_name as T_NAME " +
            "from tb_cms_language_list t,tb_cms_dictionary td " +
            "where td.dict_code = ?3 and td.status = ?2 " +
            "and td.enum_code = t.language and t.code in ?1 " +
            "and t.status = ?2 and t.language " +
            "in ( select ol.language from tb_cms_language_list ol " +
            "where ol.status = ?2 and ol.code = ?4) ")
    List<Map<String, String>> findDistinctByCodeInAndStatus(List<String> code, Integer status, String dictCode, String onlineLanguage);
}
