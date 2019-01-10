package com.zlp.dairy.business.service.impl;

import com.zlp.dairy.base.util.XaUtil;
import com.zlp.dairy.business.Handle.LanguageHandle;
import com.zlp.dairy.business.entity.Language;
import com.zlp.dairy.business.model.LanguageMO;
import com.zlp.dairy.business.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageHandle languageHandle;

    @Override
    public List<Language> allLanguage() {
        return languageHandle.findAllLanguage();
    }

    @Override
    public String createLanguage(LanguageMO languageMO) {
        String language = languageHandle.createLanguage(languageMO);
        if(XaUtil.isEmpty(language)) return null;
        return "SUCCESS";
    }
}