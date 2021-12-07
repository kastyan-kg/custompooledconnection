package com.example.custompooledconnection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

@Data
@AllArgsConstructor
@Getter
public class CustomPooledConnection implements ConnectionPool{

    private String url;
    private String user;
    private String password;
    private Queue<Connection> connectionPool;

    public static CustomPooledConnection create(String url, String user, String password) throws SQLException {
        Queue<Connection> pool = new ConcurrentLinkedDeque<>();
        for (int i = 0; i < 8; i++) {
            pool.add(createConnection(url, user, password));
        }
        return new CustomPooledConnection(url, user, password, pool);
    }
    private static Connection createConnection(String url, String user, String password)
            throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public Connection getConnection() {
        return connectionPool.poll();
    }
}
