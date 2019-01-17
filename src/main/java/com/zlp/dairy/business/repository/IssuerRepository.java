package com.zlp.dairy.business.repository;


import com.zlp.dairy.business.entity.Issuer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuerRepository extends
        PagingAndSortingRepository<Issuer, Long>,
        JpaSpecificationExecutor<Issuer> {
}
