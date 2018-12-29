package com.zlp.dairy.base.repository;

import com.zlp.dairy.base.entity.BusinessKey;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BusinessKeyRepository extends
        PagingAndSortingRepository<BusinessKey,Long>,
        JpaSpecificationExecutor<BusinessKey>{

    @Query(nativeQuery = true,value = " "+
    " select t.* from tb_business_key t where t.business_key=?1 and t.numbered_date=?2 and t.status=?3 ")
    BusinessKey findByBusinessKeyAndNumberedDateAndStatus(String businessKey, String numberDate, int status);

}
