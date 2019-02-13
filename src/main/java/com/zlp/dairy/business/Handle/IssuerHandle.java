package com.zlp.dairy.business.Handle;

import com.zlp.dairy.base.constant.Constant;
import com.zlp.dairy.business.entity.Issuer;
import com.zlp.dairy.business.repository.IssuerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class IssuerHandle {

    @Autowired
    private IssuerRepository issuerRepository;

    public List<Issuer> findAllByIssuerNameSet(Set<String> issuerNameSet) {
        return issuerRepository.findAllByIssuerNameAndStatus(issuerNameSet, Constant.Status.vaild);
    }
}
