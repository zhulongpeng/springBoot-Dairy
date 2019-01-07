package com.zlp.dairy.design.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static  final Logger logger = LoggerFactory.getLogger(DbCustomerDaoImpl.class);

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
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("Exception throw" + e.getMessage());
        }
    }

    private Customer createCustomer(ResultSet resultSet) throws SQLException {
        return new Customer(resultSet.getInt("ID"),
        resultSet.getString("FNAME"),
        resultSet.getString("LNAME"));
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public Optional<Customer> getById(Integer id) throws Exception {
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             PreparedStatement statement =  connection.prepareStatement("select * from tb_customer where id = ?")) {
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if(resultSet.next()){
            return Optional.of(createCustomer(resultSet));
        } else{
            return Optional.empty();
        }
        }catch (Exception e){
            throw new CustomerException(e.getMessage(), e);
        }finally {
            if(resultSet != null){
                resultSet.close();
            }
        }
    }

    @Override
    public Boolean add(Customer customer) throws Exception {
        if(getById(customer.getId()).isPresent()){
            return false;
        }
        try(Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT into tb_customer values (?,?,?)")){
            statement.setInt(1, customer.getId());
            statement.setString(2,customer.getFirstName());
            statement.setString(3,customer.getLastName());
            statement.execute();
            return true;
        }catch (SQLException e){
            throw new CustomerException(e.getMessage(), e);
        }
    }

    @Override
    public Boolean update(Customer customer) throws Exception {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("update tb_customer set first_name = ?, last_name = ? where ID = ?")
        ){
            statement.setInt(1,customer.getId());
            statement.setString(2, customer.getFirstName());
            statement.setString(3, customer.getLastName());
            return statement.executeUpdate() > 0;
        }catch (Exception e){
            throw new CustomerException(e.getMessage(), e);
        }
    }

    @Override
    public Boolean delete(Customer customer) throws Exception {
        try (Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("delete * from tb_customer where id = ?")
        ){
            statement.setInt(1, customer.getId());
            return statement.executeUpdate() > 0;
        }catch (SQLException e){
            throw new CustomerException(e.getMessage(), e);
        }
    }
}
