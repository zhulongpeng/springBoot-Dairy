package com.zlp.dairy.business.Handle;

import com.zlp.dairy.business.repository.IssuerProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IssuerProductHandle {

    @Autowired
    private IssuerProductRepository issuerProductRepository;
}
