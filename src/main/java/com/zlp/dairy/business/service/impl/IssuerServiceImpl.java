package com.zlp.dairy.business.service.impl;

import com.zlp.dairy.base.Service.impl.NumberServiceImpl;
import com.zlp.dairy.business.Handle.IssuerHandle;
import com.zlp.dairy.business.entity.Issuer;
import com.zlp.dairy.business.repository.IssuerRepository;
import com.zlp.dairy.business.service.IssuerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class IssuerServiceImpl extends NumberServiceImpl implements IssuerService {

    @Autowired
    private IssuerRepository issuerRepository;

    @Autowired
    private IssuerHandle issuerHandle;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAll(List<Issuer> issuers) {
        issuerRepository.saveAll(issuers);
    }

    @Override
    public String findByBusinessKey() {
        return findByBusinessKey("Issuer");
    }

    @Override
    public List<Issuer> findAllByIssuerCodeSet(Set<String> issuerSet) {
        return issuerHandle.findAllByIssuerNameSet(issuerSet);
    }
}
