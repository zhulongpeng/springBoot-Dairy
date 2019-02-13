package com.zlp.dairy.business.repository;


import com.zlp.dairy.business.entity.Category;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends
        PagingAndSortingRepository<Category, Long>,
        JpaSpecificationExecutor<Category> {
}
