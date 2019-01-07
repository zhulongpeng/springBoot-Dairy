package com.zlp.dairy.design.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class App {

    private static  final String DB_URL = "com.mysql.jdbc.Driver";

    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(final String[] args)throws Exception {
        final CustomerDao inMemoryDao = new InMemoryCustomerDao();
        performOperationsUsing(inMemoryDao);
        final DataSource dataSource = createDataSource();
        createSchema(dataSource);
        final CustomerDao dbDao = new DbCustomerDaoImpl(dataSource);
        performOperationsUsing(dbDao);
        deleteSchema(dataSource);
    }

    private static void deleteSchema(DataSource dataSource) {

    }

    private static void createSchema(DataSource dataSource) {

    }

    private static DataSource createDataSource() {
        return null;
    }

    private static void performOperationsUsing(CustomerDao inMemoryDao) {

    }

}
