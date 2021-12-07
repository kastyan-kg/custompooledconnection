package com.example.custompooledconnection;


import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


public class CustomPooledConnectionApplication {

    private static final String SQL_CREATE_TABLE = "CREATE TABLE Persons (\n" +
            "    PersonID int,\n" +
            "    LastName varchar(255),\n" +
            "    FirstName varchar(255),\n" +
            "    Address varchar(255),\n" +
            "    City varchar(255)\n" +
            ");";
    private static final String SELECT_FROM_PERSONS = "SELECT * FROM Persons";

    @SneakyThrows
    public static void main(String[] args) {
        ConnectionPool connectionPool = CustomPooledConnection.create("jdbc:h2:mem:test", "user", "password");
        try(Statement stmnt = connectionPool.getConnection().createStatement()) {
            stmnt.execute(SQL_CREATE_TABLE);
            ResultSet result = stmnt.executeQuery(SELECT_FROM_PERSONS);
            ResultSetMetaData metaData = result.getMetaData();
            System.out.println("# of columns :" + metaData.getColumnCount());
        }
    }

}
