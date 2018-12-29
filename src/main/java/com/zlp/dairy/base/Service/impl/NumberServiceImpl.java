package com.zlp.dairy.base.Service.impl;

import com.zlp.dairy.base.Service.NumberService;
import com.zlp.dairy.base.constant.Constant;
import com.zlp.dairy.base.entity.BusinessKey;
import com.zlp.dairy.base.repository.BusinessKeyRepository;
import com.zlp.dairy.base.util.DateProcessUtil;
import com.zlp.dairy.base.util.XaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("NumberService")
public class NumberServiceImpl implements NumberService {

    @Autowired
    private BusinessKeyRepository businessKeyRepository;

    @Override
    public synchronized String findByBusinessKey(String businessKey) {
        String date = DateProcessUtil.getToday(DateProcessUtil.YYYYMMDDline);
        BusinessKey businessCode = businessKeyRepository.
                findByBusinessKeyAndNumberedDateAndStatus(businessKey, date, Constant.Status.vaild);
        if (XaUtil.isNotEmpty(businessCode)) {
            int sn = businessCode.getSerialNumber() + 1;
            businessCode.setSerialNumber(sn);
            businessKeyRepository.save(businessCode);
            return date + String.format("%04d", sn);
        } else {
            businessCode = new BusinessKey();
            businessCode.setBusinessKey(businessKey);
            businessCode.setNumberedDate(date);
            businessCode.setSerialNumber(1);
            businessCode.setStatus(Constant.Status.vaild);
            businessKeyRepository.save(businessCode);
            return date + String.format("%04d", 1);
        }
    }
}
