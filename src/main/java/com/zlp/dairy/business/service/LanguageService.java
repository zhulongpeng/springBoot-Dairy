package com.zlp.dairy.business.service;

import com.zlp.dairy.business.entity.Language;
import com.zlp.dairy.business.model.LanguageMO;
import com.zlp.dairy.business.model.LanguageVM;

import java.util.*;

public interface LanguageService {

    List<Language> allLanguage();

    String createLanguage(LanguageMO languageMO);

    List<LanguageMO> allLanguageByMarkets(List<String> markets);

    List<LanguageVM> getLanguageList();

}
