package com.example.custompooledconnection;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection();
}
