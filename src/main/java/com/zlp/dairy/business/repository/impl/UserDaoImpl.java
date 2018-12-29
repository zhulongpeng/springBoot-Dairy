package com.zlp.dairy.business.repository.impl;

import com.zlp.dairy.base.constant.Constant;
import com.zlp.dairy.base.util.MyPage;
import com.zlp.dairy.base.util.XaUtil;
import com.zlp.dairy.business.entity.User;
import com.zlp.dairy.business.repository.UserDao;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Page<User> findByFilter(String searchStr, Integer nextPage, Integer pageSize) {
        StringBuffer sqlSelect = new StringBuffer();
        StringBuffer sqlFrom = new StringBuffer();
        StringBuffer sqlWhere = new StringBuffer();
        StringBuffer sqlLimit = new StringBuffer();
        Map<String, Object> param = new HashMap<>();
        sqlSelect.append(" select t.* ");
        sqlFrom.append(" from tb_user t ");
        sqlWhere.append(" where t.status =:status ");
        param.put("status", Constant.Status.vaild);
        List<String> unionUserId = getUnionUserIdList(param, searchStr);
        if(CollectionUtils.isNotEmpty(unionUserId)){
            String sqlUnion = StringUtils.join(unionUserId, " union ");
            sqlWhere.append(" and t.user_id in ( " + sqlUnion + " ) ");
        }
        String sql =sqlSelect.append(sqlFrom).append(sqlWhere).toString();
        Map<String, Object> paramTotal;
        Map<String, Object> paramQuery;
        String sqlTotal = " SELECT count(1) FROM ( %s ) stotal ";
        String sqlQuery;
        sqlTotal = String.format(sqlTotal, sql);
        paramTotal = new HashMap<>(param);
        sqlLimit.append(" limit :startLimit,:endLimit");
        param.put("startLimit", nextPage * pageSize);
        param.put("endLimit", (nextPage + 1) * pageSize);
        sqlQuery = sql + sqlLimit;
        paramQuery = param;
        Query nativeTotal = entityManager.createNativeQuery(sqlTotal);
        paramTotal.forEach(nativeTotal::setParameter);
        Query nativeQuery = entityManager.createNativeQuery(sqlQuery, User.class);
        paramQuery.forEach(nativeQuery::setParameter);
        List<User> resultList = (List<User>)nativeQuery.getResultList();
        int count = 0;
        List countQueryResultList = nativeTotal.getResultList();
        if (CollectionUtils.isNotEmpty(countQueryResultList)) {
            count = Integer.valueOf(countQueryResultList.get(0).toString());
        }
        return new MyPage<>(nextPage, pageSize, resultList, count);
    }

    private List<String> getUnionUserIdList(Map<String, Object> param, String searchStr) {
        List<String> unionSqlStrList = new ArrayList<>();
        if(XaUtil.isNotEmpty(searchStr)){
            boolean start = true;

            String sqlUserName = " " +
                    " select " + (start ? " distinct " : " ") + " u1.user_id " +
                    " from tb_user u1 " +
                    " where u1.user_name like :userName " +
                    " and u1.status =:status ";
            param.put("userName", "%" + searchStr + "%" );
            param.put("status", Constant.Status.vaild);
            unionSqlStrList.add(sqlUserName);
            start = false;

            String sqlRoleName = " " +
                    " select " + (start ? " distinct " : " ") + " u2.user_id " +
                    " from tb_user u2 inner join tb_role r1 " +
                    " on u2.role_id = r1.role_id " +
                    " where r1.role_name like :roleName " +
                    " and u2.status =:status " +
                    " and r1.status =:status ";
            param.put("roleName", "%" + searchStr + "%" );
            param.put("status", Constant.Status.vaild);
            unionSqlStrList.add(sqlRoleName);
            start = false;

            String sqlUserId = " " +
                    " select " + (start ? " distinct " : " ") + " u3.user_id " +
                    " from tb_user u3 " +
                    " where u3.user_id like :userId " +
                    " and u3.status =:status ";
            param.put("userId", "%" + searchStr + "%" );
            param.put("status", Constant.Status.vaild);
            unionSqlStrList.add(sqlUserId);
            start = false;

            String sqlEmail = " " +
                    " select " + (start ? " distinct " : " ") + " u4.user_id " +
                    " from tb_user u4 " +
                    " where u4.email like :email " +
                    " and u4.status =:status ";
            param.put("email", "%" + searchStr + "%" );
            param.put("status", Constant.Status.vaild);
            unionSqlStrList.add(sqlEmail);
        }
        return unionSqlStrList;
    }
}
