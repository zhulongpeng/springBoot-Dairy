package com.zlp.dairy.business.Handle;

import com.alibaba.fastjson.JSON;
import com.zlp.dairy.base.constant.Constant;
import com.zlp.dairy.business.entity.Language;
import com.zlp.dairy.business.model.LanguageMO;
import com.zlp.dairy.business.repository.LanguageRepository;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class LanguageHandle {

    @Autowired
    private LanguageRepository languageRepository;

    private Logger logger = LoggerFactory.getLogger(getClass());

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

    public List<LanguageMO> allLanguageByMarkets(List<String> markets) {
        logger.info(" 根据markets 获取所有语言清单数据 开始...");
        List<Map<String, String>> lists = languageRepository.findDistinctByCodeInAndStatus(markets, Constant.Status.vaild, Constant.Dict.ALL_LANGUAGE, Constant.Language.ONLINE);
        logger.info(" 根据markets 获取所有语言清单数据 结束... result:{}", JSON.toJSONString(lists));
        return convertList(lists);
    }

    private List<LanguageMO> convertList(List<Map<String, String>> lists) {
        List<LanguageMO> result = new ArrayList<>();
        if (CollectionUtils.isEmpty(lists)) return null;
        lists.forEach(temp -> {
            String language = temp.get("T_LANGUAGE");
            String name = temp.get("T_NAME");
            result.add(new LanguageMO(language, name));
        });
        return result;
    }
}
