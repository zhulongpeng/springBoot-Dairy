package com.zlp.dairy.design.dao;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Creared by Ygritte on 2019/1/7
 */
public interface CustomerDao {

    Stream<Customer> getAll() throws Exception;

    Optional<Customer> getById(Integer id) throws Exception;

    Boolean add(Customer customer) throws Exception;

    Boolean update(Customer customer) throws Exception;

    Boolean delete(Customer customer) throws  Exception;
}
