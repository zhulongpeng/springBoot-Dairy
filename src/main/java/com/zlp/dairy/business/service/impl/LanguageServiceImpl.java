package com.zlp.dairy.business.service.impl;

import com.zlp.dairy.base.util.XaUtil;
import com.zlp.dairy.business.Handle.LanguageHandle;
import com.zlp.dairy.business.entity.Language;
import com.zlp.dairy.business.model.LanguageMO;
import com.zlp.dairy.business.service.LanguageService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {

    private Logger logger = LoggerFactory.getLogger(getClass());

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

    @Override
    public List<LanguageMO> allLanguageByMarkets(List<String> markets) {
        if (CollectionUtils.isEmpty(markets)) return null;
        logger.info(" allLanguageByMarkets start...");
        // 将入参数排序
        markets.sort(String::compareTo);
        return languageHandle.allLanguageByMarkets(markets);
    }
}