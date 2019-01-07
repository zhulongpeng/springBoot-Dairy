package com.zlp.dairy.design.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class InMemoryCustomerDao implements CustomerDao {

    private Map<Integer, Customer> idToCustomer = new HashMap<>();

    @Override
    public Stream<Customer> getAll() throws Exception {
        return idToCustomer.values().stream();
    }

    @Override
    public Optional<Customer> getById(Integer id) throws Exception {
        return Optional.ofNullable(idToCustomer.get(id));
    }

    @Override
    public Boolean add(final Customer customer) throws Exception {
        if(getById(customer.getId()).isPresent()){
            return false;
        }
        idToCustomer.put(customer.getId(), customer);
        return true;
    }

    @Override
    public Boolean update(Customer customer) throws Exception {
        return idToCustomer.replace(customer.getId(), customer) != null;
    }

    @Override
    public Boolean delete(Customer customer) throws Exception {
        return idToCustomer.remove(customer.getId()) != null;
    }
}
