package com.zlp.dairy.business.Handle;

import com.zlp.dairy.base.constant.Constant;
import com.zlp.dairy.business.entity.Language;
import com.zlp.dairy.business.model.LanguageMO;
import com.zlp.dairy.business.repository.LanguageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LanguageHandle {

    @Autowired
    private LanguageRepository languageRepository;

    public List<Language> findAllLanguage() {
        return languageRepository.findAllByStatus(Constant.Status.vaild);
    }

    public String createLanguage(LanguageMO languageMO) {
        languageRepository.save(copyForEntity(languageMO, null));
        return "SUCCESS";
    }

    private Language copyForEntity(LanguageMO languageMO, Language result) {
        result = result == null ? new Language() : result;
        if(StringUtils.isNotBlank(languageMO.getCode())){
            result.setCode(languageMO.getCode());
        }
        if(StringUtils.isNotBlank(languageMO.getLanguage())){
            result.setLanguage(languageMO.getLanguage());
        }
        return result;
    }
}
