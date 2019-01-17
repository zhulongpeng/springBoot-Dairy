package com.zlp.dairy.business.repository;


import com.zlp.dairy.business.entity.IssuerProduct;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuerProductRepository extends
        PagingAndSortingRepository<IssuerProduct, Long>,
        JpaSpecificationExecutor<IssuerProduct> {
}
