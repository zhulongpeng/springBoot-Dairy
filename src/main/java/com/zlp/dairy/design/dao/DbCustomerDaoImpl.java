package com.zlp.dairy.design.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class DbCustomerDaoImpl implements CustomerDao {

    private final DataSource dataSource;

    public DbCustomerDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Stream<Customer> getAll() throws Exception {
        Connection connection;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from tb_customer");
            ResultSet resultSet = statement.executeQuery();
            return StreamSupport.stream(new Spliterators.AbstractSpliterator<Customer>(Long.MAX_VALUE, Spliterator.ORDERED) {

                @Override
                public boolean tryAdvance(Consumer<? super Customer> action) {
                    try {
                        if(!resultSet.next()){
                            return false;
                        }
                        action.accept(createCustomer(resultSet));
                        return true;
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            },false).onClose(()-> mutedClose(connection, statement, resultSet));
        } catch (SQLException e) {
            throw new CustomerException(e.getMessage(), e);
        }
    }

    private void mutedClose(Connection connection, PreparedStatement statement, ResultSet resultSet) {

    }

    private Customer createCustomer(ResultSet resultSet) {
        return null;
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public Optional<Customer> getById(Integer id) throws Exception {
        return Optional.empty();
    }

    @Override
    public Boolean add(Customer customer) throws Exception {
        return null;
    }

    @Override
    public Boolean update(Customer customer) throws Exception {
        return null;
    }

    @Override
    public Boolean delete(Customer customer) throws Exception {
        return null;
    }
}
