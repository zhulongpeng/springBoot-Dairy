package com.zlp.dairy.business.service;


import com.zlp.dairy.business.entity.Issuer;

import java.util.List;
import java.util.Set;

public interface IssuerService {

    void saveAll(List<Issuer> allIssuerForInsert);

    String findByBusinessKey();

    List<Issuer> findAllByIssuerCodeSet(Set<String> issuerSet);
}
